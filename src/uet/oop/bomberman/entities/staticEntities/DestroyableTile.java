package uet.oop.bomberman.entities.staticEntities;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Bomb.Flame;
import uet.oop.bomberman.graphics.Sprite;

/**
 * Đối tượng cố định có thể bị phá hủy
 */
public class DestroyableTile extends Tile {
    protected boolean _destroyed = false;
    protected int _timeToDisapear = 20;
    protected Sprite _belowSprite = Sprite.grass;

    public DestroyableTile(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void update() {
        if(_destroyed) {
            animate();
            if(_timeToDisapear > 0)
                _timeToDisapear--;
            else
                remove();
        }
    }

    public void destroy() {
        _destroyed = true;
    }

    @Override
    public boolean collide(Entity e) {
        // TODO: xử lý khi va chạm với Flame
        if(e instanceof Flame) {
            destroy();
        }
        return false;
    }

    public void addBelowSprite(Sprite sprite) {
        _belowSprite = sprite;
    }

    protected Sprite movingSprite(Sprite normal, Sprite x1, Sprite x2) {
        int calc = _animate % 30;

        if(calc < 10) {
            return normal;
        }

        if(calc < 20) {
            return x1;
        }

        return x2;
    }

}
