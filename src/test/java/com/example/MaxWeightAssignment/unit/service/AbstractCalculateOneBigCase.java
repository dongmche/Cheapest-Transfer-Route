package com.example.MaxWeightAssignment.unit.service;

import com.example.MaxWeightAssignment.dto.Transfer;
import com.example.MaxWeightAssignment.dto.TransferRequest;
import com.example.MaxWeightAssignment.dto.TransferResponse;
import com.example.MaxWeightAssignment.service.CalculateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public abstract class AbstractCalculateOneBigCase {

    protected abstract CalculateService getCalculateService();

    private final TransferRequest transferRequest;
    private final TransferResponse expectedTransferResponse;

    public AbstractCalculateOneBigCase(TransferRequest transferRequest, TransferResponse expectedTransferResponse) {
        this.transferRequest = transferRequest;
        this.expectedTransferResponse = expectedTransferResponse;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{prepareValues()});
    }

    private static Object[] prepareValues() {
        TransferRequest transferRequest = new TransferRequest(
                12, // maxWeight
                Arrays.asList(
                        new Transfer(2, 10),
                        new Transfer(10, 20),
                        new Transfer(3, 5),
                        new Transfer(12, 31)
                )
        );

        TransferResponse expectedTransferResponse = new TransferResponse(
                Arrays.asList(
                        new Transfer(12, 31)),
                31, // totalCost
                12  // totalWeight
        );

        return new Object[]{transferRequest, expectedTransferResponse};
    }

    @Test
    public void testCalculateService() {
        CalculateService calculateService = getCalculateService();
        TransferResponse actual = calculateService.calculate(transferRequest);
        assertEquals(expectedTransferResponse, actual);
    }
}
