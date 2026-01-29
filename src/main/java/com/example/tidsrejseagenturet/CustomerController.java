package com.example.tidsrejseagenturet;

import javafx.event.ActionEvent;

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
}
