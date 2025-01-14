package com.example.MaxWeightAssignment.service.calculateServiceImpl;

import com.example.MaxWeightAssignment.dto.TransferRequest;
import com.example.MaxWeightAssignment.dto.TransferResponse;
import com.example.MaxWeightAssignment.functions.OptimalRecursiveCalculator;
import com.example.MaxWeightAssignment.service.CalculateService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Component
@Primary
public class CalculateServiceFastImpl implements CalculateService {

    @Override
    public TransferResponse calculate(TransferRequest request) {
        return OptimalRecursiveCalculator.calculate(request);
    }

}

