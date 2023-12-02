/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.online.plant.resources;

import com.online.plant.constants.Constants;
import javax.json.JsonArray;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author ayman
 */
@WebService(serviceName = "apsoap")
public class apsoap {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAllProducts")
    public String getAllProducts() {
        JsonArray storeData101 = null;
        String filteredData101 = null;
        String fname101 = Constants.PATH_DATA_PLANTS;
        String output101 = "";
        storeData101 = DatasourceManager.readArray(fname101);
        String fileData101 = DatasourceManager.allProduct(storeData101);
        output101 = fileData101;
        return output101;
    }
    
    
}
