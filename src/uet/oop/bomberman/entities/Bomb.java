package uet.oop.bomberman.entities;


import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Bomb extends Entity {

    public Bomb(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() {

    }
}
