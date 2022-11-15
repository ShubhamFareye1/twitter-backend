package com.groupC.twitter.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(HttpStatus notFound, String errMsg){
        super(errMsg);
    }
}
