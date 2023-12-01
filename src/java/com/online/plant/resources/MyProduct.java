/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.online.plant.resources;

import com.online.plant.constants.Constants;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author ayman
 */
@Path("myproduct")
public class MyProduct {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AddProduct
     */
    public MyProduct() {
    }

    @Path("/add")
    @POST
    @Produces("text/html")
    public Response addProducts(@FormParam("id") String id,
            @FormParam("clientid") String clientid,
            @FormParam("product") String product,
            @FormParam("quantity") String quantity,
            @FormParam("status") String status,
            @FormParam("transid") String transid) {
        String output = "<font fac=e='verdana' size='2'>"
                + "you try to add thr following information"
                + " id - <u>" + id + "</u>, clientid - <u>" + clientid + "</u>"
                + "product - <u>" + product + "</u></font>";
        JsonArray newArr = null;
        JsonArray storeData = null;
        String fname = Constants.PATH_DATA_PRODUCTS;
        storeData = DatasourceManager.readArray(fname);
        newArr = DatasourceManager.addJsonToArray(storeData,
        DatasourceManager.createProductObject(id, clientid, product, quantity, status, transid));
        String balance= DatasourceManager.updateQuantity(product,quantity,"City center");
        String headmsg="";
        if(Integer.parseInt(balance)<0){
            headmsg= "Sorry, no available amount";
        }else{
           headmsg="the product is Added Successully";
           DatasourceManager.persist(newArr, fname);
           String fileData = DatasourceManager.tellMeAbout(newArr);
        }
         output = output + "<h1><Center"+headmsg+"</Center></h1> <br>id= "
                    + id + "<br>name= " + clientid + "<br>product= " + product + " :<br>"
                    // + fileData+""
                    + "<br><a href=\"..\\..\\client.html\">back to home</a></body></html>";
            return Response.status(200).entity(output).build();
       // }
    }


    @Path("/edit")
    @POST
    @Produces("text/html")
    public Response editProducts(@FormParam("id") String id,
            @FormParam("product") String product) {
        String output = "<font fac=e='verdana' size='2'>"
                + "the Product modified  with"
                + " id - <u>" + id + "</u>,"
                + "product - <u>" + product + "</u></font>";
        JsonArray newArr = null;
        JsonArray storeData = null;
        String fname = Constants.PATH_DATA_PRODUCTS;

        storeData = DatasourceManager.readArray(fname);
        JsonObject productobj = DatasourceManager.getProduct(id, storeData);

        newArr = DatasourceManager.updateProduct(id, product, storeData, productobj);
        DatasourceManager.persist(newArr, fname);
        String fileData = DatasourceManager.tellMeAbout(newArr);
        output = output + "<h1>the product is Added Successully</h1> <br>id= "
                + id + "product= " + product + " :<br>"
                + ""
                + "<br><a href=\"..\\..\\client.html\">back to home</a></body></html>";
        return Response.status(200).entity(output).build();
    }

    /**
     * Retrieves representation of an instance of res.MyProduct
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    @GET
    @Path("/delete/{id}/{client}/{transid}")
    @Produces(MediaType.TEXT_HTML)

    public Response deleteProducts(@PathParam("id") String id,
            @PathParam("client") String client,
            @PathParam("transid") String transid) {
        System.out.print(id + "           &&&&&&&&&&&&&");
        JsonArray storeData = null, filteredData = null;
        String fname = Constants.PATH_DATA_PRODUCTS;
        String output = "<font face='verdana' size='12'>"
                + "Online Plant"
                + "</font>";
        storeData = DatasourceManager.readArray(fname);
        filteredData = DatasourceManager.deleteProduct(storeData, id, client, transid);
        DatasourceManager.persist(filteredData, fname);
        output = output + "<h1>the Product deleted product " + id + "</h1>"
                + "<table border=\"1\"> <thead><tr><th>id</th><th>name</th>"
                + "<th>Products</th><th>quantity</th><th>status</th><th>transid</th>"
                + "</tr></thead> <tbody>"
                //+fileData
                + "</tbody> </table>"
                + "<br><a href=\"..\\..\\index.html\">back to home</a>"
                + "</body></html>";
        return Response.status(200).entity(output).build();
    }

    /**
     * PUT method for updating or creating an instance of MyProduct
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}