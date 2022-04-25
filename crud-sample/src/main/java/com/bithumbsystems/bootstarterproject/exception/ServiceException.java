package com.bithumbsystems.bootstarterproject.exception;

import lombok.Data;

@Data
public class ServiceException extends RuntimeException{
    private int statusCode;
    public ServiceException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
