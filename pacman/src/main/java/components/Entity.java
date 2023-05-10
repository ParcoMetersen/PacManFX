package components;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Entity {
    public Rectangle sprite;
    public Entity(Rectangle sprite) {
        this.sprite = sprite;
    }

    public Rectangle render() {
        return this.sprite;
    }
}
