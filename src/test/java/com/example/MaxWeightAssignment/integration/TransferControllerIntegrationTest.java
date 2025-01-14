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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransferControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private static String transfersCalCulateEndpoint = "/api/transfers/calculate";
    private String hostname = System.getProperty("hostname", "http://localhost:");


    @Test
    public void testNullCase() throws Exception {
        TransferRequest request = new TransferRequest();
        List<Transfer> transfers = List.of();
        request.setMaxWeight(0);
        request.setAvailableTransfers(transfers);

        TransferResponse response = new TransferResponse();
        List<Transfer> selectedTransfers = List.of();
        response.setTotalCost(0);
        response.setTotalWeight(0);
        response.setSelectedTransfers(selectedTransfers);

        testCalculateTransfer(request, response);
    }


    @Test
    public void testZeroCase() throws Exception {
        TransferRequest request = new TransferRequest();
        List<Transfer> transfers = List.of(
                new Transfer(5, 10),
                new Transfer(10, 20),
                new Transfer(3, 5),
                new Transfer(5, 8)
        );
        request.setMaxWeight(2);
        request.setAvailableTransfers(transfers);

        TransferResponse response = new TransferResponse();
        List<Transfer> selectedTransfers = List.of();
        response.setTotalCost(0);
        response.setTotalWeight(0);
        response.setSelectedTransfers(selectedTransfers);

        testCalculateTransfer(request, response);
    }

    @Test
    public void testOneBigElementCase() throws Exception {
        TransferRequest request = new TransferRequest();
        List<Transfer> transfers = List.of(
                new Transfer(5, 10),
                new Transfer(10, 20),
                new Transfer(3, 5),
                new Transfer(5, 8),
                new Transfer(100, 100)
        );
        request.setMaxWeight(100);
        request.setAvailableTransfers(transfers);

        TransferResponse response = new TransferResponse();
        List<Transfer> selectedTransfers = List.of(
                new Transfer(100, 100)
        );
        response.setTotalCost(100);
        response.setTotalWeight(100);
        response.setSelectedTransfers(selectedTransfers);

        testCalculateTransfer(request, response);
    }

    @Test
    public void testDefaultCase() throws Exception {
        TransferRequest request = new TransferRequest();
        List<Transfer> transfers = List.of(
                new Transfer(5, 10),
                new Transfer(10, 20),
                new Transfer(3, 5),
                new Transfer(5, 8)
        );
        request.setMaxWeight(15);
        request.setAvailableTransfers(transfers);

        TransferResponse response = new TransferResponse();
        List<Transfer> selectedTransfers = List.of(
                new Transfer(5, 10),
                new Transfer(10, 20)
        );
        response.setTotalCost(30);
        response.setTotalWeight(15);
        response.setSelectedTransfers(selectedTransfers);

        testCalculateTransfer(request, response);
    }

    @Test
    public void testComplexCases() throws Exception {
        TransferRequest request = new TransferRequest();
        List<Transfer> transfers = List.of(
                new Transfer(5, 10),
                new Transfer(10, 20),
                new Transfer(3, 5),
                new Transfer(5, 8)
        );
        request.setMaxWeight(18);
        request.setAvailableTransfers(transfers);

        TransferResponse response = new TransferResponse();
        List<Transfer> selectedTransfers = List.of(
                new Transfer(5, 10),
                new Transfer(10, 20),
                new Transfer(3, 5)
        );
        response.setTotalCost(35);
        response.setTotalWeight(18);
        response.setSelectedTransfers(selectedTransfers);

        testCalculateTransfer(request, response);
    }




    public void testCalculateTransfer(TransferRequest request, TransferResponse expectedResponse) throws Exception {
        // Prepare headers and HTTP entity
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TransferRequest> entity = new HttpEntity<>(request, headers);

        // Call the API
        ResponseEntity<TransferResponse> actualResponse = restTemplate.postForEntity(
                hostname + port + transfersCalCulateEndpoint,
                entity,
                TransferResponse.class
        );

        // Assertions
        assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actualResponse.getBody()).isNotNull();

        TransferResponse responseBody = actualResponse.getBody();
        assertThat(responseBody.getTotalCost()).isEqualTo(expectedResponse.getTotalCost());
        assertThat(responseBody.getTotalWeight()).isEqualTo(expectedResponse.getTotalWeight());
        assertThat(responseBody.getSelectedTransfers()).isEqualTo(expectedResponse.getSelectedTransfers());

    }
}

