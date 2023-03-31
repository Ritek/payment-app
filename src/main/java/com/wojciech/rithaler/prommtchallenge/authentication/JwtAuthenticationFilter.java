package com.wojciech.rithaler.prommtchallenge.authentication;

import com.wojciech.rithaler.prommtchallenge.customer.CustomerDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final CustomerDetailsService customerDetailsService;

    private Optional<Cookie> findAuthorizationCookie(Cookie[] cookies) {
        if (cookies == null) return Optional.empty();
        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals("Authorization"))
                .findFirst();
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        Optional<Cookie> authCookie = findAuthorizationCookie(request.getCookies());
        if (authCookie.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        final String jwt = authCookie.get().getValue();

        if (jwtService.isTokenValid(jwt)) {
            final String userEmail = jwtService.extractUsername(jwt);
            UserDetails userDetails = customerDetailsService.loadUserByUsername(userEmail);

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            );

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }
}
