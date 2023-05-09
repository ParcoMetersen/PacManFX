package gameloop;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class TimerMethod extends AnimationTimer {
    @Override
    public void handle(long now) {
        Rectangle ghostHit = collidtion();
        if(ghostHit != null) {
            App.player.setFill(Color.GREEN);
            // game over
            stop();
        }
        edgeCollidtion();



        if(App.dia == 'W') {
            App.player.setY(App.player.getY()-1);
        } else if(App.dia == 'S') {
            App.player.setY(App.player.getY()+1);
        } else if(App.dia == 'A') {
            App.player.setX(App.player.getX()-1);
        } else if(App.dia == 'D') {
            App.player.setX(App.player.getX()+1);
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

    private void edgeCollidtion() {
        if(App.player.getY() + App.player.getHeight() > 250) {
            App.player.setY(250-App.player.getHeight());
            stop();
        } else if(App.player.getY() < 0) {
            App.player.setY(0);
            stop();
        }
    }
}
