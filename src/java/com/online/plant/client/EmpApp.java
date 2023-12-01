package com.online.plant.client;

import com.online.plant.resources.ProductClient;
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

        Label statusListL101 = new Label("status: ");
        Label ProductsL101 = new Label("Product Number: ");
        Label NameL101 = new Label("Product Name: ");
        Label quantityL101 = new Label("Quantity : ");

        Label branchL101 = new Label("branch: ");
        Label branchL2101 = new Label("branch: ");

        TextField ProductTF101 = new TextField();
        TextField NameTF101 = new TextField();
        TextField quantityTF101 = new TextField();

        Button btgetAllProduct101 = new Button(" Show Product Order");
        Button btgetAllStore101 = new Button(" Show Store Product");

        Button btnorder101 = new Button(" Update Order");
        Button btnStore101 = new Button(" Update Store");

        ComboBox statuscomboBox101 = new ComboBox();
        statuscomboBox101.getItems().add("sold");
        statuscomboBox101.getItems().add("return");
        statuscomboBox101.getItems().add("available");

        ComboBox deptcomboBox101 = new ComboBox();
        deptcomboBox101.getItems().add("City center");
        deptcomboBox101.getItems().add("Bolivard");
        deptcomboBox101.getItems().add("Down Town");
        deptcomboBox101.getItems().add("Salmiya");

        ComboBox brcomboBox101 = new ComboBox();
        brcomboBox101.getItems().add("City center");
        brcomboBox101.getItems().add("Bolivard");
        brcomboBox101.getItems().add("Down Town");
        brcomboBox101.getItems().add("Salmiya");

        pane.add(btgetAllProduct101, 0, 1);
        pane.add(btgetAllStore101, 1, 1);

        pane.add(ProductsL101, 0, 2);
        pane.add(ProductTF101, 1, 2);
        pane.add(statusListL101, 2, 2);
        pane.add(statuscomboBox101, 3, 2);
        pane.add(branchL101, 4, 2);
        pane.add(deptcomboBox101, 5, 2);
        pane.add(btnorder101, 6, 2);

        pane.add(NameL101, 0, 3);
        pane.add(NameTF101, 1, 3);
        pane.add(quantityL101, 2, 3);
        pane.add(quantityTF101, 3, 3);
        pane.add(branchL2101, 4, 3);
        pane.add(brcomboBox101, 5, 3);
        pane.add(btnStore101, 6, 3);

        btnorder101.setOnAction(e -> {
            String product101 = ProductTF101.getText();
            String branch101 = deptcomboBox101.getValue().toString();
            String status101 = statuscomboBox101.getValue().toString();
            EmpApp.callRESTUpdateStatus(product101, status101, branch101);
            String msg101 = product101 + "\n" + branch101 + "\n" + status101;

            try {
                Alert alertType101 = new Alert(AlertType.INFORMATION);
                alertType101.setContentText(msg101);
                alertType101.showAndWait();
            } catch (Exception ex) {
                alert.setContentText(" Not Found");
                alert.showAndWait();
            }
            createTableProducts(EmpApp.callRESTProduct());

        });

        btnStore101.setOnAction(e -> {
            String name101 = NameTF101.getText();
            String br101 = brcomboBox101.getValue().toString();
            String quantity101 = quantityTF101.getText();

            String msg101 = name101 + "\n" + br101 + "\n" + quantity101;

            System.out.println(msg101);

            System.out.print("&&&&&&&&&&&&&11111111111&&&&&&&&&&&&&&&&&&&&&&&&");
            EmpApp.callRESTUpdateStore(name101, quantity101, br101);

            try {
                Alert alertType101 = new Alert(AlertType.INFORMATION);
                alertType101.setContentText(msg101);
                alertType101.showAndWait();
            } catch (Exception ex) {
                alert.setContentText(" Not Found");
                alert.showAndWait();
            }

            createTableStores(EmpApp.callRESTStore());
        });

        btgetAllProduct101.setOnAction(e -> {
            createTableProducts(EmpApp.callRESTProduct());
        });
        btgetAllStore101.setOnAction(e -> {
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

    public void createTableProducts(String result) {
        TableColumn<Product, String> column1 = new TableColumn<>("Id");
        column1.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Product, String> column2
                = new TableColumn<>("Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Product, String> column3
                = new TableColumn<>("Product");
        column3.setCellValueFactory(new PropertyValueFactory<>("Product"));
        TableColumn<Product, String> column4
                = new TableColumn<>("Quantity");
        column4.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        TableColumn<Product, String> column5
                = new TableColumn<>("Status");
        column5.setCellValueFactory(new PropertyValueFactory<>("status"));
        TableColumn<Product, String> column6
                = new TableColumn<>("Transid");
        column6.setCellValueFactory(new PropertyValueFactory<>("transid"));
        TableColumn<Product, String> column7
                = new TableColumn<>("Branch");
        column7.setCellValueFactory(new PropertyValueFactory<>("branch"));
        tableView.getColumns().clear();
        tableView.getItems().clear();
        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
        tableView.getColumns().add(column4);
        tableView.getColumns().add(column5);
        tableView.getColumns().add(column6);
        tableView.getColumns().add(column7);

        String[] rows = result.split("\n");
        for (String row : rows) {
            String[] columns = row.split("-");

            String id = "---";
            String name = "---";
            String Product = "---";
            String quantity = "---";
            String status = "---";
            String transid = "---";
            String branch = "---";
            int index = 1;
            for (String column : columns) {
                switch (index) {
                    case 1:
                        id = column;
                        break;
                    case 2:
                        name = column;
                        break;
                    case 3:
                        Product = column;
                        break;
                    case 4:
                        quantity = column;
                        break;
                    case 5:
                        status = column;
                        break;
                    case 6:
                        transid = column;
                        break;
                    case 7:
                        branch = column;
                        break;
                }
                index++;
            }

            Product product = new Product(id, name, Product, quantity, status, transid, branch);
            tableView.getItems().add(product);
        }
    }

    public void createTableStores(String result) {
        TableColumn<Product, String> column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Product, String> column2
                = new TableColumn<>("Quantity");
        column2.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        TableColumn<Product, String> column3
                = new TableColumn<>("Branch");
        column3.setCellValueFactory(new PropertyValueFactory<>("branch"));
        tableView.getColumns().clear();
        tableView.getItems().clear();
        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);

        String[] rows = result.split("\n");
        for (String row : rows) {
            String[] columns = row.split("-");

            String name = "---";
            String quantity = "---";
            String branch = "---";
            int index = 1;
            for (String column : columns) {
                switch (index) {
                    case 1:
                        name = column;
                        break;
                    case 2:
                        quantity = column;
                        break;
                    case 3:
                        branch = column;
                        break;
                }
                index++;
            }

            Store store = new Store(name, quantity, branch);
            tableView.getItems().add(store);
        }
    }

    public static String callRESTProduct() {
        ProductClient c101 = new ProductClient();
        String result = c101.getAllProducts();
        System.out.println(result);
        return result;
    }

    public static String callRESTStore() {
        ProductClient c101 = new ProductClient();
        String result = c101.getStoreProduct();
        System.out.println(result);
        return result;
    }

    public static String callRESTUpdateStatus(String id, String status, String branch) {
        ProductClient c101 = new ProductClient();
        String result = c101.UpdateStatus(id, status, branch);
        System.out.println(result);
        return result;
    }

    public static String callRESTUpdateStore(String name, String quantity, String branch) {
        ProductClient c101 = new ProductClient();
        String result = c101.UpdateStore(name, quantity, branch);
        System.out.println(result);
        return result;
    }

    public static class Product {

        private String id;
        private String name;
        private String Product;
        private String quantity;
        private String status;
        private String transid;
        private String branch;

        public Product(String id, String name, String Product, String quantity, String status, String transid, String branch) {
            this.id = id;
            this.name = name;
            this.Product = Product;
            this.quantity = quantity;
            this.status = status;
            this.transid = transid;
            this.branch = branch;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProduct() {
            return Product;
        }

        public void setProduct(String Product) {
            this.Product = Product;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTransid() {
            return transid;
        }

        public void setTransid(String transid) {
            this.transid = transid;
        }

        public String getBranch() {
            return branch;
        }

        public void setBranch(String branch) {
            this.branch = branch;
        }

    }

    public static class Store {

        private String name;
        private String quantity;
        private String branch;

        public Store(String name, String quantity, String branch) {
            this.name = name;
            this.quantity = quantity;
            this.branch = branch;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getBranch() {
            return branch;
        }

        public void setBranch(String branch) {
            this.branch = branch;
        }

    }
}// end of class`

