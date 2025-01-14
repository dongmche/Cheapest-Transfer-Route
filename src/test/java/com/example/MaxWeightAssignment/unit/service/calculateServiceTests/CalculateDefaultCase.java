package com.example.MaxWeightAssignment.unit.service.calculateServiceTests;

import com.example.MaxWeightAssignment.dto.TransferRequest;
import com.example.MaxWeightAssignment.dto.TransferResponse;
import com.example.MaxWeightAssignment.service.CalculateService;
import com.example.MaxWeightAssignment.service.calculateServiceImpl.CalculateServiceImpl;
import com.example.MaxWeightAssignment.unit.service.AbstractCalculateDefaultCase;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CalculateDefaultCase extends AbstractCalculateDefaultCase {
    public CalculateDefaultCase(TransferRequest transferRequest, TransferResponse expectedTransferResponse) {
        super(transferRequest, expectedTransferResponse);
    }

    @Override
    protected CalculateService getCalculateService() {

        return new CalculateServiceImpl();
    }
}

