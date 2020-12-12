package com.ereport.master.config.security.controllers;

import com.ereport.master.config.security.models.requests.LoginRequest;
import com.ereport.master.config.security.services.TokenService;
import com.ereport.master.exceptions.ServiceException;
import com.ereport.master.kenML.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController extends BaseController {

    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest r) throws ServiceException {
        return this.buildResponse(this.tokenService.login(r.getLogin(), r.getPassword()), HttpStatus.OK);
    }

}
