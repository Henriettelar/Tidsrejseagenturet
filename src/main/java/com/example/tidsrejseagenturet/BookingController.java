package com.example.tidsrejseagenturet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class BookingController {
    public void onBackClicked(ActionEvent actionEvent) {
        SceneSwitcher.switchTo(actionEvent, "WelcomeMenu.fxml");
    }

    @FXML
    private ComboBox customerChoice;
    @FXML
    private ComboBox machineChoice;
    @FXML
    private ComboBox periodChoice;
    @FXML
    private ComboBox guideChoice;


    //Fås fra db
    @FXML
    public void initializeCustomer() {
        customerChoice.getItems().addAll();
    }
    @FXML
    public void initializeMachine() {
        machineChoice.getItems().addAll();
    }
    @FXML
    public void initializePeriod() {
        periodChoice.getItems().addAll();
    }
    @FXML
    public void initializeGuide() {
        guideChoice.getItems().addAll();
    }

    public void onBookingReadyClicked(ActionEvent actionEvent) {

    }

    public void handleGuide(ActionEvent actionEvent) {
    }

    public void handlePeriod(ActionEvent actionEvent) {
    }

    public void handleMachine(ActionEvent actionEvent) {
    }

    public void handleCustomer(ActionEvent actionEvent) {
    }
}
