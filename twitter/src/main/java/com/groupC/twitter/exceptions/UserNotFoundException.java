package com.groupC.twitter.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String errMsg){
        super(errMsg);
    }
}
