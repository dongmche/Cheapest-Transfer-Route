package com.example.MaxWeightAssignment.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class Transfer {

    @Min(value = 0, message = "Transfer weight can not be less than 0")
    @NotNull
    private Integer weight;

    @NotNull
    private Integer cost;
}
