package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.dynamicEntities.Bomber;
import uet.oop.bomberman.entities.staticEntities.Grass;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.level.Game;


public class SpeedItem extends Items {

    private Board _board;

    public SpeedItem(int x, int y, Image img, Board board) {
        super(x, y, img);
        _board = board;
    }

    public SpeedItem() {

    }

    public boolean collide(Entity e) {
        if (e instanceof Bomber) {
            _board.addEntity((int) (this.x + this.y * _board.getLevel().getWidth()), new Grass((int) this.x, (int) this.y, Sprite.grass.getFxImage()));
            Game.addBomberSpeed(0.2);
        }
        return false;
    }


}