package com.groupC.twitter.exceptions;

import org.springframework.http.HttpStatus;

public class UserNameAlredyExistException extends RuntimeException{
    public UserNameAlredyExistException(HttpStatus badRequest, String msg){
        super(msg);
    }
}
