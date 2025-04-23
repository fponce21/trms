package com.francisco.trms.services;

import com.francisco.trms.dtos.ReimbursementRequestDTO;
import com.francisco.trms.models.ReimbursementRequest;
import com.francisco.trms.models.Users;
import com.francisco.trms.repositories.ReimbursementRepository;
import com.francisco.trms.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReimbursementService {
    private static final Logger logger = LoggerFactory.getLogger(ReimbursementService.class);

    private final ReimbursementRepository reimbursementRepository;
    private final UserRepository userRepository;

    public ReimbursementService(ReimbursementRepository reimbursementRepository, UserRepository userRepository) {
        this.reimbursementRepository = reimbursementRepository;
        this.userRepository = userRepository;
    }

    public ReimbursementRequestDTO createRequest(ReimbursementRequestDTO requestDTO) {
        logger.info("Creating request for userId: {}", requestDTO.getUserId());
        Users user = userRepository.findById(requestDTO.getUserId())
                .orElseThrow(() -> {
                    logger.error("User not found: {}", requestDTO.getUserId());
                    return new IllegalArgumentException("User not found");
                });

        ReimbursementRequest request = new ReimbursementRequest();
        request.setUser(user);
        request.setAmount(requestDTO.getAmount());
        request.setDescription(requestDTO.getDescription());
        request.setStatus(requestDTO.getStatus());

        ReimbursementRequest savedRequest = reimbursementRepository.save(request);
        logger.debug("Request saved: {}", savedRequest.getId());

        requestDTO.setId(savedRequest.getId());
        return requestDTO;
    }

    public List<ReimbursementRequestDTO> getAllRequests() {
        logger.info("Fetching all requests");
        List<ReimbursementRequest> requests = reimbursementRepository.findAll();
        return requests.stream().map(r -> {
            ReimbursementRequestDTO dto = new ReimbursementRequestDTO();
            dto.setId(r.getId());
            dto.setUserId(r.getUser().getId());
            dto.setAmount(r.getAmount());
            dto.setDescription(r.getDescription());
            dto.setStatus(r.getStatus());
            return dto;
        }).collect(Collectors.toList());
    }
}
