package uet.oop.bomberman.entities.dynamic;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class dynamics extends Entity {
    // so pixel 1 lan nhan phim
    protected int moving;

    // huong di cua bomberman
    protected String direction;

    // dem so buoc mot buoc
    protected int count;

    public dynamics(int x, int y, Image img) { super(x, y, img); }

    public dynamics(int moving, String direction, int count) {
        this.moving = moving;
        this.direction = direction;
        this.count = count;
    }

    public int getMoving() {
        return moving;
    }

    public void setMoving(int moving) {
        this.moving = moving;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public dynamics() {}

    @Override
    public void update() {}
}
