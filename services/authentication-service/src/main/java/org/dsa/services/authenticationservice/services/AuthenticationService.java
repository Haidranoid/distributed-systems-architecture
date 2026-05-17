package org.dsa.services.authenticationservice.services;

import org.dsa.services.authenticationservice.common.dtos.AuthResponseDto;
import org.dsa.services.authenticationservice.common.dtos.LoginDto;
import org.dsa.services.authenticationservice.common.dtos.SignupDto;

public interface AuthenticationService {
    AuthResponseDto login(LoginDto loginDto);
    AuthResponseDto signup(SignupDto signupDto);
    //void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
