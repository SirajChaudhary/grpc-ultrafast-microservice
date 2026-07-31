package com.sirajchaudhary.employeedirectory.exception;

import io.grpc.Status;
import org.springframework.grpc.server.advice.GrpcExceptionHandler;
import org.springframework.grpc.server.advice.GrpcAdvice;

@GrpcAdvice
public class GlobalGrpcExceptionHandler {

    @GrpcExceptionHandler(EmployeeNotFoundException.class)
    public Status handleEmployeeNotFoundException(EmployeeNotFoundException exception) {

        return Status.NOT_FOUND.withDescription(exception.getMessage());
    }

    @GrpcExceptionHandler(EmployeeAlreadyExistsException.class)
    public Status handleEmployeeAlreadyExistsException(EmployeeAlreadyExistsException exception) {

        return Status.ALREADY_EXISTS.withDescription(exception.getMessage());
    }

    @GrpcExceptionHandler(InvalidRequestException.class)
    public Status handleInvalidRequestException(InvalidRequestException exception) {

        return Status.INVALID_ARGUMENT.withDescription(exception.getMessage());
    }

    @GrpcExceptionHandler(Exception.class)
    public Status handleException(Exception exception) {

        return Status.INTERNAL.withDescription(exception.getMessage());
    }
}