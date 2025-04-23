package com.francisco.trms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.francisco.trms.models.ReimbursementRequest;

@Repository
public interface ReimbursementRepository extends JpaRepository<ReimbursementRequest, Long> {
}
