package ru.plifis.job4j_passport.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.plifis.job4j_passport.model.PassportEntity;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Repository
public interface PassportRepository extends CrudRepository<PassportEntity, Long> {

    Collection<PassportEntity> findAll();

    @Query("select PassportEntity from PassportEntity p where p.series = :series")
    List<PassportEntity> findPassportEntityBySeries(@Param("series") Integer series);

    @Query("select PassportEntity from PassportEntity p where p.expired <= :dateExpired")
    Collection<PassportEntity> findAllUnavailablePassports(@Param("dateExpired")Timestamp dateExpired);
}