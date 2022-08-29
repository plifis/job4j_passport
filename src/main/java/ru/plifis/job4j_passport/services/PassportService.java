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

    @Autowired
    public PassportService(PassportRepository passportRepository,
                           PassportMapper passportMapper) {
        this.passportRepository = passportRepository;
        this.passportMapper = passportMapper;
    }

    public List<PassportEntity> findPassportBySeries(Integer series) {
        return passportRepository.findPassportEntityBySeries(series);
    }

    public List<PassportEntity> findAllPassports() {
        Collection<PassportEntity> rsl = passportRepository.findAll();
        return rsl.stream().collect(Collectors.toList());
    }

    public List<PassportEntity> findAllUnavailablePassports() {
        Timestamp dateExpired = new Timestamp(System.currentTimeMillis());
        return passportRepository.findAllUnavailablePassports(dateExpired).stream().collect(Collectors.toList());
    }

    public List<PassportEntity> findAllReplaceablePassports() {
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = localDateTime.plusMonths(3);
        Timestamp dateExpired = Timestamp.valueOf(localDateTime);
        return passportRepository.findAllUnavailablePassports(dateExpired).stream().collect(Collectors.toList());
    }

    public PassportEntity createPassport(PassportEntity passportEntity) {
        return passportRepository.save(passportEntity);
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
