package com.example.MaxWeightAssignment.service.calculateServiceImpl;


import com.example.MaxWeightAssignment.dto.TransferRequest;
import com.example.MaxWeightAssignment.dto.TransferResponse;
import com.example.MaxWeightAssignment.functions.RecursiveCalculator;
import com.example.MaxWeightAssignment.service.CalculateService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
@Qualifier("secondaryService")
public class CalculateServiceImpl implements CalculateService {
    @Override
    public TransferResponse calculate(TransferRequest request) {
        return RecursiveCalculator.calculate(request);
    }
}


