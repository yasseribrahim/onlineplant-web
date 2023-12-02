package com.online.plant.models;

public class TransactionDetails {
    private int id;
    private String name;
    private String type;
    private int age;
    private int price;
    private int quantity;

    public TransactionDetails() {
    }

    public TransactionDetails(int id, String name, String type, int age, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.age = age;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
