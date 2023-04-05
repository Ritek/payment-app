package com.wojciech.rithaler.prommtchallenge;

import com.wojciech.rithaler.prommtchallenge.authentication.JwtAuthenticationFilter;
import com.wojciech.rithaler.prommtchallenge.customer.CustomerDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    AuthenticationManager authenticationManager(final AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
        http
        // disable basic / servlet authentication
            .csrf().disable()
            .httpBasic().disable()
            .formLogin().disable()
            .logout().deleteCookies("Authorization").permitAll()
            .and()
        // allows H2 console to work
            .headers().frameOptions().disable()
            .and()
        // enable role check for paths
            .authorizeHttpRequests()
            .requestMatchers(new AntPathRequestMatcher("/api/payment/**")).hasAnyAuthority("ADMIN")
            .and()
        // disable authentication for routes
            .authorizeHttpRequests()
            .requestMatchers(new AntPathRequestMatcher("/api/customer/**")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/api/login")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/api/jwt/**")).permitAll()
            .anyRequest().authenticated()

        // JWT token
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
