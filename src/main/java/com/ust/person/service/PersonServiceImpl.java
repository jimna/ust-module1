package com.ust.person.service;

import com.ust.person.exception.UserAlreadyExistsException;
import com.ust.person.exception.UserNotFoundException;
import com.ust.person.model.Person;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PersonServiceImpl implements  PersonService{

    private final String POST_ADD_PERSON = "http://localhost:8088/person/insert";
    private final String UPDATE_PERSON_BY_ID = "http://localhost:8088/person/update/{id}";
    private final String DELETE_PERSON_BY_ID ="http://localhost:8088/person/delete/{id}";
    private final String FIND_PERSON_BY_ID ="http://localhost:8088/person/{id}";

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Person addPerson(Person person) throws UserAlreadyExistsException {
        ResponseEntity<String> response = restTemplate.getForEntity(FIND_PERSON_BY_ID, String.class);
        HttpStatus status = response.getStatusCode();
        if (status.equals(HttpStatus.OK)) {
            throw new UserAlreadyExistsException("User already exist");
        }else{

            restTemplate.put(POST_ADD_PERSON,com.ust.person.model.Person.class);
            }
        return person;

    }

    @Override
    public boolean deletePerson(Long id) throws UserNotFoundException {
        restTemplate.put(DELETE_PERSON_BY_ID,com.ust.person.model.Person.class);
        return true;
    }

    @Override
    public boolean updatePerson(Long id) throws UserNotFoundException {
        restTemplate.put(UPDATE_PERSON_BY_ID,com.ust.person.model.Person.class);
        return true;
    }
}
