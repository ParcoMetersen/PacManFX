package components;

public class Collider implements Component {
    private Entity parent;

    public Entity parent() {
        return this.parent;
    }

    public Collider(Entity parent) {
        this.parent = parent;
    }
}
