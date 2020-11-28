package com.ereport.master.exceptions;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceException extends Exception{
    protected String message;
    protected ErrorCode errorCode;
    protected HttpStatus httpStatus;
}
