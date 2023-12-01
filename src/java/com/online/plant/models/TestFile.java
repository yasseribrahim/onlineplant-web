package com.online.plant.models;

import java.util.ArrayList;
import java.util.List;

public class TestFile {

    public static void main(String[] args) {
        List<Plant> plants = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            plants.add(new Plant(i, "Plant " + i, "Type " + i, i, i * 10));
        }

        List<Transaction> transactions = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            List<TransactionDetails> detailses = new ArrayList<>();
            for (int j = 1; j <= i; j++) {
                detailses.add(new TransactionDetails(i, "Plant " + j, "Type " + j, j, i * 10, i));
            }
            
            Transaction transaction = new Transaction(i, "saller1", "buyer1", i + "/10/2023");
            transaction.setDetailses(detailses);
            transactions.add(transaction);
        }
    }
}
