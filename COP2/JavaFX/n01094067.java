///////////////////////////
//Riley Kollman          //
//Java FX II             //
//Last modified: 4/15/18 //
///////////////////////////

import javafx.application.Application;
import javafx.geometry.*;
import javafx.util.Duration;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.media.*;
import javafx.event.*;
import javafx.animation.*;


//--------------------------------------------------------------------------------------------------------------------
//BUTTONS
class ButtonPane extends Application
{
   protected Text name = new Text("Riley Kollman");  
      
   protected BorderPane getPane()
   {  
      //Set up buttons       
      Button btUp = new Button("Up");
      Button btDn = new Button("Down");
      
      //Button events
      btUp.setOnAction(e -> name.setY(name.getY()-10));
      btDn.setOnAction(e -> name.setY(name.getY()+10));
         
      //Add buttons to buttonPane
      HBox buttonPane = new HBox();
      buttonPane.getChildren().addAll(btUp, btDn);
      
      //Create borderPane and add buttonPane to bottom
      BorderPane borderPane = new BorderPane();
      borderPane.setBottom(buttonPane);
      buttonPane.setAlignment(Pos.CENTER);
      
      //Set up namePane
      Pane namePane = new Pane(name);
      GridPane centerPane = new GridPane();
      centerPane.add(namePane, 1, 1, 1, 1);
      centerPane.setAlignment(Pos.CENTER);
      
      //Set up video player
      Media video = new Media("http://www.unf.edu/~n01094067/myCat.mp4");
      MediaPlayer mediaPlayer = new MediaPlayer(video);
      MediaView mediaView = new MediaView(mediaPlayer);
      
      Button playButton = new Button(">");
      playButton.setOnAction
      (e -> {
         if(playButton.getText().equals(">"))
         {
            mediaPlayer.play();
            playButton.setText("||");
         }
         else
         {
            mediaPlayer.pause();
            playButton.setText(">");
         }
      });
      
      Button rewindButton = new Button("<<");
      rewindButton.setOnAction(e -> mediaPlayer.seek(Duration.ZERO));
      
      Slider volumeSlider = new Slider();
      volumeSlider.setPrefWidth(150);
      volumeSlider.setMaxWidth(150);
      volumeSlider.setMinWidth(30);
      volumeSlider.setValue(50);
      mediaPlayer.volumeProperty().bind(volumeSlider.valueProperty().divide(100));
      
      HBox volumeBox = new HBox(10);
      volumeBox.getChildren().addAll(playButton, rewindButton, new Label("Volume"), volumeSlider);
      BorderPane mediaPane = new BorderPane();
      Text text = new Text("Chester Vs. The Duster");
      FlowPane chesterPane = new FlowPane(text);
      mediaPane.setTop(chesterPane);
      chesterPane.setAlignment(Pos.CENTER);
      FlowPane pane = new FlowPane(mediaView);
      mediaPane.setCenter(pane);
      pane.setAlignment(Pos.CENTER_RIGHT);
      mediaPane.setBottom(volumeBox);
      centerPane.add(mediaPane, 2, 1, 1, 1);      
      borderPane.setCenter(centerPane);
      volumeBox.setAlignment(Pos.CENTER);
      
      
      return borderPane;
   }
   
   @Override
   public void start(Stage stage)
   {
      Scene scene = new Scene(getPane(), 1000, 500);      
      stage.setTitle("Project 7");
      stage.setScene(scene);
      stage.show();
   }
}
       
