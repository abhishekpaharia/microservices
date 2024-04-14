package com.gmail.abhipaharia12.accounts.exception;

public class CustomerAlreadyExistsException extends RuntimeException{
    
    public CustomerAlreadyExistsException(String message){
        super(message);
    }
}
