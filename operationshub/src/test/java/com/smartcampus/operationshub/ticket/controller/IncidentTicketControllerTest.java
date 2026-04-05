package com.smartcampus.operationshub.ticket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartcampus.operationshub.common.enums.IncidentCategory;
import com.smartcampus.operationshub.common.enums.TicketPriorityLevel;
import com.smartcampus.operationshub.ticket.dto.request.CreateIncidentTicketRequest;
import com.smartcampus.operationshub.ticket.service.IncidentTicketService;
import com.smartcampus.operationshub.security.AuthEntryPointJwt;
import com.smartcampus.operationshub.security.CurrentUserContext;
import com.smartcampus.operationshub.security.CustomUserDetailsService;
import com.smartcampus.operationshub.security.JwtAuthenticationFilter;
import com.smartcampus.operationshub.security.JwtService;
import com.smartcampus.operationshub.common.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IncidentTicketController.class)
@Import(GlobalExceptionHandler.class)
class IncidentTicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IncidentTicketService incidentTicketService;

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
    @DisplayName("Should return 400 when ticket request is invalid")
    void shouldReturnBadRequestWhenTicketRequestInvalid() throws Exception {
        CreateIncidentTicketRequest request = new CreateIncidentTicketRequest();
        request.setIncidentCategory(IncidentCategory.HARDWARE_ISSUE);
        request.setTicketTitle("Bad");
        request.setDescription("Short");
        request.setPriorityLevel(TicketPriorityLevel.HIGH);

        mockMvc.perform(post("/api/v1/tickets")
                        .param("userId", "user-123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}