package ru.plifis.job4j_passport.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.plifis.job4j_passport.model.PassportDto;
import ru.plifis.job4j_passport.model.PassportEntity;
import ru.plifis.job4j_passport.services.PassportService;

import java.util.List;

@RestController
@RequestMapping("/passport")
public class PassportControllers {
    private final PassportService passportService;
    private KafkaTemplate<Long, List<PassportDto>> kafkaTemplate;

    @Autowired
    public PassportControllers(PassportService passportService,
                               KafkaTemplate kafkaTemplate) {
        this.passportService = passportService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/find")
    public List<PassportDto> findPassportBySeries(@RequestParam(required = false) Integer seria) {
        if (seria == null) {
            return passportService.findAllPassports();

        } else {
            return passportService.findPassportBySeries(seria);
        }
    }

    @GetMapping("/unavailable")
    public void findAllUnavailablePassports() {
        List<PassportDto> list = passportService.findAllUnavailablePassports();
        if (!list.isEmpty()) {
            kafkaTemplate.send("mail", list);
        }
    }

    @GetMapping("/find-replaceable")
    public List<PassportDto> findAllReplaceablePassports() {
        return passportService.findAllReplaceablePassports();
    }

    @PostMapping("/save")
    public ResponseEntity<PassportDto> createPassport(@RequestBody PassportEntity passportEntity) {
        PassportDto rsl = passportService.createPassport(passportEntity);
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