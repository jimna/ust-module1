package com.ust.person.service;

import com.ust.person.exception.UserAlreadyExistsException;
import com.ust.person.exception.UserNotFoundException;
import com.ust.person.model.Address;
import com.ust.person.model.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
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
        String date="12-12-1998";
        {
            try {
                dob = new SimpleDateFormat("dd-MM-yyyy").parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        Address add=new Address("MG Road","Banglore","Karnataka",123456);
        person=new Person(11,"Albert","shibu",dob,address);

    }

    @Test
    void addPersonTest(){
            if (person.getId() == 11) {
                when(restTemplate.getForEntity("http://localhost:8088/person/{id}", Person.class)).thenReturn(new ResponseEntity(person, HttpStatus.FORBIDDEN));
            } else {
                when(restTemplate.getForEntity("http://localhost:8088/person/insert", Person.class)).thenReturn(new ResponseEntity(person, HttpStatus.OK));
            }
            Person fetchPerson = new Person();
            fetchPerson.setId(11);
            Assert.assertEquals(fetchPerson.getId(), person.getId());
    }
     @Test
     void updatePerson() throws UserNotFoundException {
             when(restTemplate.getForEntity("http://localhost:8088/person/update/{id}", Person.class)).thenReturn(new ResponseEntity(true ,HttpStatus.OK));
             person.setLastName("Ramesh");
             Person fetchPerson = service.updatePerson(11,person);
             Assert.assertEquals(fetchPerson , true);

     }

//    @Test
//    void deletePerson() throws UserNotFoundException {
//            when(restTemplate.getForEntity("http://localhost:8088/person/delete/{id}", Person.class)).thenReturn(new ResponseEntity(true ,HttpStatus.OK)).thenThrow(NullPointerException.class);
//             fetchPerson = service.deletePerson(11);
//            Assert.assertEquals(fetchPerson , true);
//
//    }


}