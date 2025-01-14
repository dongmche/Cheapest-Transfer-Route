package com.example.MaxWeightAssignment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TransferResponse {
    private List<Transfer> selectedTransfers;
    private Integer totalCost;
    private Integer totalWeight;

}
