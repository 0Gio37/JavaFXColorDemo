package fx.georges.javafxcolor.controller;

import fx.georges.javafxcolor.model.Color;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.embed.swing.SwingFXUtils;
import javafx.application.Platform;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;


public class ColorController implements Initializable{

    public Platform platform;
    final Color currentColor = new Color(20,20, 20);

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
    private Canvas canvas;
    @FXML
    private TextField brushSize;
    @FXML
    private CheckBox eraser;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        paneBackground.setStyle("-fx-background-color:" + currentColor.getHexValue());

        hexValueField.setText(currentColor.getHexValue());
        redValueField.setText(String.valueOf(currentColor.getRed()));
        greenValueField.setText(String.valueOf(currentColor.getGreen()));
        blueValueField.setText(String.valueOf(currentColor.getBlue()));

        redSlider.setValue(currentColor.getRed());
        greenSlider.setValue(currentColor.getGreen());
        blueSlider.setValue(currentColor.getBlue());

        //drawing fonction
        String s = currentColor.getHexValue();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        canvas.setOnMouseDragged(e -> {
            double size = Double.parseDouble(brushSize.getText());
            double x = e.getX() - size / 2;
            double y = e.getY() - size / 2;
        if(eraser.isSelected()){
            gc.clearRect(x,y,size,size);
        }else{
            //arguments object Color = type double and range between 0 and 1 : (0=0, 1=255)
            javafx.scene.paint.Color drawingColor = new javafx.scene.paint.Color(((double)currentColor.getRed())/255, ((double) currentColor.getGreen())/255, ((double) currentColor.getBlue())/255, 1);
            gc.setFill(drawingColor);
            //System.out.println("mycolor :"+ drawingColor);
            gc.fillOval(x, y, size, size);
        }
        });
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
            changeFromFieldToSlider(1, redValueField, redSlider);
        });
        redSlider.valueProperty().addListener((observableValue, number, t1) -> {
            changeFromSliderToField(1, redSlider,redValueField );
        });

        //actions on green values
        greenValueField.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            changeFromFieldToSlider(2, greenValueField, greenSlider);
        });
        greenSlider.valueProperty().addListener((observableValue, number, t1) -> {
            changeFromSliderToField(2, greenSlider,greenValueField );
        });

        //actions on blue values
        blueValueField.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            changeFromFieldToSlider(3, blueValueField, blueSlider);
        });
        blueSlider.valueProperty().addListener((observableValue, number, t1) -> {
            changeFromSliderToField(3, blueSlider,blueValueField );
        });
    }

    /**
     * Change value of fields colors, update hexa field and pane background
     * @param slider type Slider object
     * @param fieldValue type TextField object
     */
    private void changeFromSliderToField(int numberColor, Slider slider, TextField fieldValue){
        int newColor = (int) slider.getValue();
        switch (numberColor) {
            case 1 -> currentColor.setRed(newColor);
            case 2 -> currentColor.setGreen(newColor);
            case 3 -> currentColor.setBlue(newColor);
        }
        fieldValue.setText(String.valueOf(newColor));
        paneBackground.setStyle("-fx-background-color:"+ currentColor.getHexValue());
        hexValueField.setText(currentColor.getHexValue());
    }
    /**
     * Change value of sliders colors, update hexa field and pane background
     * @param fieldValue type TextField object
     * @param slider type Slider object
     */
    private void changeFromFieldToSlider(int numberColor, TextField fieldValue, Slider slider){
        String s = fieldValue.getText();
        if(!s.matches(("[a-zA-Z]+")) && !s.matches("^(?=.*[A-Z])(?=.*[0-9])[A-Z0-9]+$")){
            try{
                msgWrong.setText("");
                switch (numberColor) {
                    case 1 -> currentColor.setRed(Integer.parseInt(s));
                    case 2 -> currentColor.setGreen(Integer.parseInt(s));
                    case 3 -> currentColor.setBlue(Integer.parseInt(s));
                }
                slider.setValue(Double.parseDouble(s));
            }catch (IllegalArgumentException e){
                msgWrong.setText(e.getMessage());
            }
        }else {
            msgWrong.setText("Merci de saisir un nombre");
        }
    }

    public void onClickSave(){
        try {
            Image snapshot = canvas.snapshot(null, null);
            ImageIO.write(SwingFXUtils.fromFXImage(snapshot,null), "png", new File("paint.png"));
        }catch (Exception e){
            System.out.println("failed to save"+e.toString());
        }
    }

    public void onClickExit(){
        platform.exit();
    }


}