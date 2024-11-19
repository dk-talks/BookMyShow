package com.dk.bookmyshow.exceptions;

public class ShowAlreadyStartedException extends Exception{

    private String message;

    public ShowAlreadyStartedException(String message) {
        super(message);
    }

}
