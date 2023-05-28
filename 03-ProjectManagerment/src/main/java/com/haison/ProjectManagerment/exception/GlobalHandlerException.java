package com.haison.ProjectManagerment.exception;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseExceptionDetails> excNotFound(TodoNotFoundException exc, WebRequest webRequest) {
        ResponseExceptionDetails response = new ResponseExceptionDetails();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(exc.getMessage());
        response.setError("NOT_FOUND");
        response.setPath(webRequest.getDescription(false));
        response.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ResponseExceptionDetails> excNotFound(UsernameNotFoundException exc, WebRequest webRequest) {
        ResponseExceptionDetails response = new ResponseExceptionDetails();
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setMessage(exc.getMessage());
        response.setError("NOT_FOUND_USER");
        response.setPath(webRequest.getDescription(false));
        response.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
    }
}
