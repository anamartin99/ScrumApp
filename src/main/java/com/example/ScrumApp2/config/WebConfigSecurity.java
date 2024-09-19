package com.example.ScrumApp2.config;

import com.example.ScrumApp2.jwt.AuthTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
                                .requestMatchers(HttpMethod.POST,"/api/auth/login").permitAll()
                                .requestMatchers(HttpMethod.POST,"/api/auth/register").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.GET,"/api/users").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.GET,"/api/users/{id}").hasAnyAuthority("ADMIN","USER")
                                .requestMatchers(HttpMethod.POST,"/api/users").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.PUT,"/api/{id}").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"/api/users/{id}").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.GET,"/api/projects").permitAll()
                                .requestMatchers(HttpMethod.POST,"/api/projects").hasAnyAuthority("MANAGER","ADMIN")
                                .requestMatchers(HttpMethod.PUT,"/api/projects/{id}").hasAnyAuthority("MANAGER","ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"/api/projects/{id}").hasAnyAuthority("MANAGER","ADMIN")
                                .requestMatchers(HttpMethod.GET,"/api/tasks").hasAnyAuthority("MANAGER","ADMIN")
                                .requestMatchers(HttpMethod.GET,"/api/tasks/{id}").hasAnyAuthority("USER")
                                .requestMatchers(HttpMethod.POST,"/api/tasks").hasAuthority("MANAGER")
                                .requestMatchers(HttpMethod.PUT,"/api/tasks/{id}").hasAnyAuthority("USER")
                                .requestMatchers(HttpMethod.DELETE,"/api/tasks/{id}").hasAuthority("MANAGER")
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