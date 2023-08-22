package com.bookStore.bookStore.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class ConstraintViolationExceptionResponse {
    private final String message;
    Set<Object> violations = new LinkedHashSet<>();
}
