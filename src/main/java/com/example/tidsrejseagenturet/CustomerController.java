package com.example.tidsrejseagenturet;

import Model.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CustomerController {
    public void onBackClicked(ActionEvent actionEvent) {
        SceneSwitcher.switchTo(actionEvent, "WelcomeMenu.fxml");
    }

    public void onAddClicked(ActionEvent actionEvent) {

        if (nameText.getText().isEmpty()) {
            nameText.setText("Write name");
            nameText.setStyle("-fx-text-fill: red;");
        } else {
            //add customer to table + database (other class (DBRepo))
        }
        if (emailText.getText().isEmpty()) {
            emailText.setText("Write email");
            emailText.setStyle("-fx-text-fill: red;");
        }


    }

    public void onEditClicked(ActionEvent actionEvent) {
        customerTable.getSelectionModel().select(customerTable.getSelectionModel().getSelectedIndex());

    }

    public void onDeleteClicked(ActionEvent actionEvent) {
    }

    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, String> tableCustomerId;
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
}
