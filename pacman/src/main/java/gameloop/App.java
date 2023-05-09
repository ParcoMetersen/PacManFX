package gameloop;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
 
public class App extends Application {
    public static char dia = ' ';
    public static Rectangle player = new Rectangle(150, 100, 50, 50);
    public Rectangle ghost = new Rectangle(0, 150, 25, 25);

    public static ArrayList<Rectangle> ghosts = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("you clicked me!");
            }
        });

        
        
        Group root = new Group();
        root.getChildren().add(btn);
        
        root.getChildren().add(player);
        ghosts.add(ghost);
        root.getChildren().add(ghost);
        

        /*
        Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        Scene scene = new Scene(root);
        */

        AnimationTimer loop = new TimerMethod();
        loop.start();
        
        Scene scene = new Scene(root, 640, 640);

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode() == KeyCode.W) {
                dia = 'W';
            } else if(key.getCode() == KeyCode.S) {
                dia = 'S';
            } else if(key.getCode() == KeyCode.A) {
                dia = 'A';
            } else if(key.getCode() == KeyCode.D) {
                dia = 'D';
            }
            
            loop.start();
        });

        
        
        primaryStage.setTitle("Pac-Man");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}