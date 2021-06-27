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

    private final String POST_ADD_PERSON = "http://localhost:8088/person/insert";
    private final String UPDATE_PERSON_BY_ID = "http://localhost:8088/person/update/{id}";
    private final String DELETE_PERSON_BY_ID ="http://localhost:8088/person/delete/{id}";

    @Autowired
    WebClient.Builder webClientBuilder;

    @Autowired
    PersonRepository repo;
    @Override
    public Person addPerson(Person person) throws UserAlreadyExistsException {
        if(repo.findById(person.getId()).isPresent()){
            throw new UserAlreadyExistsException("User already exist");
        }
        return repo.save(person);
//        return webClientBuilder.build().post().uri(POST_ADD_PERSON).bodyValue(person).retrieve()
//                .bodyToMono(Person.class).block();
    }

    @Override
    public Person deletePerson(Long id) throws UserNotFoundException {
        //return repo.deleteById(id);
        return webClientBuilder.build().put().uri(DELETE_PERSON_BY_ID).bodyValue(id).retrieve()
                .bodyToMono(Person.class).block();
    }

    @Override
    public Person updatePerson(Long id) throws UserNotFoundException {
        return webClientBuilder.build().put().uri(UPDATE_PERSON_BY_ID).bodyValue(id).retrieve()
                .bodyToMono(Person.class).block();
    }
}
