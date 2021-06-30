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
    public boolean addPerson(Person person) throws UserAlreadyExistsException {
//    	ResponseEntity<Person> resp = restTemplate.getForEntity(POST_GET_PERSON_URI, Person.class,person.getId());
//    	if(resp.getStatusCode().equals(HttpStatus.EXPECTATION_FAILED)) {
//    		throw new UserAlreadyExistsException("Person Already exists!");
//    	}else {
    	ResponseEntity<Person> resp =	restTemplate.postForEntity(POST_ADD_PERSON_URI, person,Person.class);
    	System.out.println(resp);
    	
    	
//    	}
    	
        return true;
    }
    @Value("${PUTUPDATEPERSON.url}")
    private String PUT_UPDATE_PERSON_URI;
    @Override
	public boolean updatePerson(Person person,Integer id) throws UserNotFoundException {
    	Map<String, Integer> params = new HashMap<String, Integer>();
	    params.put("id", id);
		restTemplate.put(PUT_UPDATE_PERSON_URI,person,params);
		return true;
	}
    @Value("${PUTDELETEPERSON.url}")
    private String PUT_DELETE_PERSON_URI;
	@Override
	public boolean deletePerson(Integer id) throws UserNotFoundException {
		Map<String, Integer> params = new HashMap<String, Integer>();
	    params.put("id", id);
		restTemplate.put(PUT_DELETE_PERSON_URI, Person.class,params);
		return true;
		}



	
}
