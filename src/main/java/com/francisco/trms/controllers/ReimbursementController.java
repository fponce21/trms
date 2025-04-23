package com.francisco.trms.controllers;

import com.francisco.trms.dtos.ReimbursementRequestDTO;
import com.francisco.trms.services.ReimbursementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/reimbursements")
@Validated
public class ReimbursementController {
    private static final Logger logger = LoggerFactory.getLogger(ReimbursementController.class);

    private final ReimbursementService reimbursementService;

    public ReimbursementController(ReimbursementService reimbursementService) {
        this.reimbursementService = reimbursementService;
    }

    @PostMapping
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<ReimbursementRequestDTO> createReimbursementRequest(
            @Valid @RequestBody ReimbursementRequestDTO requestDTO) {
        logger.info("Creating reimbursement request for userId: {}", requestDTO.getUserId());
        try {
            ReimbursementRequestDTO savedRequest = reimbursementService.createRequest(requestDTO);
            logger.debug("Request created: {}", savedRequest.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(savedRequest);
        } catch (Exception e) {
            logger.error("Failed to create request for userId: {}", requestDTO.getUserId(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('EMPLOYEE', 'MANAGER')")
    public ResponseEntity<List<ReimbursementRequestDTO>> getAllReimbursements() {
        logger.info("Fetching all reimbursement requests");
        try {
            List<ReimbursementRequestDTO> requests = reimbursementService.getAllRequests();
            logger.debug("Retrieved {} requests", requests.size());
            return ResponseEntity.ok(requests);
        } catch (Exception e) {
            logger.error("Failed to fetch requests", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

