package fx.georges.javafxcolor.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import fx.georges.javafxcolor.model.Color;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class ColorController implements Initializable {
    @FXML
    private TextField hexValueField;
    @FXML
    private Pane paneBackground;
    @FXML
    private TextField redValueField;
    @FXML
    private TextField greenValueField;
    @FXML
    private TextField blueValueField;
    @FXML
    private Label msgWrong;
    @FXML
    private Slider redSlider;
    @FXML
    private Slider greenSlider;
    @FXML
    private Slider blueSlider;

    final Color currentColor = new Color(100,200, 50);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        paneBackground.setStyle("-fx-background-color:"+ currentColor.getHexValue());

        hexValueField.setText(currentColor.getHexValue());
        redValueField.setText(String.valueOf(currentColor.getRed()));
        greenValueField.setText(String.valueOf(currentColor.getGreen()));
        blueValueField.setText(String.valueOf(currentColor.getBlue()));

        redSlider.setValue(currentColor.getRed());
        greenSlider.setValue(currentColor.getGreen());
        blueSlider.setValue(currentColor.getBlue());
    }

    public void setColorsParameters(){

        hexValueField.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            String newHexavalue = hexValueField.getText();

            try{
                msgWrong.setText("");
                currentColor.setHexValue(newHexavalue);
                paneBackground.setStyle("-fx-background-color:"+ newHexavalue);
                currentColor.convertHexaInRGB(newHexavalue);
                redSlider.setValue(currentColor.getRed());
                greenSlider.setValue(currentColor.getGreen());
                blueSlider.setValue(currentColor.getBlue());
            }catch (IllegalArgumentException e){
                msgWrong.setText(e.getMessage());
            }
        });

        redSlider.valueProperty().addListener((observableValue, number, t1) -> {
            int newRedColor = (int) redSlider.getValue();
            currentColor.setRed(newRedColor);
            redValueField.setText(String.valueOf(newRedColor));

            paneBackground.setStyle("-fx-background-color:"+ currentColor.getHexValue());
            hexValueField.setText(currentColor.getHexValue());
        });
        redValueField.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            validFormatColorFieldIsNumber(redValueField, redSlider);
        });

        greenSlider.valueProperty().addListener((observableValue, number, t1) -> {
            int newGreenColor = (int) greenSlider.getValue();
            currentColor.setGreen(newGreenColor);
            greenValueField.setText(String.valueOf(newGreenColor));

            paneBackground.setStyle("-fx-background-color:"+ currentColor.getHexValue());
            hexValueField.setText(currentColor.getHexValue());
        });
        greenValueField.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            validFormatColorFieldIsNumber(greenValueField, greenSlider);
        });

        blueSlider.valueProperty().addListener((observableValue, number, t1) -> {
            int newBlueColor = (int) blueSlider.getValue();
            currentColor.setBlue(newBlueColor);
            blueValueField.setText(String.valueOf(newBlueColor));

            paneBackground.setStyle("-fx-background-color:"+ currentColor.getHexValue());
            hexValueField.setText(currentColor.getHexValue());
        });
        blueValueField.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            validFormatColorFieldIsNumber(blueValueField, blueSlider);
        });
    }

    private void validFormatColorFieldIsNumber(TextField fieldValue, Slider slider){
        String s = fieldValue.getText();
        if(!s.matches(("[a-zA-Z]+")) && !s.matches("^(?=.*[A-Z])(?=.*[0-9])[A-Z0-9]+$") ){
            try{
                msgWrong.setText("");
                currentColor.setBlue(Integer.parseInt(s));
                slider.setValue(Double.parseDouble(s));
            }catch (IllegalArgumentException e){
                msgWrong.setText(e.getMessage());
            }
        }else {
            msgWrong.setText("Merci de saisir un nombre");
        }
    }

}