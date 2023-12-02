package com.online.plant.models;

import java.util.ArrayList;
import java.util.List;

public class Transaction {

    private int id;
    private String sellerId;
    private String buyerId;
    private String date;
    private List<TransactionDetails> detailses;

    public Transaction() {
        this.detailses = new ArrayList<>();
    }

    public Transaction(int id, String sellerId, String buyerId, String date) {
        this.id = id;
        this.sellerId = sellerId;
        this.buyerId = buyerId;
        this.date = date;
        detailses = new ArrayList<>();
    }

    public int getTotal() {
        int total = 0;
        for (TransactionDetails detailse : detailses) {
            total += detailse.getPrice() * detailse.getQuantity();
        }
        return total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<TransactionDetails> getDetailses() {
        return detailses;
    }

    public void setDetailses(List<TransactionDetails> detailses) {
        this.detailses = detailses;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Transaction other = (Transaction) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
