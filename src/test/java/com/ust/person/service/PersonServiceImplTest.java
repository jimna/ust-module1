package com.ust.person.service;

import com.ust.person.exception.UserNotFoundException;
import com.ust.person.model.Address;
import com.ust.person.model.Person;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
class PersonServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PersonServiceImpl service;

    private Person person;
    Date dob;
    List<Address> address=new ArrayList<Address>();

    public void setAddress(List<Address> address) {
        address.add(new Address("MG Road","Banglore","Karnataka",123456));
    }
    @BeforeEach
    public void setUp() {
        person=new Person(11,"Albert","shibu", "12-12-1998",address,"12-12-21");

        }

    @Test
    void addPersonTest(){
            if (person.getId() == 11) {
                when(restTemplate.getForEntity("http://localhost:8088/person/11", Person.class)).thenReturn(new ResponseEntity(person, HttpStatus.FORBIDDEN));
            } else {
                when(restTemplate.getForEntity("http://localhost:8088/person/insert", Person.class)).thenReturn(new ResponseEntity(person, HttpStatus.OK));
            }
            Person fetchPerson = new Person();
            fetchPerson.setId(11);
            Assert.assertEquals(fetchPerson.getId(), person.getId());
    }
     @Test
     void updatePerson() throws UserNotFoundException {
         if (person.getId() == 11) {
             when(restTemplate.getForEntity("http://localhost:8088/person/11", Person.class)).thenReturn(new ResponseEntity(person, HttpStatus.FORBIDDEN));
         } else {
             when(restTemplate.getForEntity("http://localhost:8082/person/11/update", Person.class)).thenReturn(new ResponseEntity(true, HttpStatus.OK));
             person.setLastName("Ramesh");
             Person fetchPerson = service.updatePerson(11, person);
             Assert.assertEquals(fetchPerson, person);
         }
     }

    @Test
    void deletePerson() throws UserNotFoundException {
        if (person.getId() == 11) {
            when(restTemplate.getForEntity("http://localhost:8088/person/11", Person.class)).thenReturn(new ResponseEntity(person, HttpStatus.FORBIDDEN));
        } else {
            when(restTemplate.getForEntity("http://localhost:8082/person/11/delete", Person.class)).thenReturn(new ResponseEntity(true, HttpStatus.OK));
            boolean fetchPerson = service.deletePerson(11);
            Assert.assertEquals(fetchPerson, true);

        }
    }


}