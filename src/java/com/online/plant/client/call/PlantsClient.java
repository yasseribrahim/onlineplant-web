/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.online.plant.client.call;

import com.online.plant.data.DatasourceManager;
import com.online.plant.models.Plant;
import java.text.MessageFormat;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class PlantsClient {

    private final WebTarget webTarget;
    private final Client client;
    private static final String BASE_URI = "http://localhost:8080/onlineplant/webresources";

    public PlantsClient() {
        client = ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("plants");
    }

    public List<Plant> getPlants() {
        WebTarget resource = webTarget;
        resource = resource.path("/");
        try {
            String json = resource.request(MediaType.APPLICATION_JSON).get(String.class);
            return DatasourceManager.loadPlants(json);
        } catch (Exception ex) {
            return null;
        }
    }

    public List<Plant> getPlants(String search) {
        WebTarget resource = webTarget;
        resource = resource.path("/search").queryParam("search", search == null ? "" : search);
        try {
            String json = resource.request(MediaType.APPLICATION_JSON).get(String.class);
            return DatasourceManager.loadPlants(json);
        } catch (Exception ex) {
            return null;
        }
    }

    public Plant getPlant(int id) {
        WebTarget resource = webTarget;
        resource = resource.path(MessageFormat.format("/{0}", new Object[]{id}));
        try {
            String json = resource.request(MediaType.APPLICATION_JSON).get(String.class);
            return DatasourceManager.loadPlant(json);
        } catch (Exception ex) {
            return null;
        }
    }
}
