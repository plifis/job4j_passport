package ru.plifis.job4j_passport.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.plifis.job4j_passport.model.PassportDto;
import ru.plifis.job4j_passport.model.PassportEntity;
import ru.plifis.job4j_passport.services.PassportService;

import java.util.List;

@RestController
public class PassportControllers {
    private final PassportService passportService;

    @Autowired
    public PassportControllers(PassportService passportService) {
        this.passportService = passportService;
    }

    @GetMapping("/find")
    public List<PassportEntity> findPassportById(@RequestParam(value = "seria") Integer series) {
        return passportService.findPassportBySeries(series);
    }

    @GetMapping("/find")
    public List<PassportEntity> findAllPassports() {
        return passportService.findAllPassports();
    }

    @GetMapping("/unavailable")
    public List<PassportEntity> findAllUnavailablePassports() {
        return passportService.findAllUnavailablePassports();
    }

    @GetMapping("/find-replaceable")
    public List<PassportEntity> findAllReplaceablePassports() {
        return passportService.findAllReplaceablePassports();
    }

    @PostMapping("/save")
    public ResponseEntity<PassportEntity> createPassport(@RequestBody PassportEntity passportEntity) {
        PassportEntity rsl = passportService.createPassport(passportEntity);
        if (rsl != null) {
            return ResponseEntity.ok().body(rsl);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(" /update")
    public ResponseEntity<PassportEntity> updatePassportById(@RequestParam(value = "id") Long id,
                                                             @RequestBody PassportDto passportDto) {
        PassportEntity rsl = passportService.updatePassportById(id, passportDto);
        if (rsl != null) {
            return ResponseEntity.ok().body(rsl);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deletePassportById(@RequestParam(value = "id") Long id) {
        passportService.deletePassportById(id);
        return ResponseEntity.ok().build();
    }
}