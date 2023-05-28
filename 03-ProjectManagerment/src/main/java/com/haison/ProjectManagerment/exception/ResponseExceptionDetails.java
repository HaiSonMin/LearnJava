package com.haison.ProjectManagerment.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseExceptionDetails {

    private int status;
    private String message;
    private String path;

    private String error;
    private LocalDateTime timeStamp;
}
