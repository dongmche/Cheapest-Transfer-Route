package com.example.MaxWeightAssignment.unit.service.calculateServiceTests;

import com.example.MaxWeightAssignment.dto.TransferRequest;
import com.example.MaxWeightAssignment.dto.TransferResponse;
import com.example.MaxWeightAssignment.service.CalculateService;
import com.example.MaxWeightAssignment.service.calculateServiceImpl.CalculateServiceImpl;
import com.example.MaxWeightAssignment.unit.service.AbstractCalculateZeroCase;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class CalculateZeroCase extends AbstractCalculateZeroCase {
    public CalculateZeroCase(TransferRequest transferRequest, TransferResponse expectedTransferResponse) {
        super(transferRequest, expectedTransferResponse);
    }

    @Override
    protected CalculateService getCalculateService() {
        return new CalculateServiceImpl();
    }
}

