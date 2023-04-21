package com.uco.TrafficAgent.infrastructure.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.concurrent.ConcurrentHashMap;

@ControllerAdvice
public class ErrorHandler {
    private static final Logger LOG = LoggerFactory.getLogger(ErrorHandler.class);
    private static final String ERROR_CONTACT_ADMIN = "Error contact admin";
    private static final ConcurrentHashMap<String, Integer> STATUS = new ConcurrentHashMap<>();

    public ErrorHandler() {
        STATUS.put(IllegalStateException.class.getSimpleName(), HttpStatus.NOT_FOUND.value());
        STATUS.put(IllegalArgumentException.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Error> handler(Exception exception) {
        ResponseEntity<Error> response;
        String exceptionName = exception.getClass().getSimpleName();
        String message = exception.getMessage();
        Integer status = STATUS.get(exceptionName);

        if (status != null) {
            Error error = new Error(exceptionName, message);
            response = new ResponseEntity<>(error, HttpStatus.valueOf(status));
        } else {
            LOG.error(exceptionName, message);
            Error error = new Error(exceptionName, ERROR_CONTACT_ADMIN);
            response = new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

}
