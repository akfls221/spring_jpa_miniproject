package com.example.spring_jpa_miniproject.exception;

public class NotEnoughStockException extends Exception{

    private String message;

    private int status;

    public NotEnoughStockException(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
