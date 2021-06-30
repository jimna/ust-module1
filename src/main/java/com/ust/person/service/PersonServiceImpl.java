package com.ust.person.service;

import com.ust.person.exception.UserAlreadyExistsException;
import com.ust.person.exception.UserNotFoundException;
import com.ust.person.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class PersonServiceImpl implements  PersonService{

    @Value("${addPerson.url}")
    private String POST_ADD_PERSON;
    @Value("${updatePerson.url}")
    private String UPDATE_PERSON_BY_ID;
    @Value("${deletePerson.url}")
    private String DELETE_PERSON_BY_ID;
    @Value("${findPerson.url}")
    private String FIND_PERSON_BY_ID;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public boolean addPerson(Person person) throws UserAlreadyExistsException {
        ResponseEntity<String> response = restTemplate.getForEntity(FIND_PERSON_BY_ID , String.class,person.getId());
        HttpStatus status = response.getStatusCode();
        if (status.equals(HttpStatus.OK)) {
            return false;
        }else{
            restTemplate.put(POST_ADD_PERSON ,Person.class);
            return true;
            }


    }

    @Override
    public boolean deletePerson(int id) throws UserNotFoundException {
        try {
            Map<String, Integer> params = new HashMap<String, Integer>();
            params.put("id", id);
            restTemplate.put(DELETE_PERSON_BY_ID , Person.class,params);
            return true;
        }catch(NoSuchElementException ue){
            throw new UserNotFoundException("User Not Found");
        }
    }

    @Override
    public boolean updatePerson(int id) throws UserNotFoundException {
        try {
            Map<String, Integer> params = new HashMap<String, Integer>();
            params.put("id", id);
            restTemplate.put(UPDATE_PERSON_BY_ID+id ,Person.class,params);
            return true;
        }catch(NoSuchElementException ue){
            throw new UserNotFoundException("User Not Found");
        }
    }
}
