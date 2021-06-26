package com.ust.person.service;

import com.ust.person.exception.UserAlreadyExistsException;
import com.ust.person.exception.UserNotFoundException;
import com.ust.person.model.Person;
import com.ust.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements  PersonService{

    @Autowired
    PersonRepository repo;
    @Override
    public boolean addPerson(Person person) throws UserAlreadyExistsException {
        if(repo.findById(person.getId()).isPresent()){
            throw new UserAlreadyExistsException("User already exist");
        }
        repo.save(person);
        return true;
    }

    @Override
    public Person getUserById(String userId) throws UserNotFoundException {
        return null;
    }
}
