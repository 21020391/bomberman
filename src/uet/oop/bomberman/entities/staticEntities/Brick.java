package uet.oop.bomberman.entities.staticEntities;


import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Bomb.Flame;
import uet.oop.bomberman.entities.dynamicEntities.enemy.Kondoria;
import uet.oop.bomberman.act.Screen;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.Coordinates;

public class Brick extends DestroyableTile {

    public Brick(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render(Screen screen) {
        int x = Coordinates.tileToPixel(_x);
        int y = Coordinates.tileToPixel(_y);

        if(_destroyed) {
            _sprite = movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2);

            screen.renderEntityWithBelowSprite(x, y, this, _belowSprite);
        }
        else
            screen.renderEntity( x, y, this);
    }

    @Override
    public boolean collide(Entity e) {

        //TODO: xử lý va cham với Flame
        if(e instanceof Flame)
            destroy();

        //TODO: xét va chạm với Doria đi qua
        return e instanceof Kondoria;
    }
}
