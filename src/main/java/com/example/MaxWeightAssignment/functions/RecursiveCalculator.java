package com.example.MaxWeightAssignment.functions;

import com.example.MaxWeightAssignment.dto.Transfer;
import com.example.MaxWeightAssignment.dto.TransferRequest;
import com.example.MaxWeightAssignment.dto.TransferResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecursiveCalculator {
    public RecursiveCalculator() {
    }

    public static TransferResponse calculate(TransferRequest request) {

        List<Transfer> availableTransfers  = request.getAvailableTransfers();
        int maxWeight = request.getMaxWeight();

        TransferResponse result = new TransferResponse();
        result.setTotalCost(0);
        result.setTotalWeight(0);
        result.setSelectedTransfers(new ArrayList<>());
        Map<Integer, Integer> mem = new HashMap<>();
        List<Transfer> transfers = new ArrayList<>();

        helper(availableTransfers,transfers, 0, 0, 0, maxWeight, result);


        return result;
    }
    private static void helper(List<Transfer> availableTransfers, List<Transfer> transfers, int curIndex, int curCost, int curWeight, int maxWeight,
                        TransferResponse result) {
        if (curIndex >= availableTransfers.size()){
            if (curCost > result.getTotalCost()){
                result.setTotalCost(curCost);
                result.setTotalWeight(curWeight);
                result.setSelectedTransfers(List.copyOf(transfers));
            }
            return;
        }


        Transfer curTransfer = availableTransfers.get(curIndex);
        if (curWeight + curTransfer.getWeight() <= maxWeight){
            transfers.add(curTransfer);
            helper(availableTransfers,
                    transfers,
                    curIndex+1,
                    curCost + curTransfer.getCost(),
                    curWeight + curTransfer.getWeight(),
                    maxWeight,
                    result);
            transfers.remove(transfers.size() - 1);
        }

        helper(availableTransfers, transfers, curIndex+1,
                curCost,
                curWeight,
                maxWeight,
                result);

    }
}
