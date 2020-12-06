package com.ereport.master.config.security.services.impl;

import com.ereport.master.config.security.models.responses.AuthResponse;
import com.ereport.master.config.security.services.TokenService;
import com.ereport.master.exceptions.ErrorCode;
import com.ereport.master.exceptions.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final RestTemplate restTemplate;

    @Override
    public AuthResponse login(String login, String password) throws ServiceException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "password");
        map.add("username", login);
        map.add("password", password);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
        try {
            ResponseEntity<AuthResponse> response =
                    restTemplate.exchange("http://devbd.genro.kz/constructorapi/Token",
                            HttpMethod.POST,
                            entity,
                            AuthResponse.class);
            return response.getBody();
        } catch (Exception e) {
            throw ServiceException.builder()
                    .httpStatus(HttpStatus.UNAUTHORIZED)
                    .message("Invalid login or password")
                    .errorCode(ErrorCode.ACCESS_DENIED)
                    .build();
        }
    }

    @Override
    public void checkAuth(String token) throws ServiceException {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, token);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
        try {
            ResponseEntity<AuthResponse> response =
                    restTemplate.exchange("http://devbd.genro.kz/widgetapi/Users/CheckToken",
                            HttpMethod.GET,
                            entity,
                            AuthResponse.class);
            Objects.nonNull(response.getBody());
        } catch (Exception e) {
            throw ServiceException.builder()
                    .httpStatus(HttpStatus.UNAUTHORIZED)
                    .message(e.getMessage())
                    .errorCode(ErrorCode.ACCESS_DENIED)
                    .build();
        }
    }

    @Override
    public UsernamePasswordAuthenticationToken createUser(String username) {
        return new UsernamePasswordAuthenticationToken(username, username);
    }
}
