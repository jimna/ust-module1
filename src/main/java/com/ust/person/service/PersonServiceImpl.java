package com.ust.person.service;

import com.ust.person.exception.UserAlreadyExistsException;
import com.ust.person.exception.UserNotFoundException;
import com.ust.person.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class PersonServiceImpl implements PersonService {

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
	public String addPerson(Person person) throws UserAlreadyExistsException {
		RestTemplate restTemplate = new RestTemplate();
		RestTemplate restTemplate1 = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		String body = "{}";
		HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);

		String id = "" + person.getId() + "";
		ResponseEntity<String> responseEntity = restTemplate1.exchange(FIND_PERSON_BY_ID + id, HttpMethod.GET,
				requestEntity, String.class);

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			ResponseEntity<String> response = restTemplate.postForEntity(POST_ADD_PERSON, person, String.class);
			return response.getBody();
		}
		else if (responseEntity.getStatusCode() == HttpStatus.ALREADY_REPORTED) {
			return "Person Already Exist";
		}
		return null;
	}

	@Override
	public boolean deletePerson(Integer id) throws UserNotFoundException {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("id", id);
		RestTemplate restTemplate1 = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		String body = "{}";
		HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);

		String uid = "" + id + "";
		ResponseEntity<String> responseEntity = restTemplate1.exchange(FIND_PERSON_BY_ID + uid, HttpMethod.GET,
				requestEntity, String.class);

		if (responseEntity.getStatusCode() == HttpStatus.ALREADY_REPORTED) {
			restTemplate.put(DELETE_PERSON_BY_ID, Person.class, params);
			return true;
		}else if(responseEntity.getStatusCode() == HttpStatus.OK){
			return false;
		}
		return false;
	}

	@Override
	public Person updatePerson(Integer id, Person person) throws UserNotFoundException {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("id", id);
		RestTemplate restTemplate1 = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		String body = "{}";
		HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);

		String uid = "" + id + "";
		ResponseEntity<String> responseEntity = restTemplate1.exchange(FIND_PERSON_BY_ID + uid, HttpMethod.GET,
				requestEntity, String.class);

		if (responseEntity.getStatusCode() == HttpStatus.ALREADY_REPORTED) {
			restTemplate.put(UPDATE_PERSON_BY_ID, person, params);
			return person;
		}else if(responseEntity.getStatusCode() == HttpStatus.OK){
			return null;
		}
		return null;
	}
}
