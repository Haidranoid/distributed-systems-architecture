package org.dsa.services.authenticationservice.services.impl;

import org.dsa.services.authenticationservice.common.dtos.AuthAccountDto;
import org.dsa.services.authenticationservice.common.dtos.AuthResponseDto;
import org.dsa.services.authenticationservice.common.dtos.LoginDto;
import org.dsa.services.authenticationservice.common.dtos.SignupDto;
import org.dsa.services.authenticationservice.common.utils.JwtSignerService;
import org.dsa.services.authenticationservice.mappers.impl.AuthenticationMapperImpl;
import org.dsa.services.authenticationservice.common.properties.Endpoints;
import org.dsa.services.authenticationservice.repositories.TokensRepository;
import org.dsa.services.authenticationservice.services.AuthenticationService;
import org.dsa.services.authenticationservice.common.constants.TokenType;
import org.dsa.services.authenticationservice.common.entities.TokenEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsa.services.core.servicesstarter.constants.Permission;
import org.dsa.services.core.servicesstarter.exceptions.InvalidCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtSignerService jwtSignerService;
    private final RestTemplate restTemplate;
    private final Endpoints endpoints;
    private final TokensRepository tokensRepository;
    private final AuthenticationMapperImpl authMapper;

    @Override
    public AuthResponseDto login(LoginDto loginDto) {

        var accountAuthenticated = restTemplate.postForObject(
                endpoints.accountsServiceInternalEndpoint() + "/authenticate-login",
                loginDto,
                AuthAccountDto.class
        );

        if (accountAuthenticated == null) {
            throw new InvalidCredentialsException();
        }

        var roles = List.of(accountAuthenticated.role().name());

        var scopes = accountAuthenticated.role()
                .getPermissions()
                .stream()
                .map(Permission::getPermission)
                .toList();

        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);
        claims.put("scope", String.join(" ", scopes));

        var accessToken = jwtSignerService.generateAccessToken(
                accountAuthenticated.username(),
                claims
        );

        var refreshToken = jwtSignerService.generateRefreshToken(
                accountAuthenticated.username()
        );

        return authMapper.toAuthResponseDto(accountAuthenticated, accessToken, refreshToken);
    }

    @Override
    public AuthResponseDto signup(SignupDto signupDto) {

        var accountCreated = restTemplate.postForObject(
                endpoints.accountsServiceInternalEndpoint(),
                signupDto,
                AuthAccountDto.class
        );

        if (accountCreated == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request body");
        }

        var accessToken = jwtSignerService.generateAccessToken(accountCreated.username(), new HashMap<>());
        var refreshToken = jwtSignerService.generateRefreshToken(accountCreated.username());

        saveAccountToken(accountCreated.id(), accessToken);

        return authMapper.toAuthResponseDto(accountCreated, accessToken, refreshToken);
    }

    /*
    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        final String refreshToken = authHeader.split(" ")[1].trim();
        if (jwtService.isExpired(refreshToken)) {
            return;
        }

        final String username = jwtService.decode(refreshToken).getSubject();
        if (username == null) {
            return;
        }

        // var dbAccount = accountHttpClient.find(username);

        var account = restTemplate.getForObject(
                endpoints.accountServiceEndpoint() + "/" + username,
                AuthAccountDto.class
        );

        if (account == null) {
            return;
        }

        if (!(username.equals(account.username()))) {
            return;
        }

        var accessToken = jwtService.generateAccessToken(account.username());

        revokeAllAccountTokensById(account.id());
        saveAccountToken(account.id(), accessToken);

        var refreshTokenRequestResponse = AuthResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .account(account)
                .build();

        new ObjectMapper().writeValue(response.getOutputStream(), refreshTokenRequestResponse);
    }

    */

    private void saveAccountToken(Long accountId, String jwtToken) {
        var token = TokenEntity.builder()
                .accountId(accountId)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();

        tokensRepository.save(token);
    }

    /*
    private void revokeAllAccountTokensById(Long accountId) {
        var validUserTokens = tokenRepository.findAllValidTokensByAccountId(accountId);

        if (validUserTokens.isEmpty()) {
            return;
        }

        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });

        tokenRepository.saveAll(validUserTokens);
    }

     */
}
