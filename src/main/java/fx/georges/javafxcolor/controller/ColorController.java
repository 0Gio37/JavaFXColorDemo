package fx.georges.javafxcolor.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private Slider redSlider;
    @FXML
    private Slider greenSlider;
    @FXML
    private Slider blueSlider;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        final Color currentColor = new Color(100,200, 50);

        String currentPaneColor = currentColor.getHexValue();
        paneBackground.setStyle("-fx-background-color:"+ currentPaneColor);

        hexValueField.setText(currentPaneColor);

        redValueField.setText(String.valueOf(currentColor.getRed()));
        greenValueField.setText(String.valueOf(currentColor.getGreen()));
        blueValueField.setText(String.valueOf(currentColor.getBlue()));

        redSlider.setValue(currentColor.getRed());
        greenSlider.setValue(currentColor.getGreen());
        blueSlider.setValue(currentColor.getBlue());


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
                redSlider.setValue(Double.parseDouble(s));
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
                greenSlider.setValue(Double.parseDouble(s));
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
                blueSlider.setValue(Double.parseDouble(s));
            }
        });

    }





}