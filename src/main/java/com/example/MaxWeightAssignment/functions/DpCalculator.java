package com.example.MaxWeightAssignment.functions;

import com.example.MaxWeightAssignment.dto.Transfer;
import com.example.MaxWeightAssignment.dto.TransferRequest;
import com.example.MaxWeightAssignment.dto.TransferResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class DpCalculator {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    static class Node {
        private Transfer transfer;
        private Node next;
    }

    public DpCalculator() {
    }


    public static TransferResponse calculateMaxWeightTransfer(TransferRequest transferRequest) {
        List<Transfer> availableTransfers = transferRequest.getAvailableTransfers();

        if (availableTransfers.isEmpty()) {
            return new TransferResponse(new ArrayList<>(), 0, 0);
        }


        int len = availableTransfers.size();
        int[] weights = new int[len];
        int[] costs = new int[len];
        Node[] transferNodes = new Node[len];

        for (int i = len - 1; i > -1; i--) {
            Transfer curTransfer = availableTransfers.get(i);
            if (curTransfer.getWeight() > transferRequest.getMaxWeight()) {
                weights[i] = 0;
                costs[i] = 0;
                transferNodes[i] = null;
                continue;
            }

            int chosenIndex = -1;
            int curCost = curTransfer.getCost();

            for (int j = i + 1; j < len; j++) {
                if (curTransfer.getWeight() + weights[j] <= transferRequest.getMaxWeight()
                        && curTransfer.getCost() + costs[j] > curCost) {
                    chosenIndex = j;
                    curCost = curTransfer.getCost() + costs[j];
                }
            }

            if (chosenIndex == -1) {
                weights[i] = curTransfer.getWeight();
                costs[i] = curTransfer.getCost();
                transferNodes[i] = new Node(curTransfer, null);
            } else {
                weights[i] = curTransfer.getWeight() + weights[chosenIndex];
                costs[i] = curTransfer.getCost() + costs[chosenIndex];
                transferNodes[i] = new Node(curTransfer, transferNodes[chosenIndex]);
            }

        }


        List<Transfer> transfers = new ArrayList<>();

        int chosenIndex = 0;
        for (int i = 1; i < len; i++) {
            if (costs[i] > costs[chosenIndex]) {
                chosenIndex = i;
            }
        }

        Node startTransfer = transferNodes[chosenIndex];

        while (startTransfer != null) {
            transfers.add(startTransfer.getTransfer());
            startTransfer = startTransfer.getNext();
        }

        TransferResponse response = new TransferResponse();
        response.setTotalCost(costs[chosenIndex]);
        response.setTotalWeight(weights[chosenIndex]);
        response.setSelectedTransfers(transfers);

        return response;
    }
}

