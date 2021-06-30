package com.ust.person.service;

import com.ust.person.exception.UserAlreadyExistsException;
import com.ust.person.exception.UserNotFoundException;
import com.ust.person.model.Person;
import org.springframework.http.ResponseEntity;

public interface PersonService {

    public String addPerson(Person person) throws UserAlreadyExistsException;

    boolean deletePerson(Integer id) throws UserNotFoundException;

    Person updatePerson(Integer id,Person person) throws UserNotFoundException;

}
