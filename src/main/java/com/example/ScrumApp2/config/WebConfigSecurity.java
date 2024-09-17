package com.example.ScrumApp2.config;

import com.example.ScrumApp2.jwt.AuthTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class WebConfigSecurity {
    private final AuthenticationProvider authenticationProvider;
    private final AuthTokenFilter authTokenFilter;

    public WebConfigSecurity(AuthenticationProvider authenticationProvider, AuthTokenFilter authTokenFilter) {
        this.authenticationProvider = authenticationProvider;
        this.authTokenFilter = authTokenFilter;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf ->
                        csrf.disable())
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/api/test/all").permitAll()
                                .requestMatchers("/api/test/user").hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers("/api/test/admin").hasAuthority("ADMIN")
                                .requestMatchers("/api/perseo/courses/post").hasAuthority("ADMIN")
                                .requestMatchers("/api/perseo/courses/put/**").hasAuthority("ADMIN")
                                .requestMatchers("/api/perseo/courses/delete/**").hasAuthority("ADMIN")
                                .requestMatchers("/api/perseo/courses/get").permitAll()
                                .requestMatchers("/api/perseo/workexperiences/get").hasAuthority("USER")
                                .requestMatchers("/api/perseo/workexperiences/put/**").hasAuthority("USER")
                                .requestMatchers("/api/perseo/workexperiences/delete/**").hasAuthority("USER")
                                .requestMatchers("/api/perseo/workexperiences/post").hasAuthority("USER")
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionManager ->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }
}
