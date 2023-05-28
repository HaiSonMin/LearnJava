package com.haison.SpringbootSearchRestAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponEntityDetail> handlerExceptionProductNotFound(ProductNotFoundException exc,
                                                                                    WebRequest webRequest) {
        ResponEntityDetail responseExc = new ResponEntityDetail();
        responseExc.setStatus(HttpStatus.NOT_FOUND.value());
        responseExc.setMessage(exc.getMessage());
        responseExc.setPath(webRequest.getDescription(false));
        responseExc.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<>(responseExc,HttpStatus.NOT_FOUND);
    }
}
