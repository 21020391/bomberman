package uet.oop.bomberman.entities.staticEntities;


import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.entities.Entity;

public class Portal extends Entity {

    public Portal(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() {

    }
}
