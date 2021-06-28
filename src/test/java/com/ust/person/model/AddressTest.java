package com.ust.person.model;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    public void AddressTest() {
        new BeanTester().testBean(Address.class);
    }

}