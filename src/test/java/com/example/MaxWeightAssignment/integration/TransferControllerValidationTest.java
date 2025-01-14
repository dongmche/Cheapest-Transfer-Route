package com.example.MaxWeightAssignment.integration;

import com.example.MaxWeightAssignment.dto.Transfer;
import com.example.MaxWeightAssignment.dto.TransferRequest;
import com.example.MaxWeightAssignment.dto.TransferResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransferControllerValidationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private static String transfersCalCulateEndpoint = "/api/transfers/calculate";
    private String hostname = System.getProperty("hostname", "http://localhost:");



    @Test
    public void testErrorIsThrownOnWrongMaxWeight() throws Exception {
        TransferRequest request = new TransferRequest();
        List<Transfer> transfers = List.of();
        request.setMaxWeight(-1);
        request.setAvailableTransfers(transfers);

        assertIfErrorIsThrown(request);
    }

    @Test
    public void testErrorIsThrownOnWrongTransfer() throws Exception {
        TransferRequest request = new TransferRequest();
        List<Transfer> transfers = List.of(
                new Transfer(5, 10),
                new Transfer(-10, 20));
        request.setMaxWeight(1);
        request.setAvailableTransfers(transfers);

        assertIfErrorIsThrown(request);
    }
    @Test
    public void testIncorrectTransferMissesWeight() throws Exception {
        TransferRequest request = new TransferRequest();
        List<Transfer> transfers = new ArrayList<>();

        Transfer incorrectTransfer = new Transfer();
        incorrectTransfer.setCost(1);
        transfers.add(incorrectTransfer);


        request.setMaxWeight(1);
        request.setAvailableTransfers(transfers);

        assertIfErrorIsThrown(request);
    }
    @Test
    public void testIncorrectTransferMissesCost() throws Exception {
        TransferRequest request = new TransferRequest();
        List<Transfer> transfers = new ArrayList<>();

        Transfer incorrectTransfer = new Transfer();
        incorrectTransfer.setWeight(1);
        transfers.add(incorrectTransfer);


        request.setMaxWeight(1);
        request.setAvailableTransfers(transfers);

        assertIfErrorIsThrown(request);
    }




    public void assertIfErrorIsThrown(TransferRequest request) {
        org.springframework.http.HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TransferRequest> entity = new HttpEntity<>(request, headers);

        // Call the API
        ResponseEntity<TransferResponse> actualResponse = restTemplate.postForEntity(
                hostname + port + transfersCalCulateEndpoint,
                entity,
                TransferResponse.class
        );

        // Assert that the response status is 400 Bad Request
        assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }


}

