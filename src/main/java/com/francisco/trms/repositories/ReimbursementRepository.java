package com.francisco.trms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.francisco.trms.models.ReimbursementRequest;

@Repository
public interface ReimbursementRepository extends JpaRepository<ReimbursementRequest, Long> {
    List<ReimbursementRequest> findByEmployeeName(String name);
}
