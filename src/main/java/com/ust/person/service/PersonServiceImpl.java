package com.ust.person.service;

import com.ust.person.exception.UserAlreadyExistsException;
import com.ust.person.exception.UserNotFoundException;
import com.ust.person.model.Person;


import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class PersonServiceImpl implements  PersonService{
	
	private final String POST_ADD_PERSON_URI = "http://localhost:8085/person/insert";
	private final String PUT_UPDATE_PERSON_URI = "http://localhost:8085/person/update{id}";
	private final String PUT_DELETE_PERSON_URI = "http://localhost:8085/person/delete{id}";
	
	@Autowired
	WebClient.Builder webClientBuilder;

    @Override
    public Person addPerson(Person person) throws UserAlreadyExistsException {
       
        return webClientBuilder.build().post().uri(POST_ADD_PERSON_URI).bodyValue(person).retrieve()
				.bodyToMono(Person.class).block();
    }

	@Override
	public Person updatePerson(Person person) throws UserNotFoundException {
		try {
			return webClientBuilder.build().post().uri(PUT_UPDATE_PERSON_URI).bodyValue(person).retrieve()
					.bodyToMono(Person.class).block();
		} catch (NoSuchElementException e) {
			throw new UserNotFoundException("Person not found");
		}
	}

	@Override
	public String deletePerson(String id) throws UserNotFoundException {
		return webClientBuilder.build().get().uri(PUT_DELETE_PERSON_URI, id).retrieve()
				.bodyToMono(String.class).block();
		}
}
