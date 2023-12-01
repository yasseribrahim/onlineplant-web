/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.online.plant.web.services;

import com.online.plant.constants.Constants;
import com.online.plant.constants.UIConstants;
import com.online.plant.resources.DatasourceManager;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 */
@Path("products")
public class ProductsWebService {

    /**
     * Creates a new instance of ProductsWebService
     */
    public ProductsWebService() {
    }

    @GET
    @Path("/products/{transactionId}/{clientId}")
    @Produces(MediaType.TEXT_HTML)
    public Response getMyProducts(@Context ServletContext servletContext, @PathParam("transactionId") String transactionId, @PathParam("clientId") String clientId) {
        JsonArray array = DatasourceManager.readArray(Constants.PATH_DATA_PRODUCTS);
        String filteredFormatted = DatasourceManager.getFormattedMyProducts(array, clientId, transactionId, servletContext.getContextPath());
        
        StringBuilder builder = new StringBuilder();
        builder.append(UIConstants.HTML_START);
        builder.append(UIConstants.ELEMENT_HEADER.replace("/assets", servletContext.getContextPath() + "/assets"));
        builder.append(UIConstants.BODY_START);
        builder.append(String.format(UIConstants.ELEMENT_TOP_BAR.replace("/assets", servletContext.getContextPath() + "/assets"), servletContext.getContextPath() + "/home", clientId));

        builder.append(String.format(UIConstants.CONTENT_PRODUCTS_TABLE, "Products", "Products", filteredFormatted));
        builder.append("</div>");
        builder.append(UIConstants.ELEMENT_FOOTER);
        builder.append(UIConstants.ELEMENT_JAVASCRIPT.replace("/assets", servletContext.getContextPath() + "/assets"));
        builder.append(UIConstants.BODY_END);
        builder.append(UIConstants.HTML_END);
        return Response.status(200).entity(builder.toString()).build();
    }

    @GET
    @Path("/products")
    @Produces(MediaType.TEXT_HTML)
    public String getAllProducts() {
        JsonArray products = DatasourceManager.readArray(Constants.PATH_DATA_PRODUCTS);
        String output101 = DatasourceManager.getProducts(products);
        return output101;
    }

    @GET
    @Path("/store")
    @Produces(MediaType.TEXT_HTML)
    public String getStoreProduct() {
        JsonArray storeData101 = DatasourceManager.readArray(Constants.PATH_DATA_STOCKS);;
        String output101 = DatasourceManager.getStores(storeData101);
        return output101;
    }

    @GET
    @Path("/updatestore/{name}/{quantity}/{branch}")
    @Produces(MediaType.TEXT_HTML)
    public String UpdateStore(@PathParam("name") String name,
            @PathParam("quantity") String quantity,
            @PathParam("branch") String branch) {

        String output101 = "";
        JsonArray newArr101 = null;
        JsonArray storeData101 = null;
        String fname101 = Constants.PATH_DATA_STOCKS;
        storeData101 = DatasourceManager.readArray(fname101);
        JsonObject product101 = DatasourceManager.getStoreProduct(name, storeData101);

        newArr101 = DatasourceManager.updateStore(name, quantity, branch, storeData101, product101);
        DatasourceManager.persist(newArr101, fname101);

        String fileData101 = DatasourceManager.tellMeAbout(newArr101);
        output101 = output101 + "-" + " name= " + name + " quantity= " + quantity + " branch= " + branch + " - "
                + fileData101 + "";
        return output101;
    }

    @GET
    @Path("/updatestatus/{id}/{status}/{branch}")
    @Produces(MediaType.TEXT_HTML)
    public String UpdateStatus(@PathParam("id") String id,
            @PathParam("status") String status,
            @PathParam("branch") String branch) {
        String output101 = "";
        JsonArray newArr101 = null;
        JsonArray storeData101 = null;
        String fname101 = Constants.PATH_DATA_PRODUCTS;
        storeData101 = DatasourceManager.readArray(fname101);
        JsonObject product101 = DatasourceManager.getProduct(id, storeData101);

        newArr101 = DatasourceManager.updateProductStatus(id, status, branch, storeData101, product101);
        DatasourceManager.persist(newArr101, fname101);
        String fileData101 = DatasourceManager.tellMeAbout(newArr101);
        //String fileData =Basics.tellMeAbout(storeData);
        output101 = output101 + "-" + id + " name= " + status + " branch= " + branch + " - "+ fileData101 + "";
        return output101;
    }

}
