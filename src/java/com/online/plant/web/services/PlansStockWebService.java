package com.online.plant.web.services;

import com.online.plant.data.DatasourceManager;
import com.online.plant.models.PlantStock;
import java.util.List;
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
@Path("/stocks")
public class PlansStockWebService {

    /**
     * Creates a new instance of ViewAll
     */
    public PlansStockWebService() {
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlantStocks() {
        List<PlantStock> plants = DatasourceManager.loadPlantStocks();
        return Response.status(200).entity(DatasourceManager.convert(plants)).build();
    }

    @GET
    @Path("/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTransactionPlant(@PathParam("id") int id, @QueryParam("quantity") int quantity) {
        boolean result = DatasourceManager.updatePlantStock(id, quantity);
        return Response.status(200).entity(result ? "Updated Success" : "Not Found").build();
    }
}
