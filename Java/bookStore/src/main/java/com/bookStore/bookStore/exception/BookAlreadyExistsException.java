package com.bookStore.bookStore.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BookAlreadyExistsException extends RuntimeException{
    private String message;

    public BookAlreadyExistsException(String message){
        super(message);
        this.message = message;
    }
}
