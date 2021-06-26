package com.ust.person.controller;

import com.ust.person.model.Person;
import com.ust.person.service.PersonService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/person")
@Api
public class PersonController {

    @Autowired
    PersonService service;

    @PostMapping("/add")
    public ResponseEntity<String> addPerson(@RequestBody Person person){
        try {
            if(!service.addPerson(person)) {
                new ResponseEntity<String>("Validation Failed", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<String>("Created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
