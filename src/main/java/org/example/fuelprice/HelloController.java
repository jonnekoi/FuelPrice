package org.example.fuelprice;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class HelloController {
    @FXML
    private TextField distanceField;

    @FXML
    private TextField kulutusField;

    @FXML
    private Text resultText;

    @FXML
    private TextField priceField;

    @FXML
    private Text polttoaineenHintaText;

    @FXML
    private Button fiButton;

    private static String language;


    private static final String url = "jdbc:mysql://localhost:3306/fuelprice";
    private static final String user = "root";
    private static final String password = "";

    @FXML
    public void calculatePrice() {
        try {
            double fuelPrice = Double.parseDouble(priceField.getText().replace(",", "."));
            double distance = Double.parseDouble(distanceField.getText().replace(",", "."));
            double consumption = Double.parseDouble(kulutusField.getText().replace(",", "."));
            double price = distance * (consumption / 100) * fuelPrice;
            polttoaineenHintaText.setText(fuelPrice + "€/l");
            resultText.setText(price + "€");
            saveData();
        } catch (NumberFormatException e) {
            System.out.println("Error: " + e);
            resultText.setText("Invalid input. Please enter valid numbers.");
        }
    }

    public void setIR(ActionEvent actionEvent) {
        Locale l = new Locale("ir");
        language = "ir";
        loadView(l);
    }

    public void setJP(ActionEvent actionEvent) {
        Locale l = new Locale("jp");
        language = "jp";
        loadView(l);
    }

    public void setFI(ActionEvent actionEvent) {
        Locale l = new Locale("fi");
        language = "fi";
        loadView(l);
    }


    public void setEN(ActionEvent actionEvent) {
        Locale l = new Locale("en");
        language = "en";
        loadView(l);
    }

    public void loadView(Locale locale) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundle", locale));
        try {
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) fiButton.getScene().getWindow();
            Scene currentScene = stage.getScene();
            Scene newScene = new Scene(root, currentScene.getWidth(), currentScene.getHeight());
            stage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveData() {
        double fuel_price = Double.parseDouble(priceField.getText().replace(",", "."));
        double distance = Double.parseDouble(distanceField.getText().replace(",", "."));
        double consumption = Double.parseDouble(kulutusField.getText().replace(",", "."));
        String language_used = language;

        String tablename = "data";

        String sql = "INSERT INTO " + tablename + " (fuel_price, distance, consumption, language_used) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setBigDecimal(1, BigDecimal.valueOf(fuel_price));
            pstmt.setBigDecimal(2, BigDecimal.valueOf(distance));
            pstmt.setBigDecimal(3, BigDecimal.valueOf(consumption));
            pstmt.setString(4, language_used);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}