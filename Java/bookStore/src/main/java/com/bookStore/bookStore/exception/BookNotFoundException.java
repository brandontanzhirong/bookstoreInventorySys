package com.bookStore.bookStore.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BookNotFoundException  extends RuntimeException{
    private String message;

    public BookNotFoundException(String message){
        super(message);
        this.message = message;
    }
}
