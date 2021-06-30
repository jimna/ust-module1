package com.ust.person.exception;


import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

import static org.junit.jupiter.api.Assertions.*;

class ErrorDetailsTest {

    @Test
    public void ErrorDetailsTest() {
        new BeanTester().testBean(ErrorDetails.class);
    }

}