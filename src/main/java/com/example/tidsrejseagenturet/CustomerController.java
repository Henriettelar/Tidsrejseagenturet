package com.example.tidsrejseagenturet;

import Model.Customer;
import Model.DB.DBConnection;
import Model.DB.DBRepo;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CustomerController {
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, Integer> tableCustomerId;
    @FXML
    private TableColumn<Customer, String> tableCustomerName;
    @FXML
    private TableColumn<Customer, String> tableCustomerEmail;

    @FXML
    private TextField idText;
    @FXML
    private TextField nameText;
    @FXML
    private TextField emailText;

    private ObservableList<Customer> customers =  FXCollections.observableArrayList();
    private final DBConnection db = new DBConnection();
    private final DBRepo dbRepo = new DBRepo(db);

    @FXML
    private void initialize(){
        //Får vores tabels kolonner til at hænge sammen med Customer-klassens gettere
        tableCustomerId.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getCustomerId()).asObject());
        tableCustomerName.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
        tableCustomerEmail.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEmail()));

        customerTable.setItems(customers);

        //Loader kunder fra databasen
        loadCustomersFromDatabase();
    }


    public void onBackClicked(ActionEvent actionEvent) {
        SceneSwitcher.switchTo(actionEvent, "WelcomeMenu.fxml");
    }

    public void onAddClicked(ActionEvent actionEvent) {

        boolean invalid = false;

        if (nameText.getText() == null || nameText.getText().trim().isEmpty()) {
            nameText.setPromptText("Skriv navn");
            nameText.setStyle("-fx-prompt-text-fill: red;");
            invalid = true;
        }

        if (emailText.getText() == null || emailText.getText().trim().isEmpty()) {
            emailText.setPromptText("Skriv email");
            emailText.setStyle("-fx-prompt-text-fill: red;");
            invalid = true;
        }

        if (invalid) {
            return;
        }

        String name = nameText.getText().trim();
        String email = emailText.getText().trim();

        // Gem i Database
        int generatedId = dbRepo.addCustomer(name, email);
        if (generatedId <= 0) {
            // Insert failed — inform the user and don't add to the table
            System.err.println("Failed to insert customer into DB.");
            // Optionally show an Alert dialog to the user
            return;
        }

        // tilføj til tabellenn
        Customer newCustomer = new Customer(generatedId, name, email);
        customers.add(newCustomer);

        // Rydder teksterne i de brugte felter og sætter teksten tilbage til default
        clearInputs();


    }

    public void onEditClicked(ActionEvent actionEvent) {
        customerTable.getSelectionModel().select(customerTable.getSelectionModel().getSelectedIndex());

    }

    public void onDeleteClicked(ActionEvent actionEvent) {
    }

    private int parseIdOrDefault(String text, int defaultVal) {
        if (text == null || text.trim().isEmpty()) return defaultVal;
        try {
            return Integer.parseInt(text.trim());
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }

    private void loadCustomersFromDatabase() {
        customers.clear();
        customers.addAll(dbRepo.getAllCustomers());
    }

    private void clearInputs() {
        idText.clear();
        nameText.clear();
        nameText.setStyle("");
        emailText.clear();
        emailText.setStyle("");
    }
}
