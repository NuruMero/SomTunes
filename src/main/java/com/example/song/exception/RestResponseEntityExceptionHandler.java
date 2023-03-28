package com.example.song.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        String body = "ERROR: Illegal argument or state introduced in the API.";
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = {DuplicatedUniqueObjectException.class})
    public ResponseEntity<Object> duplicateUniqueObjectException(DuplicatedUniqueObjectException ex, WebRequest request) {
        return handleExceptionInternal(ex, "ERROR#"+ex.getErrorCode()+": " +ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
