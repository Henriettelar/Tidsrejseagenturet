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
    }

    public void onEditClicked(ActionEvent actionEvent) {
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
