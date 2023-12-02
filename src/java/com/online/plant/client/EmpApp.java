package com.online.plant.client;

import com.online.plant.client.call.PlantStocksClient;
import com.online.plant.client.call.PlantsClient;
import com.online.plant.client.call.TransactionsClient;
import com.online.plant.models.Plant;
import com.online.plant.models.PlantStock;
import com.online.plant.models.Transaction;
import com.online.plant.models.TransactionDetails;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EmpApp extends Application {

    private TableView tableView;

    @Override
    public void start(Stage primaryStage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        GridPane pane = new GridPane();
        BorderPane root = new BorderPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(5);
        pane.setHgap(5);
        pane.setAlignment(Pos.CENTER);

        tableView = new TableView();
        // insert table here
        root.setBottom(pane);
        root.setTop(tableView);

        Label lblSearch = new Label("Plants Search: ");
        TextField txtSearchPlants = new TextField();
        Button btnSearchPlants = new Button("Search Plants");

        Label lblPlantId = new Label("Plant Id: ");
        Label lblQuantity = new Label("Quantity : ");
        TextField txtPlantId = new TextField();
        TextField txtQuantity = new TextField();

        Button btnShowPlants = new Button("Show Plants");
        Button btnShowTransactions = new Button("Show Transactions");
        Button btnShowStorePlants = new Button("Show Store Plants");

        Button btnStore = new Button("Update Store");

        pane.add(btnShowPlants, 0, 1, 2, 1);
        pane.add(btnShowTransactions, 1, 1, 2, 1);
        pane.add(btnShowStorePlants, 2, 1, 2, 1);

        pane.add(lblSearch, 0, 2);
        pane.add(txtSearchPlants, 1, 2, 3, 1);
        pane.add(btnSearchPlants, 5, 2);

        pane.add(lblPlantId, 0, 3);
        pane.add(txtPlantId, 1, 3);
        pane.add(lblQuantity, 2, 3);
        pane.add(txtQuantity, 3, 3);
        pane.add(btnStore, 4, 3);

        btnSearchPlants.setOnAction(e -> {
            String search = txtSearchPlants.getText();
            PlantsClient client = new PlantsClient();
            List<Plant> plants = client.getPlants(search);

            try {
                Alert alert1 = new Alert(AlertType.INFORMATION);
                alert1.setContentText("Result Size: " + plants.size());
                alert1.showAndWait();
            } catch (Exception ex) {
                alert.setContentText(" Not Found");
                alert.showAndWait();
            }
            createTablePlants(plants);
        });

        btnStore.setOnAction(e -> {
            int id = 0, quantity = 0;
            String idStr = txtPlantId.getText();
            String quantityStr = txtQuantity.getText();
            try {
                id = Integer.parseInt(idStr);
            } catch (Exception ex) {
            }
            try {
                quantity = Integer.parseInt(quantityStr);
            } catch (Exception ex) {
            }

            if (id <= 0) {
                Alert alert1 = new Alert(AlertType.INFORMATION);
                alert1.setContentText("Invalid Plant Id Value!!!!");
                alert1.showAndWait();
                return;
            }

            if (quantity <= 0) {
                Alert alert1 = new Alert(AlertType.INFORMATION);
                alert1.setContentText("Invalid Quantity Value!!!!");
                alert1.showAndWait();
                return;
            }

            PlantStocksClient client = new PlantStocksClient();
            String response = client.updatePlantStock(id, quantity);

            try {
                Alert alert1 = new Alert(response.contains("Success") ? AlertType.INFORMATION : AlertType.ERROR);
                alert1.setContentText(response);
                alert1.showAndWait();
            } catch (Exception ex) {
                alert.setContentText(" Not Found");
                alert.showAndWait();
            }

            createTableStores(EmpApp.callRESTStore());
        });

        btnShowPlants.setOnAction(e -> {
            createTablePlants(EmpApp.callRESTPlants());
        });

        btnShowTransactions.setOnAction(e -> {
            createTableTransactions(EmpApp.callRESTTransactions());
        });
        btnShowStorePlants.setOnAction(e -> {
            createTableStores(EmpApp.callRESTStore());
        });

        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Online Plant");
        primaryStage.setScene(scene);
        primaryStage.show();
    }// end of start

    public static void main(String[] args) {
        launch(args);
    }

    public void createTablePlants(List<Plant> plants) {
        TableColumn<Plant, String> column1 = new TableColumn<>("Id");
        column1.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Plant, String> column2
                = new TableColumn<>("Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Plant, String> column3
                = new TableColumn<>("Type");
        column3.setCellValueFactory(new PropertyValueFactory<>("type"));
        TableColumn<Plant, String> column4
                = new TableColumn<>("Age");
        column4.setCellValueFactory(new PropertyValueFactory<>("age"));
        TableColumn<Plant, String> column5
                = new TableColumn<>("Price");
        column5.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableView.getColumns().clear();
        tableView.getItems().clear();
        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
        tableView.getColumns().add(column4);
        tableView.getColumns().add(column5);

        tableView.getItems().addAll(plants);
    }

    public void createTableStores(List<PlantStock> plants) {
        TableColumn<PlantStock, String> column1 = new TableColumn<>("Plant Id");
        column1.setCellValueFactory(new PropertyValueFactory<>("plantId"));
        TableColumn<PlantStock, String> column2
                = new TableColumn<>("Plant Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("plantName"));
        TableColumn<PlantStock, String> column3
                = new TableColumn<>("Quantity");
        column3.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tableView.getColumns().clear();
        tableView.getItems().clear();
        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);

        tableView.getItems().addAll(plants);
    }

    public void createTableTransactions(List<Transaction> transactions) {
        TableColumn<Transaction, String> column1 = new TableColumn<>("Id");
        column1.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Transaction, String> column2
                = new TableColumn<>("Seller Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("sellerId"));
        TableColumn<Transaction, String> column3
                = new TableColumn<>("Buyer Name");
        column3.setCellValueFactory(new PropertyValueFactory<>("buyerId"));
        TableColumn<Transaction, String> column4
                = new TableColumn<>("Date");
        column4.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableView.getColumns().clear();
        tableView.getItems().clear();
        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
        tableView.getColumns().add(column4);

        tableView.getItems().addAll(transactions);
    }

    public static List<Plant> callRESTPlants() {
        PlantsClient client = new PlantsClient();
        List<Plant> result = client.getPlants();
        return result;
    }

    public static List<Transaction> callRESTTransactions() {
        TransactionsClient client = new TransactionsClient();
        List<Transaction> result = client.getTransactions();
        return result;
    }

    public static List<PlantStock> callRESTStore() {
        PlantStocksClient client = new PlantStocksClient();
        List<PlantStock> result = client.getPlantStockt();
        return result;
    }

    public static String callRESTUpdateStatus(String id, String status, String branch) {
//        ProductClient c101 = new ProductClient();
//        String result = c101.UpdateStatus(id, status, branch);
//        System.out.println(result);
//        return result;

        return "";
    }

    public static String callRESTUpdateStore(String name, String quantity, String branch) {
//        ProductClient c101 = new ProductClient();
//        String result = c101.UpdateStore(name, quantity, branch);
//        System.out.println(result);
//        return result;
        return "";
    }
}// end of class`

