package com.example.tidsrejseagenturet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    public void onCustomerAdministrationClicked(ActionEvent actionEvent) {
        SceneSwitcher.switchTo(actionEvent, "CustomerAdministration.fxml");
    }

    @FXML
    public void onBookingClicked(ActionEvent actionEvent) {
        SceneSwitcher.switchTo(actionEvent, "Booking.fxml");
    }

    @FXML
    public void onTimeMachinesClicked(ActionEvent actionEvent) {
        SceneSwitcher.switchTo(actionEvent, "TimeMachine.fxml");
    }

    @FXML
    public void onTimePeriodsClicked(ActionEvent actionEvent) {
        SceneSwitcher.switchTo(actionEvent, "TimePeriod.fxml");
    }

}
