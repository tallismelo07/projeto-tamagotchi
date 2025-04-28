package com.example.projetotamagochi;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TamagotchiApp {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}