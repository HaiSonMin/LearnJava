package com.haison.Spring.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalHandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class) // Handler Exception for Employee Not Found
    public ResponseEntity<EmployeeResponseExceptionDetail> handlerExcNotFound(EmployeeNotFoundException exc,
                                                                              WebRequest webRequest) {

        EmployeeResponseExceptionDetail emplExc = new EmployeeResponseExceptionDetail();
        emplExc.setStatus(HttpStatus.NOT_FOUND.value());
        emplExc.setMessage(exc.getMessage());
        emplExc.setErrorCode("USER_NOT_FOUND");
        emplExc.setTimeStamp(LocalDateTime.now());
        emplExc.setPath(webRequest.getDescription(false));

        return new ResponseEntity<>(emplExc,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class) // Handler Exception for Employee Email
    public ResponseEntity<EmployeeResponseExceptionDetail> handlerExcEmailExist(EmailAlreadyExistsException exc,
                                                                                WebRequest webRequest) {
        EmployeeResponseExceptionDetail emplExc = new EmployeeResponseExceptionDetail();
        emplExc.setStatus(HttpStatus.BAD_REQUEST.value());
        emplExc.setMessage(exc.getMessage());
        emplExc.setErrorCode("EMPLOYEE_EMAIL_ALREADY_EXISTS");
        emplExc.setTimeStamp(LocalDateTime.now());
        emplExc.setPath(webRequest.getDescription(false));

        return new ResponseEntity<>(emplExc,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class) // Handler Global Exception(All Exception go into here exception 2 exc above)
    public ResponseEntity<EmployeeResponseExceptionDetail> handlerGlobalException(Exception exception,
                                                                                  WebRequest webRequest) {
        EmployeeResponseExceptionDetail emplExc = new EmployeeResponseExceptionDetail();
        emplExc.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        emplExc.setMessage(exception.getMessage());
        emplExc.setErrorCode("INTERNAL_SERVER_ERROR");
        emplExc.setTimeStamp(LocalDateTime.now());
        emplExc.setPath(webRequest.getDescription(false));

        return new ResponseEntity<>(emplExc,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handler Validation Annotation
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exc,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String, String> errors = new HashMap<>();

        List<ObjectError> errorList = exc.getBindingResult().getAllErrors();

        errorList.forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String messageError = error.getDefaultMessage();
            errors.put(fieldName,messageError);
        });

        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }

}
