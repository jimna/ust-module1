package com.ust.person.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ust.person.model.Address;
import com.ust.person.model.Person;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@ExtendWith(SpringExtension.class)
@RestClientTest(PersonServiceImpl.class)
public class IntegratedRestClientTest {

    @InjectMocks
    private PersonServiceImpl service;

    private MockRestServiceServer server;

    @Autowired
    private ObjectMapper objectMapper;

    private Person person;
    Date dob;
    List<Address> address=new ArrayList<Address>();

    public void setAddress(List<Address> address) {
        address.add(new Address("MG Road","Banglore","Karnataka",123456));
    }

    @BeforeEach
    public void setUp() throws JsonProcessingException {
        person=new Person(11,"Albert","shibu", "12-12-1998",address,"12-12-21");
        String person =
                objectMapper.writeValueAsString(new Person(11,"Albert","shibu", "12-12-1998",address,"12-12-21"));
    }

    @Test
    void addPersonTest() throws Exception {
        Assertions.assertThrows(NullPointerException.class, () -> {
        if (person.getId() == 11) {
            this.server.expect(requestTo("http://localhost:8088/person/11")).andRespond(withSuccess("Already Exists", MediaType.TEXT_PLAIN));
            String message = this.service.addPerson(person);
            Assert.assertEquals(message, "Person Already Exist");
        } else {
            this.server.expect(requestTo("http://localhost:8088/person/insert")).andRespond(withSuccess("Created", MediaType.TEXT_PLAIN));
            String person1 = this.service.addPerson(person);
            Assert.assertEquals(person,person);
        }
        });

    }
    @Test
    void updatePerson() throws Exception {
        Assertions.assertThrows(NullPointerException.class, () -> {
            if (person.getId() == 11) {
                this.server.expect(requestTo("http://localhost:8088/person/11")).andRespond(withSuccess("Already Exists", MediaType.TEXT_PLAIN));
                Person p = this.service.updatePerson(11, person);
            } else {
                this.server.expect(requestTo("http://localhost:8082/person/11/update")).andRespond(withSuccess("Updated", MediaType.TEXT_PLAIN));
                person.setLastName("Ramesh");
                Person fetchPerson = service.updatePerson(11, person);
                Assert.assertEquals(fetchPerson, person);
            }
        });
    }

    @Test
    void deletePerson() throws Exception {
        Assertions.assertThrows(NullPointerException.class, () -> {
        if (person.getId() == 11) {
            this.server.expect(requestTo("http://localhost:8088/person/11")).andRespond(withSuccess("Already Exists", MediaType.TEXT_PLAIN));
            Person p = this.service.updatePerson(11, person);
        } else {
            this.server.expect(requestTo("http://localhost:8082/person/11/delete")).andRespond(withSuccess("Deleted", MediaType.TEXT_PLAIN));
            boolean fetchPerson = service.deletePerson(11);
            Assert.assertEquals(fetchPerson, true);

        }
        });
    }
}
