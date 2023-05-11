package gameloop;

import components.Entity;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class TimerMethod extends AnimationTimer {
    private long lastTime = System.nanoTime();
    private int delay = 100;
    @Override
    public void handle(long now) {

        long time = System.nanoTime();
        int deltaTime = (int) ((time - lastTime) / 1000000);

        Rectangle ghostHit = collidtion();
        if(ghostHit != null) {
            App.player.setFill(Color.GREEN);
            // game over
            stop();
        }
        

        
        if (deltaTime > delay) {
            Entity[] objs = App.level.nextTo(App.level.scaleValue(App.player.getX()),
            App.level.scaleValue(App.player.getY()));
            Entity pos = App.level.position(App.level.scaleValue(App.player.getX()),
            App.level.scaleValue(App.player.getY()));

            System.out.println(App.level.position(0, 0).tag());

            if(pos.tag() == "Coin") {
                pos.pickUp();
            }
            if(pos.tag() == "Power") {
                pos.pickUp();
            }

            if(App.dia == 'W' && objs[0].tag() != "Wall") {
                App.player.setY(App.player.getY()-App.size);
            } else if(App.dia == 'S' && objs[2].tag() != "Wall") {
                App.player.setY(App.player.getY()+App.size);
            } else if(App.dia == 'A' && objs[3].tag() != "Wall") {
                App.player.setX(App.player.getX()-App.size);
            } else if(App.dia == 'D' && objs[1].tag() != "Wall") {
                App.player.setX(App.player.getX()+App.size);
            }
            delay = deltaTime + 100;
        }
    }

    private Rectangle collidtion() {
        for(int i = 0; i < App.ghosts.size(); i++) {
            Rectangle g = App.ghosts.get(i);
            if(App.player.getBoundsInParent().intersects(g.getBoundsInParent())) {
                 return g;
            }
        }
        return null;
    }
}
