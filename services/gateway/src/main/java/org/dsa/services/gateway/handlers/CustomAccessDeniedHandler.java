package org.dsa.services.gateway.handlers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException, ServletException {

        log.info("request URL: {}", request.getRequestURL());
        log.info("Access denied: {}", HttpStatus.FORBIDDEN);

        //Set response status to 403 Unauthorized
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        // Create JSON response
        String jsonResponse = new ObjectMapper().writeValueAsString(
                Map.of(
                        "error",
                        HttpStatus.FORBIDDEN.getReasonPhrase(),
                        "message",
                        accessDeniedException.getMessage()
                )
        );

        // Write the response body
        response.getWriter().write(jsonResponse);
    }
}
