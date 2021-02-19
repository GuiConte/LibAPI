package com.guiconte.exception;

public class InvalidStatusException extends RuntimeException{

    public InvalidStatusException() {
        super("O Status informado é invalido !");
    }
}
