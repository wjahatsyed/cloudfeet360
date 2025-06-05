package com.cloudfleet360.auth_service.service;

import com.cloudfleet360.auth_service.dto.AuthRequest;
import com.cloudfleet360.auth_service.dto.RegisterRequest;
import com.cloudfleet360.auth_service.entity.Role;
import com.cloudfleet360.auth_service.entity.User;
import com.cloudfleet360.auth_service.repository.UserRepository;
import com.cloudfleet360.auth_service.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldRegisterUserAndReturnJwtToken() {
        RegisterRequest request = new RegisterRequest();
        request.setName("Test User");
        request.setEmail("test@example.com");
        request.setPassword("password");
        request.setRole(Role.USER);

        User mockUser = User.builder()
                .id(1L)
                .name("Test User")
                .email("test@example.com")
                .password("encoded")
                .role(Role.USER)
                .build();

        when(passwordEncoder.encode("password")).thenReturn("encoded");
        when(userRepository.save(any(User.class))).thenReturn(mockUser);
        when(jwtService.generateToken("test@example.com")).thenReturn("mock-jwt-token");

        var response = authService.register(request);

        assertEquals("mock-jwt-token", response.getToken());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void shouldAuthenticateAndReturnJwtToken() {
        AuthRequest request = new AuthRequest();
        request.setEmail("test@example.com");
        request.setPassword("password");

        User user = User.builder()
                .id(1L)
                .email("test@example.com")
                .password("encoded")
                .role(Role.USER)
                .build();

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(new UsernamePasswordAuthenticationToken("test@example.com", "password"));

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(jwtService.generateToken("test@example.com")).thenReturn("mock-jwt-token");

        var response = authService.authenticate(request);

        assertEquals("mock-jwt-token", response.getToken());
        verify(jwtService, times(1)).generateToken("test@example.com");
    }

}