//------------------------------------------------------------------------------------------------------
//NAME FONTS
class NameFont extends ButtonPane
{
   protected BorderPane getPane()
   {        
      BorderPane borderPane = super.getPane();
      
      //Fonts
      Font fontBoldItalic = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);
      Font fontBold = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20);
      Font fontItalic = Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20);
      Font fontNormal = Font.font("Times New Roman", 
      FontWeight.NORMAL, FontPosture.REGULAR, 20);   
      name.setFont(fontNormal); 

      //Set up check boxes in left portion of borderframe
      CheckBox boxBold = new CheckBox("Bold");
      CheckBox boxItalic = new CheckBox("Italic");
      VBox checkPane = new VBox(boxBold, boxItalic);
      borderPane.setLeft(checkPane);
      checkPane.setAlignment(Pos.CENTER_LEFT);      
      
      //Event handler
      EventHandler<ActionEvent> handler = e -> 
      { 
         if (boxBold.isSelected() && boxItalic.isSelected()) 
         {
            name.setFont(fontBoldItalic); // Both check boxes checked
         }
         else if (boxBold.isSelected()) 
         {
            name.setFont(fontBold); // The Bold check box checked
         }
         else if (boxItalic.isSelected()) 
         {
            name.setFont(fontItalic); // The Italic check box checked
         }      
         else 
         {
            name.setFont(fontNormal); // Both check boxes unchecked
         }
      };      
      boxBold.setOnAction(handler);
      boxItalic.setOnAction(handler);
      
      return borderPane;      
   }
}

//---------------------------------------------------------------------------------------------------------
//RADIO BUTTONS
class Radio extends NameFont
{
   protected BorderPane getPane()
   {
      BorderPane borderPane = super.getPane();
      
      //Set up radio buttons
      RadioButton radMaroon = new RadioButton("Maroon");
      RadioButton radGold = new RadioButton("Gold");
      RadioButton radTeal = new RadioButton("Teal");
      
      VBox radioPane = new VBox(radMaroon, radGold, radTeal);     
      borderPane.setRight(radioPane);
      radioPane.setAlignment(Pos.CENTER_LEFT);
      
      ToggleGroup radGroup = new ToggleGroup();
      radMaroon.setToggleGroup(radGroup);
      radGold.setToggleGroup(radGroup);
      radTeal.setToggleGroup(radGroup);
          
      radMaroon.setOnAction
      (e -> {
         if (radMaroon.isSelected()) 
         {
            name.setFill(Color.MAROON);
         }
      });
          
      radGold.setOnAction
      (e -> {
         if (radGold.isSelected()) 
         {
            name.setFill(Color.GOLD);
         }
      });
       
      radTeal.setOnAction
      (e -> {
         if (radTeal.isSelected()) 
         {
            name.setFill(Color.TEAL);
         }
      });
      
      return borderPane;
   }
}

class RectangleBounce extends Radio
{
   protected BorderPane getPane()
   {
      BorderPane borderPane = super.getPane();
      
      BorderPane topPane = new BorderPane();
      Rectangle rectangle = new Rectangle(0,0,75,40);
      rectangle.setFill(Color.RED);
      HBox rectanglePane = new HBox(rectangle);
      topPane.setCenter(rectanglePane);
      
      Slider rectangleSlider = new Slider();
      HBox sliderPane = new HBox(rectangleSlider);
      topPane.setBottom(sliderPane);
      sliderPane.setAlignment(Pos.CENTER);
      
      //Define travel distance and speed for rectangle
      KeyValue XDistance = new KeyValue(rectangle.translateXProperty(), 1000 - rectangle.getWidth());
      KeyValue YDistance = new KeyValue(rectangle.translateYProperty(), 20 - rectangle.getHeight());
      KeyFrame XSpeed = new KeyFrame(Duration.millis(5000), XDistance);
      KeyFrame YSpeed = new KeyFrame(Duration.millis(600), YDistance);
      
      Timeline XTimeline = new Timeline(XSpeed);
      XTimeline.setCycleCount(Timeline.INDEFINITE);
      XTimeline.setAutoReverse(true);
      Timeline YTimeline = new Timeline(YSpeed);
      YTimeline.setCycleCount(Timeline.INDEFINITE);
      YTimeline.setAutoReverse(true);

      XTimeline.setRate(0);
      YTimeline.setRate(0);

      XTimeline.play();
      YTimeline.play(); 
      
      rectangleSlider.valueProperty().addListener
      (e -> {
         XTimeline.setRate((rectangleSlider.getValue())/50);
         YTimeline.setRate((rectangleSlider.getValue())/50);
      });
      
      borderPane.setTop(topPane);
      
      return borderPane;
   }
}

public class n01094067 extends RectangleBounce //extend whatever is most recent
{
    public static void main(String[] args) throws Exception
    {
      Application.launch(args);
    }
}