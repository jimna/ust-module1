package com.ust.person.model;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;



class PersonTest {

    @Test
    public void PersonTest() {
        new BeanTester().testBean(Person.class);
    }

}