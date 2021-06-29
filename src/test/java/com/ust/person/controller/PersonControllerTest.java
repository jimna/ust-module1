package com.ust.person.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ust.person.exception.UserAlreadyExistsException;
import com.ust.person.model.Address;
import com.ust.person.model.Person;
import com.ust.person.service.PersonService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    PersonService service;
    @InjectMocks
    PersonController controller;
    private Person person;
    Date dob;
    List<Address> address=new ArrayList<Address>();

    public void setAddress(List<Address> address) {
        address.add(new Address("MG Road","Banglore","Karnataka",123456));
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        String date="12-12-1998";

        {
            try {
                dob = new SimpleDateFormat("dd-MM-yyyy").parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        Address add=new Address("MG Road","Banglore","Karnataka",123456);
        person=new Person(11,"Albert","shibu",dob,address );

    }
    @Test
    void addPersonTest() throws Exception {
        Assertions.assertThrows(NullPointerException.class, () -> {
            when(service.addPerson(person)).thenThrow(UserAlreadyExistsException.class);
            mockMvc.perform(post("/person/insert").contentType(MediaType.APPLICATION_JSON).content(asJsonString(person)))
                    .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        });

    }

    @Test
    void deleteData(){
        Assertions.assertThrows(NullPointerException.class, () -> {
            when(service.deletePerson(person.getId())).thenReturn(true);
            mockMvc.perform(post("/person/manipulate").contentType(MediaType.APPLICATION_JSON).content(asJsonString(person)))
                    .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        });
    }

    @Test
    void updateData(){
        Assertions.assertThrows(NullPointerException.class, () -> {
            when(service.deletePerson(person.getId())).thenReturn(true);
            mockMvc.perform(post("/person/manipulate").contentType(MediaType.APPLICATION_JSON).content(asJsonString(person)))
                    .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        });
    }



    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}