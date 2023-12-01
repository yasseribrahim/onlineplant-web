/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.online.plant.resources;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:ListProduct [listproduct]<br>
 * USAGE:
 * <pre>
 *        ProductClient client = new ProductClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author ayman
 */
public class ProductClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/onlineplant/webresources";

    public ProductClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("products");
    }

    public String getAllProducts() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("products");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_HTML).get(String.class);
    }
    public String getStoreProduct() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("store");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_HTML).get(String.class);
    }

    public <T> T getMyProducts(Class<T> responseType, String transid, String client) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("products/{0}/{1}", new Object[]{transid, client}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_HTML).get(responseType);
    }

    public String UpdateStatus(String product, String status, String branch) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("updatestatus/{0}/{1}/{2}", new Object[]{product, status, branch}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_HTML).get(String.class);
    }
    
    public String UpdateStore(String name, String quantity, String branch) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("updatestore/{0}/{1}/{2}", new Object[]{name, quantity, branch}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_HTML).get(String.class);
    }

    public void close() {
        client.close();
    }
    
}
