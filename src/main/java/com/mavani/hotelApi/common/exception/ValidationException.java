package com.mavani.hotelApi.common.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String msg){
        super(msg);
    };
}
