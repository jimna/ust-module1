package com.ust.person.controller;

import com.ust.person.exception.UserAlreadyExistsException;
import com.ust.person.exception.UserNotFoundException;
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
            return new ResponseEntity<String>("Person Created", HttpStatus.OK);
        } catch (UserAlreadyExistsException ae) {
            return new ResponseEntity<String>("User Already Exists", HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/manipulate")
    public ResponseEntity<?> manipulateData(@Valid @PathVariable() Long id,@PathVariable() String operation){
    	   try{
               if(operation.equalsIgnoreCase("delete")){
                   service.deletePerson(id);
                   return new ResponseEntity<String>("Deleted Successfully", HttpStatus.OK);
               }
               else if(operation.equalsIgnoreCase("update")) {
                   service.updatePerson(id);
                   return new ResponseEntity<String>("Updated", HttpStatus.OK);
               }else{
                   return new ResponseEntity<String>("Invalid Operation", HttpStatus.NOT_ACCEPTABLE);
               }
           }catch (UserNotFoundException ue){
               return new ResponseEntity<String>("User Not Found", HttpStatus.FORBIDDEN);
           }
    }
}


