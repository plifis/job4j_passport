package ru.plifis.job4j_passport.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.plifis.job4j_passport.model.PassportDto;
import ru.plifis.job4j_passport.model.PassportEntity;

@Component
public class ConverterPassport {
    private final ModelMapper modelMapper;
    public ConverterPassport(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PassportDto convertPassportEntityToPassportDto(PassportEntity entity) {
        return modelMapper.map(entity, PassportDto.class);
    }
}