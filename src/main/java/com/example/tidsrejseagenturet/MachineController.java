package com.example.tidsrejseagenturet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MachineController {
    public void onBackClicked(ActionEvent actionEvent) {
        SceneSwitcher.switchTo(actionEvent, "WelcomeMenu.fxml");
    }
    @FXML
    private TableView timeMachines;

    @FXML
    private TableColumn machineName;

    @FXML
    private TableColumn machineCapacity;

    @FXML
    private TableColumn machineStatus;
}
