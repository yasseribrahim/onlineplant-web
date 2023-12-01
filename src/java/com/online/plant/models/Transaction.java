package com.online.plant.models;

import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private int id;
    private String sallerId;
    private String buyerId;
    private String date;
    private List<TransactionDetails> detailses;

    public Transaction(int id, String sallerId, String buyerId, String date) {
        this.id = id;
        this.sallerId = sallerId;
        this.buyerId = buyerId;
        this.date = date;
        detailses = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSallerId() {
        return sallerId;
    }

    public void setSallerId(String sallerId) {
        this.sallerId = sallerId;
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
