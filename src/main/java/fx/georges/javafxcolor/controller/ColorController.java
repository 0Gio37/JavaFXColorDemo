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

    final Color currentColor = new Color(100,200, 50);

    //variable on change color top pan
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


    //variable for drawing bottom pan
    @FXML
    private Pane drawingZone;




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

    /**
     * Methode called when mouse is on application screen
     */
    public void setColorsParameters(){
        //actions on hexa values
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

        //actions on red values
        redValueField.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            changeFromFieldToSlider(redValueField, redSlider);
        });
        redSlider.valueProperty().addListener((observableValue, number, t1) -> {
            changeFromSliderToField(redSlider,redValueField );
        });

        //actions on green values
        greenValueField.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            changeFromFieldToSlider(greenValueField, greenSlider);
        });
        greenSlider.valueProperty().addListener((observableValue, number, t1) -> {
            changeFromSliderToField(greenSlider,greenValueField );
        });

        //actions on blue values
        blueValueField.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            changeFromFieldToSlider(blueValueField, blueSlider);
        });
        blueSlider.valueProperty().addListener((observableValue, number, t1) -> {
            changeFromSliderToField(blueSlider,blueValueField );
        });
    }

    /**
     * Change value of fields colors, update hexa field and pane background
     * @param slider type Slider object
     * @param fieldValue type TextField object
     */
    private void changeFromSliderToField(Slider slider, TextField fieldValue){
        int newColor = (int) slider.getValue();
        currentColor.setBlue(newColor);
        fieldValue.setText(String.valueOf(newColor));
        paneBackground.setStyle("-fx-background-color:"+ currentColor.getHexValue());
        hexValueField.setText(currentColor.getHexValue());
    }
    /**
     * Change value of sliders colors, update hexa field and pane background
     * @param fieldValue type TextField object
     * @param slider type Slider object
     */
    private void changeFromFieldToSlider(TextField fieldValue, Slider slider){
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


    /**
     * Methode called when mouse is on application screen
     */
    public void drawFonction(){
        String currentDrawingColor = currentColor.getHexValue();


    }







}