package com.ust.person.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserAlreadyExistsExceptionTest {

    @Test
    void userAlreadyExistException(){
        UserAlreadyExistsException exception = assertThrows(
                UserAlreadyExistsException.class,
                () -> { throw new UserAlreadyExistsException("User already exist"); }
        );

        assertEquals("User already exist", exception.getMessage());
    }

}