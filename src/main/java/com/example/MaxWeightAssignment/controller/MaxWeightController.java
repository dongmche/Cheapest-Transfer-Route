package com.example.MaxWeightAssignment.controller;


import com.example.MaxWeightAssignment.dto.TransferRequest;
import com.example.MaxWeightAssignment.dto.TransferResponse;
import com.example.MaxWeightAssignment.service.CalculateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/transfers")
@Validated
public class MaxWeightController {

    @Autowired
    private CalculateService calculateService;

    @PostMapping("/calculate")
    public ResponseEntity<Object> calculateTransfers(@Valid @RequestBody TransferRequest request) {
        TransferResponse response = calculateService.calculate(request);
        return ResponseEntity.ok(response);
    }
}
