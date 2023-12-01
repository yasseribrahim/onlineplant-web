package com.online.plant.resources;

import com.online.plant.constants.Constants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.json.*;

public class DatasourceManager {

    public static JsonArray readArray(String filename) {
        System.out.println("\n Reading object from file " + filename);
        try {
            JsonReader reader = Json.createReader(new FileReader(filename));
            JsonArray array = reader.readArray();
            System.out.println("Your Array was read from file " + filename);
            return array;
        } catch (FileNotFoundException ex) {
            System.out.println("Couldn't find file " + ex);
            return null;
        }
    }

    public static String getFormattedTramsactions(JsonArray transactions, String clientId, String applicationContext) {
        JsonObject object;
        StringBuilder stringBuilder = new StringBuilder();
        String clean;
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("clientid", clientId);
        JsonObject param102 = builder.build();
        JsonArrayBuilder arrayBuilder102 = Json.createArrayBuilder();
        for (int i = 0; i < transactions.size(); i++) {
            object = transactions.getJsonObject(i);
            if (object.get("clientid").equals(param102.get("clientid"))) {
                arrayBuilder102.add(object);
            }
        }
        JsonArray filtered = arrayBuilder102.build();
        String id, client, transaction;
        for (int i = 0; i < filtered.size(); i++) {
            stringBuilder.append("<tr>");
            object = filtered.getJsonObject(i);
            client = object.get("clientid").toString().replaceAll("\"", "");
            id = object.get("id").toString().replaceAll("\"", "");
            transaction = object.get("Transaction").toString().replaceAll("\"", "");
            System.out.println("current: " + id + " & " + client);
            for (String name : object.keySet()) {
                JsonValue value = object.get(name);// replaceAll("\"", "")              
                clean = value.toString().replaceAll("\"", "");
                stringBuilder.append("<td>").append(clean).append("</td>");
            }
            String url = applicationContext + "/webresources/transactions/details/" + clientId + "/?";
            stringBuilder.append("<td>");
            stringBuilder.append("<a class=\"btn btn-primary\" href=\"").append(url).append("id=").append(id);
            stringBuilder.append("&transaction=").append(transaction).append("\">View Details</a>");
            stringBuilder.append("<td></tr>");

        }
        return stringBuilder.toString();
    }

    public static String getFormattedMyProducts(JsonArray products, String clientId, String transactionId, String applicationContext) {
        StringBuilder stringBuilder = new StringBuilder();
        String clean;
        JsonObjectBuilder jsonObjBuilder102 = Json.createObjectBuilder();
        jsonObjBuilder102.add("transid", transactionId);
        jsonObjBuilder102.add("client", clientId);
        JsonObject param102 = jsonObjBuilder102.build();
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (int i = 0; i < products.size(); i++) {
            JsonObject object = products.getJsonObject(i);
            if (object.get("transid").equals(param102.get("transid")) && object.get("clientid").equals(param102.get("client"))) {
                builder.add(object);
            }
        }
        JsonArray filteredArray = builder.build();
        String id, transid;
        for (int i = 0; i < filteredArray.size(); i++) {
            stringBuilder.append("<tr>");
            JsonObject object = filteredArray.getJsonObject(i);
            id = object.get("id").toString().replaceAll("\"", "");
            transid = object.get("transid").toString().replaceAll("\"", "");
            for (String name : object.keySet()) {
                JsonValue value = object.get(name);              
                clean = value.toString().replaceAll("\"", "");
                System.out.println("cleaned:   " + clean);
                stringBuilder.append("<td>").append(clean).append("</td>");
            }
            
            String deleteUrl = applicationContext + "/webresources/myproduct/delete/";
            String editUrl = applicationContext + "/editproduct.html?";

            stringBuilder.append("<td><a class=\"btn btn-info\" href=\"").append(editUrl).append("id=").append(id).append("&client=").append(clientId).append("\">Edit </a></td>");
            stringBuilder.append("<td><a class=\"btn btn-danger\" href=\"").append(deleteUrl).append(id).append("\\").append(clientId).append("\\").append(transid).append("\">Delete </a><td>");
            stringBuilder.append("</tr>");
        }
        return stringBuilder.toString();
    }

