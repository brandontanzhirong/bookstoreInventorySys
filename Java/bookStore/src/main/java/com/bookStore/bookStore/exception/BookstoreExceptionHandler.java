package com.bookStore.bookStore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookstoreExceptionHandler {
    @ExceptionHandler(value = BookNotFoundException.class)
    public ResponseEntity handleBookNotFound(BookNotFoundException e){
        return new ResponseEntity<>(new GenericExceptionResponse(HttpStatus.NOT_FOUND.value(), "Book Not Found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BookAlreadyExistsException.class)
    public ResponseEntity handleBookNotFound(BookAlreadyExistsException e){
        return new ResponseEntity<>(new GenericExceptionResponse(HttpStatus.CONFLICT.value(), "Book Already Exist"), HttpStatus.CONFLICT);
    }

}
