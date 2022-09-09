package ru.plifis.job4j_passport.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.plifis.job4j_passport.model.PassportDto;
import ru.plifis.job4j_passport.model.PassportEntity;
import ru.plifis.job4j_passport.repository.PassportRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassportService {
    private final PassportRepository passportRepository;
    private final PassportMapper passportMapper;
    private final ConverterPassport converterPassport;

    @Autowired
    public PassportService(PassportRepository passportRepository,
                           PassportMapper passportMapper,
                           ConverterPassport converterPassport) {
        this.passportRepository = passportRepository;
        this.passportMapper = passportMapper;
        this.converterPassport = converterPassport;
    }

    public List<PassportDto> findPassportBySeries(Integer series) {
        return passportRepository.findPassportEntityBySeries(series).stream()
                .map(converterPassport::convertPassportEntityToPassportDto).collect(Collectors.toList());
    }

    public List<PassportDto> findAllPassports() {
        return passportRepository.findAll().stream()
                .map(converterPassport::convertPassportEntityToPassportDto).collect(Collectors.toList());
    }

    public List<PassportDto> findAllUnavailablePassports() {
        Timestamp dateExpired = new Timestamp(System.currentTimeMillis());
        Collection<PassportEntity> rsl =  passportRepository.findAllUnavailablePassports(dateExpired);
        return rsl.stream()
                .map(converterPassport::convertPassportEntityToPassportDto).collect(Collectors.toList());
    }

    public List<PassportDto> findAllReplaceablePassports() {
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = localDateTime.plusMonths(3);
        Timestamp dateExpired = Timestamp.valueOf(localDateTime);
        return passportRepository.findAllUnavailablePassports(dateExpired).stream()
                .map(converterPassport::convertPassportEntityToPassportDto).collect(Collectors.toList());
    }

    public PassportDto createPassport(PassportEntity passportEntity) {
        PassportEntity entity = passportRepository.save(passportEntity);
        return converterPassport.convertPassportEntityToPassportDto(entity);
    }

    public PassportEntity updatePassportById(Long id, PassportDto passportDto) {
        PassportEntity passportEntity = passportRepository.findById(id).get();
        passportMapper.updatePassportEntityFromDto(passportDto, passportEntity);
        return passportRepository.save(passportEntity);
    }

    public void deletePassportById(Long id) {
        passportRepository.deleteById(id);
    }
}
