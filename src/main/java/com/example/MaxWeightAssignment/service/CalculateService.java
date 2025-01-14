package com.example.MaxWeightAssignment.service;

import com.example.MaxWeightAssignment.dto.TransferRequest;
import com.example.MaxWeightAssignment.dto.TransferResponse;

public interface CalculateService {
    public TransferResponse calculate(TransferRequest transferRequest);
}
