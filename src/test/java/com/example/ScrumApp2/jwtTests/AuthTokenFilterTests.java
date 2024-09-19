package com.example.ScrumApp2.jwtTests;

import com.example.ScrumApp2.jwt.AuthTokenFilter;
import com.example.ScrumApp2.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthTokenFilterTests {
    @InjectMocks
    private AuthTokenFilter authTokenFilter;

    @Mock
    private JwtService jwtService;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFilter_WithValidToken() throws Exception {
        String token = "valid.token.here";
        String username = "username";
        UserDetails userDetails = mock(UserDetails.class);

        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(jwtService.getUsernameFromToken(token)).thenReturn(username);
        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        when(jwtService.isTokenValid(token, userDetails)).thenReturn(true);

        authTokenFilter.filter(request, response, filterChain); // Use the public method

        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    public void testFilter_WithInvalidToken() throws Exception {
        String token = "invalid.token.here";

        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(jwtService.getUsernameFromToken(token)).thenReturn("username");
        when(userDetailsService.loadUserByUsername(any())).thenThrow(new UsernameNotFoundException("User not found"));

        authTokenFilter.filter(request, response, filterChain); // Use the public method

        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    public void testFilter_NoToken() throws Exception {
        when(request.getHeader("Authorization")).thenReturn(null);

        authTokenFilter.filter(request, response, filterChain); // Use the public method

        verify(filterChain, times(1)).doFilter(request, response);
    }
}