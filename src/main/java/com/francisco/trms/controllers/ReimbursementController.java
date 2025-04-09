package com.francisco.trms.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.francisco.trms.models.ReimbursementRequest;
import com.francisco.trms.services.ReimbursementService;

@RestController
@RequestMapping("/trms/reimbursements")
public class ReimbursementController {

    private final ReimbursementService service;

    public ReimbursementController(ReimbursementService service) {
        this.service = service;
    }

    @GetMapping
    public List<ReimbursementRequest> getAll() {
        return service.getAllRequests();
    }

    @PostMapping
    public ResponseEntity<ReimbursementRequest> create(@RequestBody ReimbursementRequest req) {
        return new ResponseEntity<>(service.createRequest(req), HttpStatus.CREATED);
    }
}