    public static String updateQuantity(String name, String quantity, String branch) {
        String fname = Constants.PATH_DATA_STOCKS;
        JsonArray storeData = DatasourceManager.readArray(fname);
        JsonObject product = DatasourceManager.getStoreProduct(name, storeData);
        String productbalance102 = product.get("quantity").toString().replace("\"", "");
        int balance102 = Integer.parseInt(productbalance102);
        balance102 = balance102 - Integer.parseInt(quantity);
        String newBalance = String.valueOf(balance102);
        if (balance102 < 0) {
            return newBalance;
        } else {
            JsonArray newArr102 = DatasourceManager.updateStore(name, newBalance, branch, storeData, product);
            DatasourceManager.persist(newArr102, fname);
            if (balance102 <= 4 && balance102 >= 0) {
                DatasourceManager.addUrgent102(product.get("name").toString().replace("\"", ""), newBalance);
            }
            return newBalance;
        }
    }

    public static void addUrgent102(String name, String quantity) {
        JsonArray newUrgentArray102 = null;
        JsonArray storeData102 = null;
        String f102 = "fast_sellingJSON.txt";
        storeData102 = DatasourceManager.readArray(f102);
        JsonObject urgentobj102 = DatasourceManager.createUrgentObject(name, quantity);

        newUrgentArray102 = DatasourceManager.addJsonToArray(storeData102, urgentobj102);
        DatasourceManager.persist(newUrgentArray102, f102);
    }

    public static JsonObject getProduct(String id, JsonArray ar) {
        JsonObject obj102 = null;
        String currentval102 = "";
        JsonObject currentObj102 = null;
        JsonArrayBuilder arrayBuilder102 = Json.createArrayBuilder();
        for (int i = 0; i < ar.size(); i++) {
            obj102 = ar.getJsonObject(i);
            currentval102 = obj102.get("id").toString().replace("\"", "");
            if (currentval102.equals(id)) {
                currentObj102 = obj102;
                break;
            }
        }
        return currentObj102;
    }

    public static JsonObject getStoreProduct(String name, JsonArray ar) {
        JsonObject obj102 = null;
        String currentval102 = "";
        JsonObject currentObj102 = null;
        JsonArrayBuilder arrayBuilder102 = Json.createArrayBuilder();
        for (int i = 0; i < ar.size(); i++) {
            obj102 = ar.getJsonObject(i);
            currentval102 = obj102.get("name").toString().replace("\"", "");
            if (currentval102.equals(name)) {
                currentObj102 = obj102;
                break;
            }
        }
        return currentObj102;
    }

    public static JsonArray updateStore(String name, String quantity, String branch, JsonArray jar, JsonObject old) {
        JsonObject updatedObject102;
        updatedObject102 = DatasourceManager.updateJsonObj(old, "quantity", quantity);
        updatedObject102 = DatasourceManager.updateJsonObj(updatedObject102, "branch", branch);

        System.out.println(updatedObject102);
        JsonObject obj102 = null;
        boolean found = false;
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (int i = 0; i < jar.size(); i++) {
            obj102 = jar.getJsonObject(i);
            if (obj102.get("name").equals(updatedObject102.get("name"))) {
                arrayBuilder.add(updatedObject102);
            } else {
                arrayBuilder.add(obj102);
            }
        }
        JsonArray newArr102 = arrayBuilder.build();
        return newArr102;
    }

    public static boolean hasProduct(String transid, JsonArray ar) {
        JsonObject obj = null;
        String currentval = "";
        JsonObject currentObj = null;
        boolean found = false;
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (int i = 0; i < ar.size(); i++) {
            obj = ar.getJsonObject(i);
            currentval = obj.get("transid").toString().replace("\"", "");
            if (currentval.equals(transid)) {
                found = true;
                break;
            }
        }
        return found;
    }

    public static JsonArray updateProductStatus(String id, String status, String branch, JsonArray jar, JsonObject old) {
        JsonObject updatedObject102;
        updatedObject102 = DatasourceManager.updateJsonObj(old, "status", status);
        updatedObject102 = DatasourceManager.updateJsonObj(updatedObject102, "branch", branch);

        System.out.println(updatedObject102);
        JsonObject obj102 = null;
        boolean found102 = false;
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (int i = 0; i < jar.size(); i++) {
            obj102 = jar.getJsonObject(i);
            if (obj102.get("id").equals(updatedObject102.get("id"))) {
                arrayBuilder.add(updatedObject102);
            } else {
                arrayBuilder.add(obj102);
            }
        }
        JsonArray newArr102 = arrayBuilder.build();
        return newArr102;
    }

    public static String getProducts(JsonArray ar) {
        String arrData102 = "", clean102 = "";
        for (int i = 0; i < ar.size(); i++) {
            JsonObject obj102 = ar.getJsonObject(i);
            for (String name102 : obj102.keySet()) {
                JsonValue jv102 = obj102.get(name102);// replaceAll("\"", "")
                clean102 = jv102.toString().replaceAll("\"", "");
                arrData102 = arrData102 + clean102 + "-";
            }
            arrData102 = arrData102 + "\n";
        }
        return arrData102;
    }

