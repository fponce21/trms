package com.francisco.trms.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.francisco.trms.models.ReimbursementRequest;
import com.francisco.trms.repositories.ReimbursementRepository;

@Service
public class ReimbursementService {

    private final ReimbursementRepository repository;

    public ReimbursementService(ReimbursementRepository repository) {
        this.repository = repository;
    }

    public List<ReimbursementRequest> getAllRequests() {
        return repository.findAll();
    }

    public ReimbursementRequest createRequest(ReimbursementRequest request) {
        return repository.save(request);
    }

    // Additional business logic here
}
