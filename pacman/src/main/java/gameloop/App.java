package gameloop;

import java.util.ArrayList;

import components.Entity;
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
import map.levelLoarder;
 
public class App extends Application {
    public static char dia = ' ';
    public static Rectangle player = new Rectangle(150, 100, 50, 50);

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

        levelLoarder level = new levelLoarder();
        level.fillLevel();
        
        
        Group root = new Group();
        root.getChildren().add(btn);

        Entity[][] bord = level.getLevel();

        for(int i = 0; i < level.getLevel().length; i++) {
            for(int j = 0; j < level.getLevel()[j].length; j++) {
                root.getChildren().add(level.getLevel()[i][j].render());
            }
        }
        
        
        

        root.getChildren().get(0).setTranslateX(100);

        root.getChildren().add(player);
        

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