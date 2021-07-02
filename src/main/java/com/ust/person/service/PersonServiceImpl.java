package com.ust.person.service;

import com.ust.person.exception.UserAlreadyExistsException;
import com.ust.person.exception.UserNotFoundException;
import com.ust.person.model.Person;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private RestTemplate restTemplate;
	@Value("${POSTGETPERSON.url}")
	private String POST_GET_PERSON_URI;

	@Value("${POSTADDPERSON.url}")
	private String POST_ADD_PERSON_URI;

	@Override
	public String addPerson(Person person) throws UserAlreadyExistsException {
		RestTemplate restTemplate = new RestTemplate();
		RestTemplate restTemplate1 = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		String body = "{}";
		HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);

		String id = "" + person.getId() + "";
		ResponseEntity<String> responseEntity = restTemplate1.exchange(POST_GET_PERSON_URI + id, HttpMethod.GET,
				requestEntity, String.class);

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			ResponseEntity<String> response = restTemplate.postForEntity(POST_ADD_PERSON_URI, person, String.class);
			return response.getBody();
		}
		if (responseEntity.getStatusCode() == HttpStatus.ALREADY_REPORTED) {
			return "person already exist";
		}
		return null;
	}

	@Value("${PUTUPDATEPERSON.url}")
	private String PUT_UPDATE_PERSON_URI;

	@Override
	public Person updatePerson(Person person, Integer id) throws UserNotFoundException {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("id", id);
		RestTemplate restTemplate1 = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		String body = "{}";
		HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);
		ResponseEntity<String> responseEntity = restTemplate1.exchange(POST_GET_PERSON_URI + id, HttpMethod.GET,
				requestEntity, String.class);
		if (responseEntity.getStatusCode() == HttpStatus.ALREADY_REPORTED) {
			restTemplate.put(PUT_UPDATE_PERSON_URI, person, params);
			return person;
		}
		return null;
	}

	@Value("${PUTDELETEPERSON.url}")
	private String PUT_DELETE_PERSON_URI;

	@Override
	public boolean deletePerson(Integer id) throws UserNotFoundException {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("id", id);
		RestTemplate restTemplate1 = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		String body = "{}";
		HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);
		ResponseEntity<String> responseEntity = restTemplate1.exchange(POST_GET_PERSON_URI + id, HttpMethod.GET,
				requestEntity, String.class);
		if (responseEntity.getStatusCode() == HttpStatus.ALREADY_REPORTED) {
			restTemplate.put(PUT_DELETE_PERSON_URI,Person.class, params);
			return true;
		}
		return false;
	}
}
