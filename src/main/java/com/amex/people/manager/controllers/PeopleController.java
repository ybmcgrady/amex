package com.amex.people.manager.controllers;

import com.amex.people.manager.dataaccess.PeopleDAO;
import com.amex.people.manager.dataaccess.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PeopleRepository peopleRepo;


    public PeopleController(PeopleRepository peopleRepo) {
        this.peopleRepo = peopleRepo;
    }

    @PostMapping
    public ResponseEntity<PeopleDAO> create(@RequestBody PeopleDAO peopleDAO) {
        try {
            peopleRepo.save(peopleDAO);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(peopleDAO, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(peopleDAO, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<PeopleDAO> read(@PathVariable Long id) {
        Optional<PeopleDAO> peopleDAOOptional = peopleRepo.findById(id);
        if (peopleDAOOptional != null) {
            return new ResponseEntity<>(peopleDAOOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public List<PeopleDAO> list() {
        return (List<PeopleDAO>)peopleRepo.findAll();
    }


    @PutMapping("{id}")
    public ResponseEntity<PeopleDAO> update(@PathVariable Long id, @RequestBody PeopleDAO peopleDAO) {
        Optional<PeopleDAO> peopleDAOOptional = peopleRepo.findById(id);

        if (!peopleDAOOptional.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        try {
            peopleDAO.setId(id);
            peopleRepo.save(peopleDAO);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(peopleDAO, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return new ResponseEntity<>(peopleDAO, HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<PeopleDAO> delete(@PathVariable Long id) {
        peopleRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
