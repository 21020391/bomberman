package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;
import javafx.stage.Screen;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.dynamicEntities.Bomber;
import uet.oop.bomberman.entities.staticEntities.Grass;
import uet.oop.bomberman.graphics.Sprite;

public class FlameItem extends Items {

    private Board _board;

    public FlameItem(int x, int y, Image img, Board _board) {
        super(x, y, img);
        this._board = _board;
    }

    private void createFlame() {

    }

    @Override
    public void update() {

    }

    public boolean collide(Entity e) {

        if (e instanceof Bomber) {
            _board.addEntity((int) (this.x +this.y * _board.getLevel().getWidth()),
                    new Grass((int) this.x, (int) this.y, Sprite.grass.getFxImage()));

        }
        return false;
    }


}

