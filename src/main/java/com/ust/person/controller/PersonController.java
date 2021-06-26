package com.ust.person.controller;

import com.ust.person.exception.UserAlreadyExistsException;
import com.ust.person.model.Person;
import com.ust.person.service.PersonService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/person")
@Api
public class PersonController {

    @Autowired
    PersonService service;

    @PostMapping("/add")
    public ResponseEntity<?> addPerson(@Valid @RequestBody Person person) throws UserAlreadyExistsException {
        try {
            service.addPerson(person);
            return new ResponseEntity<String>("created", HttpStatus.OK);
        } catch (UserAlreadyExistsException ae) {
            return new ResponseEntity<String>("User Already Exists", HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/manipulate")
    public ResponseEntity<?> manipulateData(@Valid @PathVariable() String id){
        return null;
    }
}


