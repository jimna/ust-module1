package com.ust.person.controller;

import com.google.gson.Gson;
import com.ust.person.model.Address;
import com.ust.person.model.Person;
import com.ust.person.service.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

//@RunWith(SpringRunner.class)
//@WebMvcTest
@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private MockMvc mock;

    @MockBean
    private PersonService service;

    @InjectMocks
    private PersonController controller;

    private Person person;
    String dob;
    List<Address> address = new ArrayList<Address>();

    public void setAddress(List<Address> address) {
    	 address.add(new Address("Munnar Road","Munnar","Kerala",263656));
    }

    @BeforeEach
    public void setUp() {
    	 person=new Person(11,"Jesna","Bivera", "12-12-2015",address,"01-10-21");

    }

    @Test
    void deleteData() throws Exception {

        when(service.deletePerson(person.getId())).thenReturn(true);
        mock.perform(MockMvcRequestBuilders.put("/api/person/manipulate/11/delete").contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(person))).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateData() throws Exception {
        when(service.updatePerson(person, person.getId())).thenReturn(person);
        mock.perform(MockMvcRequestBuilders.put("/api/person/manipulate/11/update").contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(person))).andExpect(MockMvcResultMatchers.status().isOk());

    }
}

















//    @Test
//    void addPersonTest() throws Exception {
//        //person=new Person(11,"Albert","shibu", "12-12-1998",address,"12-12-21");
//        when(service.addPerson(person)).thenReturn("Created");
//        mock.perform(MockMvcRequestBuilders.post("/api/person/add").contentType(MediaType.APPLICATION_JSON)
//                .content(new Gson().toJson(person))).andExpect(MockMvcResultMatchers.status().isCreated());
//
//    }

