package components;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Entity {
    private Rectangle sprite;
    private String tag;
    public Entity(Rectangle sprite, String tag) {
        this.sprite = sprite;
        this.tag = tag;
    }

    public void pickUp() {
        this.sprite.setFill(Color.WHITE);
        this.tag = "";
    }
    public Rectangle render() {
        return this.sprite;
    }
    public String tag() {
        return this.tag;
    }
}
