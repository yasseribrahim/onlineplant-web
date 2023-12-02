/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.online.plant.client.call;

import com.online.plant.data.DatasourceManager;
import com.online.plant.models.Transaction;
import com.online.plant.models.TransactionDetails;
import java.text.MessageFormat;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class TransactionsClient {

    private final WebTarget webTarget;
    private final Client client;
    private static final String BASE_URI = "http://localhost:8080/onlineplant/webresources";

    public TransactionsClient() {
        client = ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("transactions");
    }

    public List<Transaction> getTransactions() {
        WebTarget resource = webTarget;
        resource = resource.path("/");
        try {
            String json = resource.request(MediaType.APPLICATION_JSON).get(String.class);
            return DatasourceManager.loadTransactions(json);
        } catch (Exception ex) {
            return null;
        }
    }

    public List<Transaction> getTransactionsBuyer(String buyerId) {
        WebTarget resource = webTarget;
        resource = resource.path(MessageFormat.format("/buyer/{0}/", new Object[]{buyerId}));
        try {
            String json = resource.request(MediaType.APPLICATION_JSON).get(String.class);
            return DatasourceManager.loadTransactions(json);
        } catch (Exception ex) {
            return null;
        }
    }

    public List<Transaction> getTransactionsSeller(String sellerId) {
        WebTarget resource = webTarget;
        resource = resource.path(MessageFormat.format("/seller/{0}/", new Object[]{sellerId}));
        try {
            String json = resource.request(MediaType.APPLICATION_JSON).get(String.class);
            return DatasourceManager.loadTransactions(json);
        } catch (Exception ex) {
            return null;
        }
    }

    public Transaction getTransactionDetails(int id) {
        WebTarget resource = webTarget;
        resource = resource.path(MessageFormat.format("/{0}", new Object[]{id}));
        try {
            String json = resource.request(MediaType.APPLICATION_JSON).get(String.class);
            return DatasourceManager.loadTransaction(json);
        } catch (Exception ex) {
            return null;
        }
    }

    public TransactionDetails getTransactionPlantDetails(int id, int plantId) {
        WebTarget resource = webTarget;
        resource = resource.path(MessageFormat.format("/{0}/{1}", new Object[]{id, plantId}));
        try {
            String json = resource.request(MediaType.APPLICATION_JSON).get(String.class);
            return DatasourceManager.loadTransactionDetails(json);
        } catch (Exception ex) {
            return null;
        }
    }

    public String updateTransactionPlantDetails(int id, int plantId, int quantity) {
        WebTarget resource = webTarget;
        resource = resource.path(MessageFormat.format("/plant/edit/{0}/{1}", new Object[]{id, plantId})).queryParam("quantity", quantity);
        try {
            String response = resource.request(MediaType.APPLICATION_JSON).get(String.class);
            return response;
        } catch (Exception ex) {
            return null;
        }
    }

    public String addTransactionPlantDetails(int id, int plantId, int quantity) {
        WebTarget resource = webTarget;
        resource = resource.path(MessageFormat.format("/plant/add/{0}/{1}", new Object[]{id, plantId})).queryParam("quantity", quantity);
        try {
            String response = resource.request(MediaType.APPLICATION_JSON).get(String.class);
            return response;
        } catch (Exception ex) {
            return null;
        }
    }

    public String deleteTransactionPlantDetails(int id, int plantId) {
        WebTarget resource = webTarget;
        resource = resource.path(MessageFormat.format("/plant/delete/{0}/{1}", new Object[]{id, plantId}));
        try {
            String response = resource.request(MediaType.APPLICATION_JSON).get(String.class);
            return response;
        } catch (Exception ex) {
            return null;
        }
    }
}
