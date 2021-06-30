package com.ust.person.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserNotFoundExceptionTest {

    @Test
    void userNotFoundExceptionTest(){
        UserNotFoundException exception = assertThrows(
                UserNotFoundException.class,
                () -> { throw new UserNotFoundException("User Not Found"); }
        );

        assertEquals("User Not Found", exception.getMessage());
    }

}