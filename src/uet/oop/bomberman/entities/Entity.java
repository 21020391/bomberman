package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.staticEntities.Brick;
import uet.oop.bomberman.graphics.Sprite;

import java.util.function.Consumer;

public abstract class Entity {
    protected static int x;

    protected static int y;

    protected Image img;

    public Entity(int xUnit, int yUnit, Image img) {
        x = xUnit * Sprite.SCALED_SIZE;
        y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public Entity() {

    }

    public static int getX() {
        return 0;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public  void setY(int y) {
        this.y = y;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public abstract void update();
}


