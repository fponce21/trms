package com.francisco.trms.services;

import com.francisco.trms.dtos.ReimbursementRequestDTO;
import com.francisco.trms.models.ReimbursementRequest;
import com.francisco.trms.models.Users;
import com.francisco.trms.repositories.ReimbursementRepository;
import com.francisco.trms.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ReimbursementServiceTest {
    @Mock
    private ReimbursementRepository reimbursementRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ReimbursementService reimbursementService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createRequest_success() {
        ReimbursementRequestDTO dto = new ReimbursementRequestDTO();
        dto.setUserId(1L);
        dto.setAmount(1000.0);
        dto.setDescription("Course");
        dto.setStatus("PENDING");

        Users user = new Users();
        user.setId(1L);

        ReimbursementRequest savedRequest = new ReimbursementRequest();
        savedRequest.setId(1L);
        savedRequest.setUser(user);
        savedRequest.setAmount(1000.0);
        savedRequest.setDescription("Course");
        savedRequest.setStatus("PENDING");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(reimbursementRepository.save(any(ReimbursementRequest.class))).thenReturn(savedRequest);

        ReimbursementRequestDTO result = reimbursementService.createRequest(dto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(userRepository).findById(1L);
        verify(reimbursementRepository).save(any(ReimbursementRequest.class));
    }

    @Test
    void createRequest_userNotFound_throwsException() {
        ReimbursementRequestDTO dto = new ReimbursementRequestDTO();
        dto.setUserId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> reimbursementService.createRequest(dto));
        verify(userRepository).findById(1L);
        verify(reimbursementRepository, never()).save(any());
    }
}
