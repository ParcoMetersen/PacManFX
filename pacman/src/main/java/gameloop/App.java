package gameloop;

import java.util.ArrayList;

import components.Entity;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import map.levelLoarder;
 
public class App extends Application {

    public static int size = 20;
    public static levelLoarder level;

    public static char dia = ' ';
    public static Rectangle player = new Rectangle(size, size, size, size);
    public Rectangle ghost = new Rectangle(0, 150, 25, 25);

    public static ArrayList<Rectangle> ghosts = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        level = new levelLoarder(size);
        level.fillLevel();
        
        
        Group root = new Group();

        for(int i = 0; i < level.getLevel().length; i++) {
            for(int j = 0; j < level.getLevel()[j].length; j++) {
                root.getChildren().add(level.getLevel()[i][j].render());
            }
        }
        root.getChildren().add(player);
        // ghosts.add(ghost);
        // root.getChildren().add(ghost);
        

        /*
        Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        Scene scene = new Scene(root);
        */

        AnimationTimer loop = new TimerMethod();
        
        Scene scene = new Scene(root, size*level.getLevel()[0].length, size*level.getLevel().length);

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            Entity[] objs = level.nextTo(level.scaleValue(player.getX()),
            level.scaleValue(player.getY()));

            if(key.getCode() == KeyCode.W && objs[0].tag() != "Wall") {
                dia = 'W';
            } else if(key.getCode() == KeyCode.S && objs[2].tag() != "Wall") {
                dia = 'S';
            } else if(key.getCode() == KeyCode.A && objs[3].tag() != "Wall") {
                dia = 'A';
            } else if(key.getCode() == KeyCode.D && objs[1].tag() != "Wall") {
                dia = 'D';
            }
        });

        
        loop.start();
        primaryStage.setTitle("Pac-Man");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}