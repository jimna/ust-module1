package com.ust.person.exception;

public class UserAlreadyExistsException extends  Exception{

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
