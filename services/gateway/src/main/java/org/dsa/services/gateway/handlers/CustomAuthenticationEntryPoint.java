package org.dsa.services.gateway.handlers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        log.info("request URL: {}", request.getRequestURL());
        log.info("Access denied: {}", HttpStatus.UNAUTHORIZED);

        //Set response status to 401 Unauthorized
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        // Create JSON response
        String jsonResponse = new ObjectMapper().writeValueAsString(
                Map.of(
                        "error",
                        HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                        "message",
                        authException.getMessage()
                )
        );

        // Write the response body
        response.getWriter().write(jsonResponse);
    }
}
