package com.ust.person.model;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


class PersonTest {

    @Test
    public void PersonTest() {
        new BeanTester().testBean(Person.class);
    }

}