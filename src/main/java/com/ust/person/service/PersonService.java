package com.ust.person.service;

import com.ust.person.exception.UserAlreadyExistsException;
import com.ust.person.exception.UserNotFoundException;
import com.ust.person.model.Person;

public interface PersonService {

    public boolean addPerson(Person person) throws UserAlreadyExistsException;

    Person getUserById(String userId) throws UserNotFoundException;

}
