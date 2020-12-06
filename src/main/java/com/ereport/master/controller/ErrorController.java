package com.ereport.master.controller;

import com.ereport.master.exceptions.ServiceException;
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

}
