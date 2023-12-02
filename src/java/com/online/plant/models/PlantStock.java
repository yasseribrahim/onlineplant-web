package com.online.plant.models;

public class PlantStock {
    private int plantId;
    private String plantName;
    private int quantity;

    public PlantStock() {
    }

    public PlantStock(int plantId, String plantName, int quantity) {
        this.plantId = plantId;
        this.plantName = plantName;
        this.quantity = quantity;
    }

    public int getPlantId() {
        return plantId;
    }

    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
