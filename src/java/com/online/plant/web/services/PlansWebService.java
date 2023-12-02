package com.online.plant.web.services;

import com.online.plant.data.DatasourceManager;
import com.online.plant.models.Plant;
import java.util.ArrayList;
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
@Path("/plants")
public class PlansWebService {

    /**
     * Creates a new instance of ViewAll
     */
    public PlansWebService() {
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlants() {
        List<Plant> plants = DatasourceManager.loadPlants();
        return Response.status(200).entity(DatasourceManager.convert(plants)).build();
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlants(@QueryParam("search") String search) {
        List<Plant> plants = DatasourceManager.loadPlants();
        List<Plant> filtered = new ArrayList<>();
        if (search != null && !search.isEmpty()) {
            for (Plant plant : plants) {
                if (plant.getName().toLowerCase().contains(search.toLowerCase()) || search.equals(plant.getType()) || search.equals(plant.getAge() + "") || search.equals(plant.getPrice() + "")) {
                    filtered.add(plant);
                }
            }
        } else {
            filtered.addAll(plants);
        }
        return Response.status(200).entity(DatasourceManager.convert(filtered)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransactionDetails(@PathParam("id") int id) {
        List<Plant> plants = DatasourceManager.loadPlants();
        for (Plant plant : plants) {
            if (plant.getId() == id) {
                return Response.status(200).entity(DatasourceManager.convert(plant)).build();
            }
        }
        return Response.status(404).entity("Not Found").build();
    }
}
