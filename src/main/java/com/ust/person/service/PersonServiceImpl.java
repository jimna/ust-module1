package com.ust.person.service;

import com.ust.person.exception.UserAlreadyExistsException;
import com.ust.person.exception.UserNotFoundException;
import com.ust.person.model.Person;


import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class PersonServiceImpl implements  PersonService{
	
	private final String POST_GET_PERSON_URI = "http://localhost:8085/person/{id}";
	private final String POST_ADD_PERSON_URI = "http://localhost:8085/person/insert";
	private final String PUT_UPDATE_PERSON_URI = "http://localhost:8085/person/update{id}";
	private final String PUT_DELETE_PERSON_URI = "http://localhost:8085/person/delete{id}";
	
	@Autowired
	private RestTemplate restTemplate;
	

    @Override
    public Person addPerson(Person person) throws UserAlreadyExistsException {
    	ResponseEntity<Person> resp = restTemplate.getForEntity(POST_GET_PERSON_URI, Person.class);
    	if(resp.getStatusCode().equals(HttpStatus.OK)) {
    		throw new UserAlreadyExistsException("Person Already exists!");
    	}
    	restTemplate.put(POST_ADD_PERSON_URI, Person.class);
        return person;
    }

    @Override
	public boolean updatePerson(Long id) throws UserNotFoundException {
		restTemplate.put(PUT_UPDATE_PERSON_URI, Person.class);
		return true;
	}

	@Override
	public boolean deletePerson(Long id) throws UserNotFoundException {
		restTemplate.put(PUT_DELETE_PERSON_URI, Person.class);
		return true;
		}



	
}
