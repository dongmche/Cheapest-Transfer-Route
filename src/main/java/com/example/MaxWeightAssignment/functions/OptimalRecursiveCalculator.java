package com.example.MaxWeightAssignment.functions;

import com.example.MaxWeightAssignment.dto.Transfer;
import com.example.MaxWeightAssignment.dto.TransferRequest;
import com.example.MaxWeightAssignment.dto.TransferResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OptimalRecursiveCalculator {
    public OptimalRecursiveCalculator() {
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    static class TransferNode {
        private Transfer transfer;
        private TransferNode next;
        private int weight;
        private int cost;
    }


    public static TransferResponse calculate(TransferRequest request) {
        Map<String, TransferNode> mem = new HashMap<>();
        TransferNode transferNode = helper(mem, request, 0, 0);

        if (transferNode == null){
            return new TransferResponse(new ArrayList<>(), 0, 0);
        }


        TransferResponse response = new TransferResponse();
        response.setTotalCost(transferNode.getCost());
        response.setTotalWeight(transferNode.getWeight());

        List<Transfer> transfers = new ArrayList<>();

        while (transferNode != null){
            transfers.add(transferNode.getTransfer());
            transferNode = transferNode.getNext();
        }
        response.setSelectedTransfers(transfers);

        return response;
    }

    private static TransferNode helper(Map<String, TransferNode> mem, TransferRequest request, int curWeight, int index) {
        if (index >= request.getAvailableTransfers().size() || curWeight > request.getMaxWeight()) {
            return null;
        }

        // I used string representation for the key
        String key = curWeight + "-" + index;
        Transfer curTransfer = request.getAvailableTransfers().get(index);

        // Check memoization map
        if (mem.containsKey(key)) {
            return mem.get(key);
        }

        TransferNode with = null;

        // Calculate the "with" case if weight is within limits
        if (curTransfer.getWeight() + curWeight <= request.getMaxWeight()) {
            TransferNode newNode = helper(mem, request, curWeight + curTransfer.getWeight(), index + 1);
            with = new TransferNode(curTransfer, newNode, curTransfer.getWeight(), curTransfer.getCost());

            if (newNode != null){
                with.setCost(newNode.getCost() + curTransfer.getCost());
                with.setWeight(newNode.getWeight() + curTransfer.getWeight());
            }
        }

        // Calculate the "without" case
        TransferNode without = helper(mem, request, curWeight, index + 1);


        if (without == null) {
            return with;
        }

        return (with.getCost() > without.getCost()) ? with : without;
    }

}
