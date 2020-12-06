package com.ereport.master.config.security.services;

import com.ereport.master.config.security.models.responses.AuthResponse;
import com.ereport.master.exceptions.ServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface TokenService {

    AuthResponse login(String login, String password) throws ServiceException;

    void checkAuth(String token) throws ServiceException;

    UsernamePasswordAuthenticationToken createUser(String username);
}
