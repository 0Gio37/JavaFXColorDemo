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

        String currentPaneColor = currentColor.getHexValue();
        paneBackground.setStyle("-fx-background-color:"+ currentPaneColor);

        hexValueField.setText(currentPaneColor);

        redValueField.setText(String.valueOf(currentColor.getRed()));
        greenValueField.setText(String.valueOf(currentColor.getGreen()));
        blueValueField.setText(String.valueOf(currentColor.getBlue()));

        redSlider.setValue(currentColor.getRed());
        greenSlider.setValue(currentColor.getGreen());
        blueSlider.setValue(currentColor.getBlue());
    }

    public void setColorsParameters(){

        hexValueField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
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
            }
        });

        redSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                int newRedColor = (int) redSlider.getValue();
                currentColor.setRed(newRedColor);
                redValueField.setText(String.valueOf(newRedColor));

                String currentPaneColor = currentColor.getHexValue();
                paneBackground.setStyle("-fx-background-color:"+ currentPaneColor);
                hexValueField.setText(currentPaneColor);
            }
        });
        redValueField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                String s = redValueField.getText();
                if(!s.matches("[a-zA-Z]+")){
                    try{
                        currentColor.setRed(Integer.parseInt(redValueField.getText()));
                        redSlider.setValue(Double.parseDouble(s));
                        msgWrong.setText("");
                    }catch (IllegalArgumentException e){
                        msgWrong.setText(e.getMessage());
                    }
                }else {
                    msgWrong.setText("Merci de saisir un nombre");
                }
            }
        });

        greenSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                int newGreenColor = (int) greenSlider.getValue();
                currentColor.setGreen(newGreenColor);
                greenValueField.setText(String.valueOf(newGreenColor));

                String currentPaneColor = currentColor.getHexValue();
                paneBackground.setStyle("-fx-background-color:"+ currentPaneColor);
                hexValueField.setText(currentPaneColor);
            }
        });
        greenValueField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                String s = greenValueField.getText();
                if(!s.matches("[a-zA-Z]+")){
                    try{
                        currentColor.setGreen(Integer.parseInt(greenValueField.getText()));
                        greenSlider.setValue(Double.parseDouble(s));
                        msgWrong.setText("");
                    }catch (IllegalArgumentException e){
                        msgWrong.setText(e.getMessage());
                    }
                }else {
                    msgWrong.setText("Merci de saisir un nombre");
                }
            }
        });

        blueSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                int newBlueColor = (int) blueSlider.getValue();
                currentColor.setBlue(newBlueColor);
                blueValueField.setText(String.valueOf(newBlueColor));

                String currentPaneColor = currentColor.getHexValue();
                paneBackground.setStyle("-fx-background-color:"+ currentPaneColor);
                hexValueField.setText(currentPaneColor);
            }
        });
        blueValueField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                String s = blueValueField.getText();
                if(!s.matches("[a-zA-Z]+")){
                    try{
                        currentColor.setBlue(Integer.parseInt(blueValueField.getText()));
                        blueSlider.setValue(Double.parseDouble(s));
                        msgWrong.setText("");
                    }catch (IllegalArgumentException e){
                        msgWrong.setText(e.getMessage());
                    }
                }else {
                    msgWrong.setText("Merci de saisir un nombre");
                }
            }
        });




    }





}