    public static String getStores(JsonArray ar) {
        String arrData102 = "", clean = "";
        for (int i = 0; i < ar.size(); i++) {
            JsonObject obj102 = ar.getJsonObject(i);
            for (String name102 : obj102.keySet()) {
                JsonValue jv102 = obj102.get(name102);// replaceAll("\"", "")
                clean = jv102.toString().replaceAll("\"", "");
                arrData102 = arrData102 + clean + "-";
            }
            arrData102 = arrData102 + "\n";
        }
        return arrData102;
    }

    public static String allProduct(JsonArray ar) {
        String arrData102 = "id \t \t    name \t \t \t Product \t \t \t"
                + "quantity \t \t \t status \t \t \t transid \t \t \t branch \t \t \t \n"
                + "----------------------------------------------------"
                + "--------------------------------------------------- \n"
                + "", clean102 = "";
        for (int i = 0; i < ar.size(); i++) {
            JsonObject obj102 = ar.getJsonObject(i);
            for (String name102 : obj102.keySet()) {
                JsonValue jv102 = obj102.get(name102);// replaceAll("\"", "")
                clean102 = jv102.toString().replaceAll("\"", "");
                arrData102 = arrData102 + clean102 + "\t \t\t";
            }
            arrData102 = arrData102 + "\n";
        }
        return arrData102;
    }

    public static String allStores(JsonArray ar) {
        String arrData102 = "id \t \t    name \t \t \t quantity \t \t \t"
                + "branch \t \t \t  \t \t \t \n"
                + "---------------------------------------------------"
                + "--------------------------------------------------\n"
                + "", clean = "";
        for (int i = 0; i < ar.size(); i++) {
            JsonObject obj102 = ar.getJsonObject(i);
            for (String name102 : obj102.keySet()) {
                JsonValue jv102 = obj102.get(name102);// replaceAll("\"", "")
                clean = jv102.toString().replaceAll("\"", "");
                arrData102 = arrData102 + clean + "\t \t\t";
            }
            arrData102 = arrData102 + "\n";
        }
        return arrData102;
    }

    public static String allTransactions(JsonArray ar) {
        String arrData102 = "", clean = "";
        for (int i = 0; i < ar.size(); i++) {
            JsonObject obj102 = ar.getJsonObject(i);
            for (String name102 : obj102.keySet()) {
                JsonValue jv102 = obj102.get(name102);// replaceAll("\"", "")
                clean = jv102.toString().replaceAll("\"", "");
                arrData102 = arrData102 + clean + " ";
            }
            arrData102 = arrData102 + "\n";
        }
        return arrData102;
    }

    public static String transactionList(JsonArray ar) {
        String arrData102 = "", clean = "";
        System.out.println("\n About your array ......................................");
        for (int i = 0; i < ar.size(); i++) {
            arrData102 = arrData102 + "<tr>";
            JsonObject obj = ar.getJsonObject(i);
            for (String name : obj.keySet()) {
                JsonValue jv = obj.get(name);// replaceAll("\"", "")
                clean = jv.toString().replaceAll("\"", "");
                System.out.println("cleaned:   " + clean);
                arrData102 = arrData102 + "<td><a href=>" + clean + "</a></td><td>"
                        + "<a href=\"..\\..\\mytrans.html\">select </a></td>";
            }
            arrData102 = arrData102 + "</tr>";
        }
        return arrData102;
    }

    public static JsonArray addJsonToArray(JsonArray jar, JsonObject job) {
        JsonObject obj102 = null;
        boolean found = false;
        JsonArrayBuilder arrayBuilder102 = Json.createArrayBuilder();
        for (int i = 0; i < jar.size(); i++) {
            obj102 = jar.getJsonObject(i);
            arrayBuilder102.add(obj102);
        }
        arrayBuilder102.add(job);
        JsonArray newArr = arrayBuilder102.build();
        return newArr;
    }

    public static JsonObject updateJsonObj(JsonObject obj, String objKey, String newval) {
        JsonObjectBuilder jobuilder = Json.createObjectBuilder();
        for (String key : obj.keySet()) {
            if (key.equals(objKey)) {
                jobuilder.add(key, newval);
            } else {
                jobuilder.add(key, obj.get(key));
            }
        }
        obj = jobuilder.build();
        return obj;
    }

