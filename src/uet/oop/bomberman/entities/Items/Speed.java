package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.bomberman;
import static uet.oop.bomberman.BombermanGame.fixedEntities;

public class Speed extends Items{
    public static int speed = 1;

    public Speed(int x, int y, Image img) {
        super(x, y, img);
    }

    public Speed() {
    }

    public Speed(boolean receive) {
        super(receive);
    }

    @Override
    public void update() {
        for (Entity entity : fixedEntities)
            if (entity instanceof Speed && !this.receive)
                if (listKill[entity.getX() / 32][entity.getY() / 32] == 4)
                    entity.setImg(Sprite.powerup_speed.getFxImage());

        if (!this.receive)
            if (bomberman.getX() == this.x && bomberman.getY() == this.y) {
                this.setImg(Sprite.grass.getFxImage());
                this.receive = true;
                speed = 2;
            }
    }
}
