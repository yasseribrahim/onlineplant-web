package com.online.plant.web.services;

import com.online.plant.constants.Constants;
import com.online.plant.constants.UIConstants;
import com.online.plant.resources.DatasourceManager;
import javax.json.JsonArray;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.*;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
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
    @Path("/{clientid}")
    @Produces(MediaType.TEXT_HTML)
    public Response getTransactions(@Context ServletContext servletContext, @PathParam("clientid") String clientid) {
        StringBuilder builder = new StringBuilder();
        builder.append(UIConstants.HTML_START);
        builder.append(UIConstants.ELEMENT_HEADER.replace("/assets", servletContext.getContextPath() + "/assets"));
        builder.append(UIConstants.BODY_START);
        builder.append(String.format(UIConstants.ELEMENT_TOP_BAR.replace("/assets", servletContext.getContextPath() + "/assets"), servletContext.getContextPath() + "/home", clientid));

        JsonArray array = DatasourceManager.readArray(Constants.PATH_DATA_TRANSACTIONS);
        String filteredFormatted = DatasourceManager.getFormattedTramsactions(array, clientid, servletContext.getContextPath());

        builder.append(String.format(UIConstants.CONTENT_TRANSACTIONS_TABLE, "Transactions", "Transactions", filteredFormatted));
        builder.append("</div>");
        builder.append(UIConstants.ELEMENT_FOOTER);
        builder.append(UIConstants.ELEMENT_JAVASCRIPT.replace("/assets", servletContext.getContextPath() + "/assets"));
        builder.append(UIConstants.BODY_END);
        builder.append(UIConstants.HTML_END);
        return Response.status(200).entity(builder.toString()).build();
    }

    @GET
    @Path("/details/{clientId}")
    @Produces(MediaType.TEXT_HTML)
    public Response getTransactionDetails(@Context ServletContext servletContext, @PathParam("clientId") String clientId, @QueryParam("id") String id, @QueryParam("transaction") String transaction) {
        StringBuilder builder = new StringBuilder();
        builder.append(UIConstants.HTML_START);
        builder.append(UIConstants.ELEMENT_HEADER.replace("/assets", servletContext.getContextPath() + "/assets"));
        builder.append(UIConstants.BODY_START);
        builder.append(String.format(UIConstants.ELEMENT_TOP_BAR.replace("/assets", servletContext.getContextPath() + "/assets"), servletContext.getContextPath() + "/home", clientId));

        String viewProductsUrl = servletContext.getContextPath() + "/webresources/products/products/" + id + "/" + clientId + "/";
        String addProductsUrl = servletContext.getContextPath() + "/addproduct.html?transid=" + id + "&client=" + clientId + "&trans=" + transaction;
        builder.append(String.format(UIConstants.CONTENT_TRANSACTIONS_DETAILS, id, clientId, transaction, viewProductsUrl, addProductsUrl, servletContext.getContextPath() + "/home"));
        builder.append("</div>");
        builder.append(UIConstants.ELEMENT_FOOTER);
        builder.append(UIConstants.ELEMENT_JAVASCRIPT.replace("/assets", servletContext.getContextPath() + "/assets"));
        builder.append(UIConstants.BODY_END);
        builder.append(UIConstants.HTML_END);
        return Response.status(200).entity(builder.toString()).build();
    }

    @Path("/alltrans")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getALLTrans() {
        JsonArray storeData102 = null;
        String fname102 = Constants.PATH_DATA_TRANSACTIONS;
        storeData102 = DatasourceManager.readArray(fname102);
        String fileData102 = DatasourceManager.allTransactions(storeData102);
        //output =fileData;
        return fileData102;
    }
}
