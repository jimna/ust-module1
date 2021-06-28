package com.ust.person.service;

import com.ust.person.exception.UserAlreadyExistsException;
import com.ust.person.exception.UserNotFoundException;
import com.ust.person.model.Person;

public interface PersonService {

    public Person addPerson(Person person) throws UserAlreadyExistsException;
    
    Person updatePerson(Person person) throws UserNotFoundException;
    
    public String deletePerson(String id) throws UserNotFoundException;
    
    

}
