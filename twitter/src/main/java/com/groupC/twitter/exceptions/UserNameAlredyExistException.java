package com.groupC.twitter.exceptions;

public class UserNameAlredyExistException extends RuntimeException{
    public UserNameAlredyExistException(String msg){
        super(msg);
    }
}
