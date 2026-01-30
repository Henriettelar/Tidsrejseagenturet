package com.example.tidsrejseagenturet;

import Model.Customer;
import Model.DB.DBConnection;
import Model.DB.DBRepo;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

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
        Customer selected = customerTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            return;
        }

            String newName = nameText.getText() == null ? "" : nameText.getText().trim();
            String newEmail = emailText.getText() == null ? "" : emailText.getText().trim();

            if (newName.isEmpty() || newEmail.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Navn og email skal udfyldes før opdatering", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            int id = selected.getCustomerId();

            Task<Boolean> task = new Task<>() {
                @Override
                protected Boolean call()  {
                    return dbRepo.updateCustomer(id, newName, newEmail);
                }
            };

        task.setOnSucceeded(evt -> {
            boolean ok = Boolean.TRUE.equals(task.getValue());
            if (ok) {
                Customer updated = new  Customer(id, newName, newEmail);
                int index = customers.indexOf(selected);
                if (index >= 0) {
                    customers.set(index, updated); // Udskift listen med den opdateret version
                } else {
                    //ellers load det hele fra databasen
                    loadCustomersFromDatabase();
                }
                clearInputs();
                new Alert(Alert.AlertType.INFORMATION, "Kunden er opdateret.", ButtonType.OK).showAndWait();
            } else {
                new Alert(Alert.AlertType.ERROR, "Opdatering mislykkedes.", ButtonType.OK).showAndWait();
            }
        });

        task.setOnFailed(evt -> {
            task.getException().printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fejl ved opdatering: " + task.getException().getMessage(), ButtonType.OK).showAndWait();
        });
        new Thread(task).start();
    }

    public void onDeleteClicked(ActionEvent actionEvent) {
        Customer selected = customerTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            return;
        }

        //Ekstra til at spørge om de er sikker på at de vil slette kunden
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Vil du slette denne kunde?", ButtonType.YES, ButtonType.NO);
        confirm.setTitle("Bekræft slet");
        confirm.setHeaderText(null);

        confirm.showAndWait().ifPresent(btn -> {
            if (btn == ButtonType.YES) {
                Task<Boolean> task = new Task<>(){ //Så den kører i baggrunden uden at UI fryser
                    @Override
                    protected Boolean call() {
                        return dbRepo.deleteCustomer(selected.getCustomerId());
                    }
                };
                task.setOnSucceeded(evt ->{
                    Boolean deleted = task.getValue();
                    if (Boolean.TRUE.equals(deleted)) {
                        customers.remove(selected);
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Kunne ikke slette kunde i databasen.", ButtonType.OK).showAndWait();
                    }
                });

                task.setOnFailed(evt ->{
                    task.getException().printStackTrace();
                    new Alert(Alert.AlertType.ERROR, "Fejl i sletning: " + task.getException().getMessage(), ButtonType.OK).showAndWait();
                });

                new Thread(task).start();
            }
        });
    }


    private void loadCustomersFromDatabase() {
        customers.clear();
        List<Customer> all = dbRepo.getAllCustomers();
        customers.addAll(all);
    }

    private void clearInputs() {
        idText.clear();
        nameText.clear();
        nameText.setStyle("");
        emailText.clear();
        emailText.setStyle("");
    }
}

