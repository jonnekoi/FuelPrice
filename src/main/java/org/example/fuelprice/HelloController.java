package org.example.fuelprice;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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
    private Button enButton;

    @FXML
    private Button fiButton;

    @FXML
    private Button jpButton;

    @FXML
    private Button faButton;

    private ResourceBundle bundle;

    private Locale locale;

    // private static final double fuelPrice = 1.7;


    @FXML
    protected void calculatePrice() {
        double fuelPrice = Double.parseDouble(priceField.getText().replace(",", "."));
        double distance = Double.parseDouble(distanceField.getText().replace(",", "."));
        double consumption = Double.parseDouble(kulutusField.getText().replace(",", "."));
        double price = distance * (consumption / 100) * fuelPrice;
        polttoaineenHintaText.setText("Fuel price: " + fuelPrice + "€/l");
        resultText.setText("The price is: " + price + "€");
    }

    @FXML
    public void loadLanguage(String lang) {
        Locale locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("language", locale);

    }

    @FXML
    public void fiButtonClick(ActionEvent actionEvent) {
        loadLanguage("fi");
    }
    @FXML
    public void faButtonClick(ActionEvent actionEvent) {
        loadLanguage("fa");
    }
    @FXML
    public void jpButtonClick(ActionEvent actionEvent) {
        loadLanguage("jp");
    }
    @FXML
    public void enButtonClick(ActionEvent actionEvent) {
        loadLanguage("en");
    }
}