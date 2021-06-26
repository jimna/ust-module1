package com.ust.person.service;

import com.ust.person.exception.UserAlreadyExistsException;
import com.ust.person.exception.UserNotFoundException;
import com.ust.person.model.Person;
import com.ust.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class PersonServiceImpl implements  PersonService{

//    private final String POST_ADD_PERSON = "http://localhost:8085/person/insert";
//    private final String UPDATE_PERSON_BY_ID = "http://localhost:8085/person/update/{id}";
//    private final String DELETE_PERSON_BY_ID ="http://localhost:8085/person/delete/{id}";
//
//    @Autowired
//    WebClient.Builder webClientBuilder;

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
    public Person manipulateData(String userId) throws UserNotFoundException {
        return null;
    }
}
