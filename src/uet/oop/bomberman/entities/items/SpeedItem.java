package uet.oop.bomberman.entities.items;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.dynamicEntities.Bomber;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Audio;

import java.io.File;

public class SpeedItem extends Items {
    private Audio eatItem;

    public SpeedItem(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        eatItem = new Audio(new File("res/Sound/EatItemp2.wav"));
    }

    @Override
    public boolean collide(Entity e) {
        // TODO: xử lý Bomber ăn Item
        if(e instanceof Bomber){
            if(this.isRemoved())
                return true;
            BombermanGame.addBomberSpeed(0.5);
            remove();
            eatItem.play();
            return true;
        }
        return false;
    }


}
