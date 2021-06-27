package com.ust.person.model;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;



class PersonTest {
    Address add=new Address("abc","cde","fdg",123456);
    Person person=new Person(11,"Albert","shibu","12-12-1998",add);

    @Test
    public void PersonTest() {
        new BeanTester().testBean(Person.class);
    }

}