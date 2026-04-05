package com.smartcampus.operationshub.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartcampus.operationshub.auth.dto.LoginRequest;
import com.smartcampus.operationshub.auth.service.AuthenticationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.smartcampus.operationshub.auth.controller.AuthenticationController;
import com.smartcampus.operationshub.security.AuthEntryPointJwt;
import com.smartcampus.operationshub.security.CurrentUserContext;
import com.smartcampus.operationshub.security.CustomUserDetailsService;
import com.smartcampus.operationshub.security.JwtAuthenticationFilter;
import com.smartcampus.operationshub.security.JwtService;
import com.smartcampus.operationshub.common.exception.GlobalExceptionHandler;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthenticationController.class)
@Import(GlobalExceptionHandler.class)
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthenticationService authenticationService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private AuthEntryPointJwt authEntryPointJwt;

    @MockBean
    private CurrentUserContext currentUserContext;

    @Test
    @DisplayName("Should return 400 when login request is invalid")
    void shouldReturnBadRequestWhenLoginRequestInvalid() throws Exception {
        LoginRequest request = new LoginRequest();
        request.setUniversityEmailAddress("");
        request.setPassword("");

        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}