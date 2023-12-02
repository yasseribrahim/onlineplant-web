/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.online.plant.client.call;

import com.online.plant.data.DatasourceManager;
import com.online.plant.models.PlantStock;
import java.text.MessageFormat;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class PlantStocksClient {

    private final WebTarget webTarget;
    private final Client client;
    private static final String BASE_URI = "http://localhost:8080/onlineplant/webresources";

    public PlantStocksClient() {
        client = ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("stocks");
    }

    public List<PlantStock> getPlantStockt() {
        WebTarget resource = webTarget;
        resource = resource.path("/");
        try {
            String json = resource.request(MediaType.APPLICATION_JSON).get(String.class);
            return DatasourceManager.loadPlantStocks(json);
        } catch (Exception ex) {
            return null;
        }
    }

    public String updatePlantStock(int id, int quantity) {
        WebTarget resource = webTarget;
        resource = resource.path(MessageFormat.format("/update/{0}", new Object[]{id})).queryParam("quantity", quantity);
        try {
            String response = resource.request(MediaType.APPLICATION_JSON).get(String.class);
            return response;
        } catch (Exception ex) {
            return null;
        }
    }
}
