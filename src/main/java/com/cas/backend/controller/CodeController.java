package com.cas.backend.controller;

import com.cas.backend.entity.Code;
import com.cas.backend.repository.CodeRepository;
import com.cas.backend.service.Auditable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CodeController {

    Logger log = LoggerFactory.getLogger(CodeController.class);

    private final CodeRepository repository;

    CodeController(CodeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/codes")
    List<Code> all() {
        log.info("CodeController all()");
        return repository.findAll();
    }

    @PostMapping("/codes")
    @Auditable
    Code addCode(@RequestBody Code code) {
        log.info("CodeController save()");
        return repository.save(code);
    }

    @DeleteMapping("/codes/{id}")
    @Auditable
    public ResponseEntity<HttpStatus> deleteCode(@PathVariable("id") long id) {
        log.info("CodeController deleteCode(), id:{}", id);
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
