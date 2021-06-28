package com.ust.person.service;

import com.ust.person.exception.UserAlreadyExistsException;
import com.ust.person.exception.UserNotFoundException;
import com.ust.person.model.Person;

public interface PersonService {

    public Person addPerson(Person person) throws UserAlreadyExistsException;

    boolean deletePerson(Long id) throws UserNotFoundException;

    boolean updatePerson(Long id) throws UserNotFoundException;

}
