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
        switchScene(actionEvent, "CustomerAdministration.fxml");
    }

    @FXML
    public void onBookingClicked(ActionEvent actionEvent) {
        switchScene(actionEvent, "Booking.fxml");
    }

    @FXML
    public void onTimeMachinesClicked(ActionEvent actionEvent) {
        switchScene(actionEvent, "TimeMachines.fxml");
    }

    @FXML
    public void onTimePeriodsClicked(ActionEvent actionEvent) {
        switchScene(actionEvent, "TimePeriods.fxml");
    }

    //Hjælp til at kunne skifte mellem de forskellige scener
    private void switchScene(ActionEvent event, String fxmlFileName) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFileName));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = stage.getScene();
            if (scene == null) {
                scene = new Scene(root);
                stage.setScene(scene);
            } else {
                scene.setRoot(root);
            }
            stage.sizeToScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
