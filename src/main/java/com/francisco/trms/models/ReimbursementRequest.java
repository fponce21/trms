package com.francisco.trms.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reimbursement_requests")
public class ReimbursementRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String employeeName;
	private String eventType;
	private Double amountRequested;
	private String status;

	public ReimbursementRequest() {
	}

	public ReimbursementRequest(String employeeName, String eventType, Double amountRequested, String status) {
		this.employeeName = employeeName;
		this.eventType = eventType;
		this.amountRequested = amountRequested;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Double getAmountRequested() {
		return amountRequested;
	}

	public void setAmountRequested(Double amountRequested) {
		this.amountRequested = amountRequested;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
