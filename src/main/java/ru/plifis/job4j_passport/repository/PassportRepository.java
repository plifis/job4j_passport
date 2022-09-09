package ru.plifis.job4j_passport.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.plifis.job4j_passport.model.PassportEntity;

import java.sql.Timestamp;
import java.util.Collection;

public interface PassportRepository extends CrudRepository<PassportEntity, Long> {

    Collection<PassportEntity> findAll();

    @Query("select p from PassportEntity p where p.series = :series")
    Collection<PassportEntity> findPassportEntityBySeries(@Param("series") Integer series);

    @Query("select p from PassportEntity p where p.expired <= :dateExpired")
    Collection<PassportEntity> findAllUnavailablePassports(@Param("dateExpired") Timestamp dateExpired);
}