package com.example.projetotamagochi;

import application.EventosAleatorios;
import application.Tamagotchi;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TamagtchiApp extends Application {
    private Tamagotchi tamagotchi;
    private Label statusLabel;
    private Label climaLabel;

    @Override
    public void start(Stage primaryStage) {
        tamagotchi = new Tamagotchi("Biscoito");

        statusLabel = new Label(getStatusText());
        climaLabel = new Label("Clima: Não está chovendo");
        ImageView tamagotchiImage = new ImageView(new Image("file:src/images/tamagotchi.png"));
        tamagotchiImage.setFitWidth(150);
        tamagotchiImage.setPreserveRatio(true);

        Button btnAlimentar = createStyledButton("Alimentar");
        Button btnBrincar = createStyledButton("Brincar");
        Button btnDormir = createStyledButton("Dormir");
        Button btnBanho = createStyledButton("Tomar Banho");
        Button btnMedicamento = createStyledButton("Medicamento");
        Button btnInteragir = createStyledButton("Interaja");

        btnAlimentar.setOnAction(e -> { tamagotchi.alimentar(); updateStatus(); });
        btnBrincar.setOnAction(e -> { tamagotchi.brincar(); updateStatus(); });
        btnDormir.setOnAction(e -> { tamagotchi.dormir(); updateStatus(); });
        btnBanho.setOnAction(e -> { tamagotchi.tomarBanho(); updateStatus(); });
        btnMedicamento.setOnAction(e -> { tamagotchi.darMedicamento(); updateStatus(); });
        btnInteragir.setOnAction(e -> { tamagotchi.interagir(); updateStatus(); });

        VBox layout = new VBox(20, tamagotchiImage, statusLabel, climaLabel,
                btnAlimentar,
                btnBrincar,
                btnDormir,
                btnBanho,
                btnMedicamento,
                btnInteragir);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #f0f8ff; -fx-padding: 20px;");

        Scene scene = new Scene(layout, 500, 450);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Tamagotchi");
        primaryStage.show();
    }

    private void updateStatus() {
        statusLabel.setText(getStatusText());
        if (EventosAleatorios.houveChuva()) {
            climaLabel.setText("Clima: Está chovendo! Cuidado com o resfriado!");
        } else {
            climaLabel.setText("Clima: Não está chovendo");
        }
    }

    private String getStatusText() {
        String status = String.format(
                "Nome: %s\nFome: %d | Felicidade: %d | Energia: %d\nSaúde: %d | Higiene: %d | Social: %d\nIdade: %d | Fase: %s\nHora: %s",
                tamagotchi.getNome(),
                tamagotchi.getFome(),
                tamagotchi.getFelicidade(),
                tamagotchi.getEnergia(),
                tamagotchi.getSaude(),
                tamagotchi.getHigiene(),
                tamagotchi.getSocializacao(),
                tamagotchi.getIdade(),
                tamagotchi.getFase(),
                tamagotchi.getRelogio().toString()
        );

        if (tamagotchi.estaResfriado()) {
            status += "\n⚠ " + tamagotchi.getNome() + " está resfriado!";
        }

        if (EventosAleatorios.houveChuva()) {
            status += "\n☔ Está chovendo no mundo do Tamagotchi!";
        }

        return status;
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #ffcc00; -fx-text-fill: black; -fx-font-size: 14px; -fx-border-radius: 10px; -fx-background-radius: 10px;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #ffaa00; -fx-text-fill: black; -fx-font-size: 14px; -fx-border-radius: 10px; -fx-background-radius: 10px;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #ffcc00; -fx-text-fill: black; -fx-font-size: 14px; -fx-border-radius: 10px; -fx-background-radius: 10px;"));
        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
