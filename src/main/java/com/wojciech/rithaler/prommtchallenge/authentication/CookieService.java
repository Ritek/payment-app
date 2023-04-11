package com.wojciech.rithaler.prommtchallenge.authentication;

import jakarta.servlet.http.Cookie;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

/*
@AllArgsConstructor
public class CookieService {
    private static final String AUTHORIZATION_COOKIE_NAME = "Authorization";
    private final JwtService jwtService;

    public Cookie createAuthCookie(UserDetails userDetails) {
        String jwtToken = jwtService.generateToken(userDetails);

        Cookie cookie = new Cookie(AUTHORIZATION_COOKIE_NAME, jwtToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/api");

        return cookie;
    }

    public Cookie createInvalidatedAuthCookie() {
        Cookie cookie = new Cookie(AUTHORIZATION_COOKIE_NAME, null);
        cookie.setHttpOnly(true);
        cookie.setPath("/api");
        cookie.setMaxAge(0);

        return cookie;
    }
}
*/
