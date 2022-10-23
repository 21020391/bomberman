package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
//import uet.oop.bomberman.entities.dynamicEntities.Character;

public class Flame extends Entity {
    protected int _direction;
    private  Board _board;
    private int _radius;
    protected int xOrigin, yOrigin;

    protected FlameSegment[] _flameSegments = new FlameSegment[0];

    public Flame(int x, int y, Image img, int direction, int radius,Board board) {

        xOrigin = x;
        yOrigin = y;
        _direction = direction;
        _radius = radius;
        _board = board;
    }

    private int calculateDistance() {
        return _radius;
    }

    public FlameSegment flameSegmentAt(int x, int y) {
        for (int i = 0; i < _flameSegments.length; i++) {
            if (_flameSegments[i].getX() == x && _flameSegments[i].getY() == y)
                return _flameSegments[i];
        }
        return null;
    }

    @Override
    public void update() {
    }

}