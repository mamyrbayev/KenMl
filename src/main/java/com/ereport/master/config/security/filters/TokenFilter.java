package com.ereport.master.config.security.filters;

import com.ereport.master.config.security.services.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class TokenFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final ObjectMapper mapper;
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws ServletException, IOException {
        try {
            String authHeader = req.getHeader(HttpHeaders.AUTHORIZATION);
            if (Objects.nonNull(authHeader)) {
                tokenService.checkAuth(authHeader);
                UsernamePasswordAuthenticationToken user = tokenService.createUser(authHeader);
                if (Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
                    SecurityContextHolder.getContext().setAuthentication(user);
                }
            }
            filterChain.doFilter(req, resp);
        } catch (Exception e) {
            resp.setStatus(HttpStatus.UNAUTHORIZED.value());
            resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
            PrintWriter writer = resp.getWriter();
            writer.write(mapper.writeValueAsString(Collections.singletonMap("errors", e.getMessage())));
        }
    }
}
