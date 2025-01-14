package com.example.MaxWeightAssignment.unit.service.calculateFastServiceTests;

import com.example.MaxWeightAssignment.dto.TransferRequest;
import com.example.MaxWeightAssignment.dto.TransferResponse;
import com.example.MaxWeightAssignment.service.CalculateService;
import com.example.MaxWeightAssignment.service.calculateServiceImpl.CalculateServiceFastImpl;
import com.example.MaxWeightAssignment.unit.service.AbstractCalculateDefaultCase;

public class CalculateDefaultCase extends AbstractCalculateDefaultCase {

    public CalculateDefaultCase(TransferRequest transferRequest, TransferResponse expectedTransferResponse) {
        super(transferRequest, expectedTransferResponse);
    }

    @Override
    protected CalculateService getCalculateService() {
        return new CalculateServiceFastImpl();
    }
}

