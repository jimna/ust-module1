package com.ust.person.service;

import com.ust.person.exception.UserAlreadyExistsException;
import com.ust.person.exception.UserNotFoundException;
import com.ust.person.model.Person;

public interface PersonService {

    public String addPerson(Person person) throws UserAlreadyExistsException;
    
    Person updatePerson(Person person,Integer id) throws UserNotFoundException;
    
    public boolean deletePerson(Integer id) throws UserNotFoundException;
    
    

}
