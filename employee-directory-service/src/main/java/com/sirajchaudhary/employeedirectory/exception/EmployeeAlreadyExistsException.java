package com.sirajchaudhary.employeedirectory.exception;

public class EmployeeAlreadyExistsException extends RuntimeException {

    public EmployeeAlreadyExistsException(String field, String value) {
        super("Employee already exists with " + field + ": " + value);
    }
}