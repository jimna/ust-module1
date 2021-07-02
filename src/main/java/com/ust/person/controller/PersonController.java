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
    	
    	if(service.addPerson(person).equals("person already exist")) {
   		 return new ResponseEntity<String>("person already exist", HttpStatus.FORBIDDEN);
   	}
       return new ResponseEntity<String>("created", HttpStatus.CREATED);

   }
    
   // @RequestBody Person person,
    @PutMapping("/manipulate/{id}/{operation}")
    public ResponseEntity<?> manipulateData(@Valid @RequestBody Person person,  @PathVariable() Integer id,@PathVariable() String operation){
    	   try{
               if(operation.equalsIgnoreCase("delete")){
            	   try {
            		   if(service.deletePerson(id)) {
            			   return new ResponseEntity<String>("Deleted Successfully", HttpStatus.OK);
            		   }return new ResponseEntity<String>("Person Not Found", HttpStatus.NOT_FOUND);
            	   }catch (UserNotFoundException e) {
            		   return new ResponseEntity<String>("Person Not Found", HttpStatus.NOT_FOUND);
				}           
               }
               else if(operation.equalsIgnoreCase("update")) {
                   if((service.updatePerson(person, id))==null)
                   {
                	   return new ResponseEntity<String>("Person Not Found", HttpStatus.NOT_FOUND);
                   } 
                   return new ResponseEntity<String>("Updated", HttpStatus.OK);
               }else{
                   return new ResponseEntity<String>("Invalid Operation", HttpStatus.NOT_ACCEPTABLE);
               }
           }catch (UserNotFoundException ue){
               return new ResponseEntity<String>("User Not Found", HttpStatus.FORBIDDEN);
           }
    }
}


