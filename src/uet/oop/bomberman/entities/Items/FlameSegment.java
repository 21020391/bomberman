package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.dynamicEntities.Character;
public class FlameSegment extends Entity {
    private boolean _last;

    /**
     * last segment.
     */

    public FlameSegment(int x, int y, Image img, int direction, boolean last) {
        super(x, y, img);
        this._last = last;

        switch (direction) {
            case 0:
                if (!last) {
                    _sprite = Sprite.explosion_horizontal2;
                } else {
                    _sprite = Sprite.explosion_vertical_top_last2;
                }
                break;
            case 1:
                if (!last) {
                    _sprite = Sprite.explosion_horizontal2;
                } else {
                    _sprite = Sprite.explosion_horizontal_right_last2;
                }
                break;
            case 2:
                if (!last) {
                    _sprite = Sprite.explosion_vertical2;
                } else {
                    _sprite = Sprite.explosion_vertical_down_last2;
                }
                break;
            case 3:
                if (!last) {
                    _sprite = Sprite.explosion_horizontal2;
                } else {
                    _sprite = Sprite.explosion_horizontal_left_last2;
                }
                break;
        }
    }

    @Override
    public void update() {

    }

    public boolean collide(Entity e) {
        if(e instanceof Character) {
            ((Character) e).kill();
            return true;
        }
        return false;
    }
}

