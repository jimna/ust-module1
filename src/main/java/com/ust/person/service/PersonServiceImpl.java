package com.ust.person.service;

import com.ust.person.exception.UserAlreadyExistsException;
import com.ust.person.exception.UserNotFoundException;
import com.ust.person.model.Person;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class PersonServiceImpl implements  PersonService{
	
	@Autowired
	private RestTemplate restTemplate;
	@Value("${POSTGETPERSON.url}")
    private String POST_GET_PERSON_URI;
	
	@Value("${POSTADDPERSON.url}")
    private String POST_ADD_PERSON_URI;

    @Override
    public Person addPerson(Person person) throws UserAlreadyExistsException {
    	 Map<String, Long> params = new HashMap<String, Long>();
    	    params.put("id", 1L);
    	ResponseEntity<Person> resp = restTemplate.getForEntity(POST_GET_PERSON_URI, Person.class,params);
    	if(resp.getStatusCode().equals(HttpStatus.OK)) {
    		throw new UserAlreadyExistsException("Person Already exists!");
    	}
    	restTemplate.postForObject(POST_ADD_PERSON_URI, person,Person.class);
        return person;
    }
    @Value("${PUTUPDATEPERSON.url}")
    private String PUT_UPDATE_PERSON_URI;
    @Override
	public boolean updatePerson(Long id) throws UserNotFoundException {
    	Map<String, Long> params = new HashMap<String, Long>();
	    params.put("id", 1L);
		restTemplate.put(PUT_UPDATE_PERSON_URI, Person.class,params);
		return true;
	}
    @Value("${PUTDELETEPERSON.url}")
    private String PUT_DELETE_PERSON_URI;
	@Override
	public boolean deletePerson(Long id) throws UserNotFoundException {
		restTemplate.put(PUT_DELETE_PERSON_URI, Person.class);
		return true;
		}



	
}
