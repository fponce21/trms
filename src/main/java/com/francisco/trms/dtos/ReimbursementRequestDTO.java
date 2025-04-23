package com.francisco.trms.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReimbursementRequestDTO {
    private Long id;
    @NotNull
    private Long userId;
    @Positive
    private Double amount;
    @NotBlank
    private String description;
    @NotBlank
    private String status;
}
