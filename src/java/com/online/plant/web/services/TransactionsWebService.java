package com.online.plant.web.services;

import com.online.plant.data.DatasourceManager;
import com.online.plant.models.Transaction;
import com.online.plant.models.TransactionDetails;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.*;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author user
 */
@Path("/transactions")
public class TransactionsWebService {

    /**
     * Creates a new instance of ViewAll
     */
    public TransactionsWebService() {
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransactions() {
        List<Transaction> transactions = DatasourceManager.loadTransactions();
        return Response.status(200).entity(DatasourceManager.convert(transactions)).build();
    }

    @GET
    @Path("/buyer/{byuerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransactionsBuyer(@PathParam("byuerId") String byuerId) {
        List<Transaction> transactions = DatasourceManager.loadTransactions();
        List<Transaction> filterdTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getBuyerId().equalsIgnoreCase(byuerId)) {
                filterdTransactions.add(transaction);
            }
        }
        return Response.status(200).entity(DatasourceManager.convert(filterdTransactions)).build();
    }

    @GET
    @Path("/seller/{sellerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransactionsSeller(@PathParam("sellerId") String sellerId) {
        List<Transaction> transactions = DatasourceManager.loadTransactions();
        List<Transaction> filterdTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getSellerId().equalsIgnoreCase(sellerId)) {
                filterdTransactions.add(transaction);
            }
        }
        return Response.status(200).entity(DatasourceManager.convert(filterdTransactions)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransactionDetails(@PathParam("id") int id) {
        List<Transaction> transactions = DatasourceManager.loadTransactions();
        for (Transaction transaction : transactions) {
            if (transaction.getId() == id) {
                return Response.status(200).entity(DatasourceManager.convert(transaction)).build();
            }
        }
        return Response.status(404).entity("Not Found").build();
    }

    @GET
    @Path("/{id}/{plantId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransactionPlantDetails(@PathParam("id") int id, @PathParam("plantId") int plantId) {
        List<Transaction> transactions = DatasourceManager.loadTransactions();
        for (Transaction transaction : transactions) {
            if (transaction.getId() == id) {
                for (TransactionDetails detail : transaction.getDetailses()) {
                    if (detail.getId() == plantId) {
                        return Response.status(200).entity(DatasourceManager.convert(detail)).build();
                    }
                }
            }
        }
        return Response.status(404).entity("Not Found").build();
    }

    @GET
    @Path("/plant/edit/{id}/{plantId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTransactionPlant(@PathParam("id") int id, @PathParam("plantId") int plantId, @QueryParam("quantity") int quantity) {
        DatasourceManager.updateTransactionPlant(id, plantId, quantity);
        return Response.status(200).entity("Updated").build();
    }

    @GET
    @Path("/plant/add/{id}/{plantId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTransactionPlant(@PathParam("id") int id, @PathParam("plantId") int plantId, @QueryParam("quantity") int quantity) {
        DatasourceManager.addTransactionPlant(id, plantId, quantity);
        return Response.status(200).entity("Updated").build();
    }

    @GET
    @Path("/plant/delete/{id}/{plantId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTransactionPlant(@PathParam("id") int id, @PathParam("plantId") int plantId) {
        DatasourceManager.deleteTransactionPlant(id, plantId);
        return Response.status(200).entity("Updated").build();
    }
}
