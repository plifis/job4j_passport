package ru.plifis.job4j_passport.services;


import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;
import ru.plifis.job4j_passport.model.PassportDto;
import ru.plifis.job4j_passport.model.PassportEntity;

@Component
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PassportMapper {
    void updatePassportEntityFromDto(PassportDto dto, @MappingTarget PassportEntity entity);

}