    public static JsonArray deleteProduct(JsonArray data, String id, String client, String transid) {
        JsonObject obj = null;
        JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
        jsonObjBuilder.add("id", id);
        JsonObject param = jsonObjBuilder.build();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (int i = 0; i < data.size(); i++) {
            obj = data.getJsonObject(i);
            if (!obj.get("id").equals(param.get("id"))) {
                arrayBuilder.add(obj);
            }

        }
        JsonArray newArr = arrayBuilder.build();
        return newArr;
    }

    public static JsonArray createJasonArray(JsonObject obj) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        arrayBuilder.add(obj);
        JsonArray ar = arrayBuilder.build();
        return ar;
    }

    public static JsonObject createProductObject(String id, String clientid, String product,
            String quantity, String status, String transid) {
        JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
        jsonObjBuilder.add("id", id);
        jsonObjBuilder.add("clientid", clientid);
        jsonObjBuilder.add("product", product);
        jsonObjBuilder.add("quantity", quantity);
        jsonObjBuilder.add("status", status);
        jsonObjBuilder.add("transid", transid);
        jsonObjBuilder.add("branch", ".");

        JsonObject jsonObj = jsonObjBuilder.build();
        return jsonObj;
    }

    public static JsonObject createUrgentObject(String name, String quantity) {
        JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
        jsonObjBuilder.add("name", name);
        jsonObjBuilder.add("quantity", quantity);
        JsonObject jsonObj = jsonObjBuilder.build();
        return jsonObj;
    }

    public static void persist(JsonArray obj, String filename) {
        try (JsonWriter jsonWriter = Json.createWriter(new FileWriter(filename))) {
            jsonWriter.writeArray(obj);
            System.out.println("Object was written to " + filename);
        } catch (IOException ex) {
            System.out.println("Writing object failed... " + ex);
        }
    }

    public static String tellMeAbout(JsonArray ar) {
        String arrData = "", clean = "";
        System.out.println("\n About your array ......................................");
        for (int i = 0; i < ar.size(); i++) {
            arrData = arrData + "<tr>";
            JsonObject obj = ar.getJsonObject(i);
            for (String name : obj.keySet()) {
                JsonValue jv = obj.get(name);// replaceAll("\"", "")
                clean = jv.toString().replaceAll("\"", "");
                System.out.println("cleaned:   " + clean);
                arrData = arrData + "<td>" + clean + "</tc>";
            }
            arrData = arrData + "</tr>";

        }
        return arrData;
    }

    public static String tellMeAbout(JsonObject obj) {
        String data = "", clean = "";
        System.out.println("\nAbout your object ......................................");
        for (String name : obj.keySet()) {
            JsonValue jv = obj.get(name);
            clean = jv.toString().replaceAll("\"", "");

            data = data + "<br>" + name + "\n";
            System.out.printf("%-20s is type %-10s with value %s%n", name, jv.getValueType(), jv);
        }
        return data;
    }

    public static void ArrayIterate(JsonArray ar) {
        for (int i = 0; i < ar.size(); i++) {
            JsonValue jv = (JsonValue) ar.get(i);
            JsonObject j = ar.getJsonObject(i);
            System.out.printf("At index %s is %8s  %-10s%n", i, jv.getValueType(), jv);

        }
    }

    public static void DeleteFile(String filename) {
        File myObj = new File(filename);
        if (myObj.delete()) {
            System.out.println("Deleted the file: " + myObj.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }

    public static void main(String[] args) {
        String output = "";
        JsonArray newArr = null;
        JsonArray storeData = null;
        String fname = "C:\\Users\\user\\AppData\\Roaming\\NetBeans\\8.2rc\\config\\GF_4.1.1\\domain1\\config\\ProductsJSON.txt";
        storeData = DatasourceManager.readArray(fname);
        System.out.println(storeData);
        JsonObject product = DatasourceManager.getProduct("20", storeData);
        newArr = DatasourceManager.updateProductStatus("20", "pending", "it", storeData, product);

        // RESTcomplaintClient c = new RESTcomplaintClient();
        // String result = c.getAllComplaints();
        // String update = c.UpdateStatus("1", "done", "IT");
        //  System.out.println(update);     
    }

    public static JsonArray updateProduct(String id, String product, JsonArray jar, JsonObject old) {
        JsonObject updatedObject;
        updatedObject = DatasourceManager.updateJsonObj(old, "product", product);
        System.out.println(updatedObject);
        JsonObject obj = null;
        boolean found = false;
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (int i = 0; i < jar.size(); i++) {
            obj = jar.getJsonObject(i);
            if (obj.get("id").equals(updatedObject.get("id"))) {
                arrayBuilder.add(updatedObject);
            } else {
                arrayBuilder.add(obj);
            }
        }
        JsonArray newArr = arrayBuilder.build();
        return newArr;
    }

}
