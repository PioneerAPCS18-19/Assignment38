/**
 * Write a description of class BasicAnimation here.
 * 
 * @author King
 * @version May 2017
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.KeyCode;

public class Animations extends Application
{
    private int screenWidth, screenHeight;
    private int score;
    private Ball ball = new Ball(5, 2, 4);

    public Animations()
    {
        screenWidth = 400;
        screenHeight = 400;
        
        score = 0;
    }

    @Override 
    public void start(Stage stage) 
    {
    	// create canvas
        Canvas canvas = new Canvas(screenWidth, screenHeight);
        canvas.setFocusTraversable(true);
        
        // create GraphicsContext object
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        // loop frequency (lower values make run() loop more often).
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(20), e -> run(gc)));
        tl.setCycleCount(Timeline.INDEFINITE);

        // handle mouse events
        canvas.setOnMouseClicked(e -> 
            {
                if(tl.getStatus() == Animation.Status.PAUSED)
                {
                    tl.play();
                }
                else
                {
                    tl.pause();
                }
            });
         
        // handle key events
        canvas.setOnKeyPressed(e -> 
            {
                if(e.getCode() == KeyCode.UP)
                {
                    ball.addVel();
                }
                else if(e.getCode() == KeyCode.DOWN)
                {
                    ball.decVel();
                }
            });

        
        stage.setTitle("Animations"); // window title
        stage.setScene(new Scene(new StackPane(canvas)));
        stage.show();  // show window
        
        tl.play();  // start looping in run()
    }

    private void run(GraphicsContext gc)
    {
        // color for background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, screenWidth, screenHeight);

        // color for ball
        gc.setFill(Color.WHITE);
        gc.fillOval(ball.getX(), ball.getY(), 2 * ball.getR(), 2 * ball.getR());
        
        // color for unfilled oval
        gc.setStroke(Color.RED);
        gc.strokeOval(139, 133, 80, 25);

        ball.move();

        if(ball.getY() < 0 || ball.getY() + 2 * ball.getR() > screenHeight)
        {
            ball.hitTopBotWall();
        }

        if(ball.getX() < 0 || ball.getX() + 2 * ball.getR() > screenWidth)
        {
            ball.hitSideWall();
            score++;
        }
        
        gc.fillText("Score: " + score, 150, 150);
        
        Image img = null;
        try 
        {
			img = new Image(new FileInputStream("image.png"));
		} 
        catch (FileNotFoundException e) 
        {
			e.printStackTrace();
		}
        
        if(img != null)
        {
        	gc.drawImage(img, 250, 250);
        }
    }

    public static void main(String[] args)
    {
        Application.launch(args);
    }
}
