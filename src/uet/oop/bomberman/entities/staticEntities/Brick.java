package uet.oop.bomberman.entities.staticEntities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.dead_position;
import static uet.oop.bomberman.BombermanGame.fixedEntities;

public class Brick extends Entity {

    public Brick(int x, int y, Image img) {
        super( x, y, img);
    }

    private void checkHidden() {
        for (Entity entity : fixedEntities) {
            if (entity instanceof Brick)
                if (dead_position[entity.getX() / 32][entity.getY() / 32] == 4) {
                    entity.setImg(Sprite.grass.getFxImage());
                }
        }
    }

    @Override
    public void update() {
        checkHidden();
    }
}
