///////////////////////////
//Riley Kollman          //
//Java FX                //
//Last modified: 4/02/18 //
///////////////////////////

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import java.lang.Math;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class n01094067 extends Application
{
   @Override
   public void start(Stage stage)
   {
      //create flow pane to hold all other panes
      FlowPane pane = new FlowPane();
      
      //clock
      int hour = (int)(Math.random()*12);
      int minute = (int)(Math.random()*60);
      int second = (int)(Math.random()*60);
      ClockPane clock = new ClockPane(hour, minute, second);
      Text name = new Text(0.362*clock.getW(),0.52*clock.getH(), "Riley Kollman");
      clock.getChildren().add(name);
      pane.getChildren().add(clock);
      
      //hangman
      Pane hangmanPane = new Pane();
      
      QuadCurve base = new QuadCurve();
      base.setStartX(10);
      base.setStartY(300);
      base.setEndX(60);
      base.setEndY(300);
      base.setControlX(35);
      base.setControlY(275);
      base.setStroke(Color.BLACK);
      base.setFill(Color.WHITE);
      hangmanPane.getChildren().add(base);
      
      Line pole = new Line(35, 10, 35, 287);
      hangmanPane.getChildren().add(pole);
      
      Line beam = new Line(35, 10, 100, 10);
      hangmanPane.getChildren().add(beam);
      
      Line headLock = new Line(100, 10, 100, 40);
      hangmanPane.getChildren().add(headLock);
      
      Circle head = new Circle(30, Color.WHITE);
      head.setStroke(Color.BLACK);
      head.setCenterX(100.0);
      head.setCenterY(70.0);
      hangmanPane.getChildren().add(head);
      
      Line torso = new Line(100, 100, 100, 200);
      hangmanPane.getChildren().add(torso);
      
      Line leftArm = new Line(100, 100, 50, 150);
      hangmanPane.getChildren().add(leftArm);
      
      Line rightArm = new Line(100, 100, 150, 150);
      hangmanPane.getChildren().add(rightArm);
      
      Line leftLeg = new Line(100, 200, 75, 275);
      hangmanPane.getChildren().add(leftLeg);
      
      Line rightLeg = new Line(100, 200, 125, 275);
      hangmanPane.getChildren().add(rightLeg);
      
      pane.getChildren().add(hangmanPane);
      
      //fans
      GridPane fanPane = new GridPane();
      for(int r=1; r<=2; r++)
      {
         for(int c=1; c<=2; c++)
         {
            Circle fan = new Circle(45, Color.WHITE);
            fan.setStroke(Color.BLACK);
            fanPane.add(fan, r, c);
            Pane blades = new Pane();
            for(int a=30; a<=390; a+=90)
            {
               Arc tri = new Arc();
               tri.setCenterX(46);
               tri.setCenterY(46);
               tri.setRadiusX(40);
               tri.setRadiusY(40);
               tri.setStartAngle(a);
               tri.setLength(35);
               tri.setType(ArcType.ROUND);
               blades.getChildren().add(tri);
            }
            fanPane.add(blades, r, c);
         }
      }
      pane.getChildren().add(fanPane);
      
      //final setup
      Scene scene = new Scene(pane, 600, 500);      
      stage.setTitle("Project 6");
      stage.setScene(scene);
      stage.show();
   }
}

class ClockPane extends Pane 
{
	private int hour;
	private int minute;
	private int second;

	// Clock pane's width and height
	private double w = 250, h = 250;

	/** Construct a default clock with the current time */
	public ClockPane() {
		setCurrentTime();
	}

	/** Construct a click with specified hour, minute, and second */
	public ClockPane(int hour, int minute, int second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		paintClock();
	}

	/** Return hour */
	public int getHour() {
		return hour;
	}

	/** Set a new hour */
	public void setHour(int hour) {
		this.hour = hour;
		paintClock();
	}

	/** Return minute */
	public int getMinute() {
		return minute;
	}

	/** Set a new minute */
	public void setMinute(int minute) {
		this.minute = minute;
		paintClock();
	}

	/** Return second */
	public int getSecond() {
		return second;
	}

	/** Set a new second */
	public void setSecond(int second) {
		this.second = second;
		paintClock();
	}

	/** Return clock pane's width */
	public double getW() {
		return w;
	}

	/** Set clock pane's width */
	public void getW(double w) {
		this.w = w;
		paintClock();
	}

	/** Return clock pane's height */
	public double getH() {
		return h;
	}

	/** Set clock pane's heigt */
	public void setH(double h) {
		this.h = h;
		paintClock();
	}

	/* Set the current time for the clock */
	public void setCurrentTime() {
		// Construct a Calendar for the current date and time
		Calendar calendar = new GregorianCalendar();

		// Set current hour, minute and second
		this.hour = calendar.get(Calendar.HOUR_OF_DAY);
		this.minute = calendar.get(Calendar.MINUTE);
		this.second = calendar.get(Calendar.SECOND);

		paintClock(); // Repaint the clock
	}
	
	/** Paint the clock */
	protected void paintClock() {
		// Initialize clock parameters
		double clockRadius = Math.min(w, h) * 0.8 * 0.5;
		double centerX = w / 2;
		double centerY = h / 2;

		// Draw circle
		Circle circle = new Circle(centerX, centerY, clockRadius);
		circle.setFill(Color.WHITE);
		circle.setStroke(Color.BLACK);
		Text t1 = new Text(centerX - 5, centerY - clockRadius + 12, "12");
		Text t2 = new Text(centerX - clockRadius + 3, centerY + 5, "9");
		Text t3 = new Text(centerX + clockRadius - 10, centerY + 3, "3");
		Text t4 = new Text(centerX - 3, centerY + clockRadius - 3, "6");

		// Draw second hand
		double sLength = clockRadius * 0.8;
		double secondX = centerX + sLength *
			Math.sin(second * (2 * Math.PI / 60));
		double secondY = centerY - sLength *
			Math.cos(second * (2 * Math.PI / 60));
		Line sLine = new Line(centerX, centerY, secondX, secondY);
		sLine.setStroke(Color.RED);

		// Draw minute hand
		double mLength = clockRadius * 0.65;
		double xMinute = centerX + mLength *
			Math.sin(minute * (2 * Math.PI / 60));
		double minuteY = centerY - mLength *
			Math.cos(minute * (2 * Math.PI / 60));
		Line mLine = new Line(centerX, centerY, xMinute, minuteY);
		mLine.setStroke(Color.BLUE);

		// Draw hour hand 
		double hLength = clockRadius * 0.5;
		double hourX = centerX + hLength *
			Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
		double hourY = centerY - hLength * 
			Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
		Line hLine = new Line(centerX, centerY, hourX, hourY);
		hLine.setStroke(Color.GREEN);

		getChildren().clear();
		getChildren().addAll(circle, t1, t2, t3, t4, sLine, mLine, hLine);
	}
}