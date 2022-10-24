package uet.oop.bomberman.entities.Items;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.dynamicEntities.Bomber;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.Sound.Audio;

import java.io.File;

public class FlameItem extends Items {
    private Audio eatItem;

    public FlameItem(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        eatItem = new Audio(new File("res/Sound/EatItemp2.wav"));
    }

    @Override
    public boolean collide(Entity e) {
        // TODO: xử lý Bomber ăn Item
        if(e instanceof Bomber){
            if(this.isRemoved())
                return true;
            BombermanGame.addBombRadius(1);
            remove();
            eatItem.play();
            return true;
        }
        return false;
    }

}
