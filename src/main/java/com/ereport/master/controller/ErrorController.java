package com.ereport.master.controller;

import com.ereport.master.exceptions.ErrorCode;
import com.ereport.master.exceptions.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ErrorController extends BaseController {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<?> errorHandler(ServiceException exception) {
        Map<String, Object> map = new HashMap();
        map.put("message", exception.getMessage());
        map.put("errorCode", exception.getErrorCode());
        return buildResponse(map, exception.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> commonHandler(Exception exception) {
        Map<String, Object> map = new HashMap();
        map.put("message", exception.getMessage());
        map.put("errorCode", ErrorCode.SYSTEM_ERROR);
        return buildResponse(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
