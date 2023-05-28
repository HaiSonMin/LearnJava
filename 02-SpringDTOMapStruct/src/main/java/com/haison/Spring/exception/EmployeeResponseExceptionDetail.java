package com.haison.Spring.exception;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseExceptionDetail {
    private int status;
    private String path;
    private String message;
    private String errorCode;
    private LocalDateTime timeStamp;

}
