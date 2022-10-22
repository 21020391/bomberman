package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.dynamicEntities.Character;

public class Flame extends Entity {
    protected int _direction;
    private int _radius;
    protected int xOrigin, yOrigin;

    protected FlameSegment[] _flameSegments = new FlameSegment[0];

    public Flame(int x, int y, Image img, int direction, int radius) {

        xOrigin = x;
        yOrigin = y;
        _direction = direction;
        _radius = radius;
        createFlameSegments();
    }

    private void createFlameSegments() {

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