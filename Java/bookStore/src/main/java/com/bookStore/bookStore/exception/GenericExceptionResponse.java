package com.bookStore.bookStore.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GenericExceptionResponse {
    private int status;
    private String message;
}
