package com.example.MaxWeightAssignment.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequest {

    @NotNull(message = "Max weight cannot be null")
    @Min(value = 0, message = "Max weight cannot be less than 1")
    private Integer maxWeight;

    @Valid
    private List<Transfer> availableTransfers;

}

