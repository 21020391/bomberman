package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.dynamicEntities.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.fixedEntities;
import static uet.oop.bomberman.BombermanGame.idObjects;

public class FlameItem extends Items {

    public FlameItem() {

    }
    public FlameItem(int x, int y, Image img,int direction, int radius) {
        super(x, y, img);

        createFlame();
    }

    private void createFlame() {

    }

    public FlameItem(boolean receive) {
        super(receive);
    }

    @Override
    public void update() {
        for (Entity entity : fixedEntities)
            if (entity instanceof Bomber && !this.receive)
                if (idObjects[getX() * 16][entity.getY() * 16] == 2)
                    entity.setImg(Sprite.powerup_flames.getFxImage());

        if (!this.receive)
            if (getX() == x && getY() == y) {
                this.setImg(Sprite.grass.getFxImage());
                this.receive = true;
            }
    }
}

