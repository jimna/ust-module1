package com.ust.person.controller;

import com.ust.person.exception.UserAlreadyExistsException;
import com.ust.person.exception.UserNotFoundException;
import com.ust.person.model.Person;
import com.ust.person.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation("Add a Person Details")
    public ResponseEntity<?> addPerson(@Valid @RequestBody Person person) throws UserAlreadyExistsException {
    	if(service.addPerson(person).equals("Person Already Exist")) {
    		 return new ResponseEntity<String>("Person Already Exist", HttpStatus.FORBIDDEN);
    	}
        return new ResponseEntity<String>("Created", HttpStatus.CREATED);

    }

    @PutMapping("/manipulate/{id}/{operation}")
    @ApiOperation("Update or Delete data")
    public ResponseEntity<?> manipulateData( @PathVariable() Integer id,@PathVariable() String operation,@Valid @RequestBody Person person){
        try{
            if(operation.equalsIgnoreCase("delete")){
                try{
                    service.deletePerson(id);
                    return new ResponseEntity<String>("Deleted", HttpStatus.OK);
                }catch(UserNotFoundException us){
                    return new ResponseEntity<String>("User Not Found", HttpStatus.NOT_FOUND);
                }

            }
            else if(operation.equalsIgnoreCase("update")) {
                service.updatePerson(id, person);
                return new ResponseEntity<String>("Success Updated", HttpStatus.OK);
            }else{
                return new ResponseEntity<String>("Invalid Operation", HttpStatus.NOT_ACCEPTABLE);
            }
        }catch (UserNotFoundException ue){
            return new ResponseEntity<String>("User Not Found", HttpStatus.FORBIDDEN);
        }
    }
}


