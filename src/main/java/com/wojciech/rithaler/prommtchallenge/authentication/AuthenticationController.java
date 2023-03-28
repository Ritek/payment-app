package com.wojciech.rithaler.prommtchallenge.authentication;

import com.wojciech.rithaler.prommtchallenge.customer.CustomerDetailsService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
class AuthenticationController {
    private AuthenticationManager authenticationManager;
    private CustomerDetailsService customerDetailsService;

    @PostMapping("/login")
    public ResponseEntity<UserDetails> login(@Valid @RequestBody LoginDto loginDto, HttpSession session) {
        final Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );

        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(auth);

        UserDetails userDetails = customerDetailsService.loadUserByUsername(loginDto.getEmail());
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, context);

        return ResponseEntity.ok(userDetails);
    }


}
