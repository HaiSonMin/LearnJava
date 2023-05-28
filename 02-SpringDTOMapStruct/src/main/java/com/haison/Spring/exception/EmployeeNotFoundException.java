package com.haison.Spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// If cant find Entity then into here
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException{

    public EmployeeNotFoundException(String resourceName, String fieldName, Long fieldValue) {
        super(String.format("%s not found with %s: %s",resourceName,fieldName,fieldValue));
    }

//    private String resourceName;
//    private String fieldName;
//    private Long fieldValue;
//
//    public EmployeeNotFoundException(String resourceName, String fieldName, Long fieldValue) {
//        super(String.format("%s not found with %s: %s",resourceName,fieldName,fieldValue));
//        this.resourceName = resourceName;
//        this.fieldName = fieldName;
//        this.fieldValue = fieldValue;
//    }


}
