package com.example.MaxWeightAssignment.functions;

import com.example.MaxWeightAssignment.dto.Transfer;
import com.example.MaxWeightAssignment.dto.TransferRequest;
import com.example.MaxWeightAssignment.dto.TransferResponse;

import java.util.ArrayList;
import java.util.List;

public class newOptimizedSolution {
    public static TransferResponse optimizeTransfers(TransferRequest request) {
        int maxWeight = request.getMaxWeight();
        List<Transfer> transfers = request.getAvailableTransfers();
        int n = transfers.size();

        int[] dp = new int[maxWeight + 1];


        boolean[][] selected = new boolean[n + 1][maxWeight + 1];

        // Fill the DP table
            for (int i = 1; i <= n; i++) {
            Transfer transfer = transfers.get(i - 1);
            int weight = transfer.getWeight();
            int cost = transfer.getCost();

            for (int j = maxWeight; j >= weight; j--) {
                if (dp[j - weight] + cost > dp[j]) {
                    dp[j] = dp[j - weight] + cost;
                    selected[i][j] = true;
                }
            }
        }

        // Backtracking
        List<Transfer> selectedTransfers = new ArrayList<>();
        int remainingWeight = maxWeight;
            for (int i = n; i > 0; i--) {
            if (selected[i][remainingWeight]) {
                selectedTransfers.add(transfers.get(i - 1));
                remainingWeight -= transfers.get(i - 1).getWeight();
            }
        }

        // returned and create a response
        TransferResponse response = new TransferResponse();
            response.setSelectedTransfers(selectedTransfers);
            response.setTotalCost(dp[maxWeight]);
            response.setTotalWeight(maxWeight - remainingWeight);

            return response;
    }
}

