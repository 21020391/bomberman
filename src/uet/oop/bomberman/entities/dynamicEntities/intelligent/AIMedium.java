package uet.oop.bomberman.entities.dynamicEntities.intelligent;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.dynamicEntities.*;
import uet.oop.bomberman.entities.dynamicEntities.enemy.Enemy;

public class AIMedium extends AI {
    Bomber _bomber;
    Enemy _e;
    Board _board;

    public AIMedium(Bomber bomber, Enemy e, Board board) {
        _bomber = bomber;
        _e = e;
        _board = board;
    }
    public boolean collide(Entity e){
        return e instanceof Bomb;
    }

    @Override
    public int calculateDirection() {
        // TODO: cài đặt thuật toán tìm đường đi
        if(collide(_board.getBomb())){
            return random.nextInt(4);
        }

        if(_bomber == null) {
            return random.nextInt(4);
        }

        int vertical = random.nextInt(2);

        if(vertical == 1) {
            int v = calculateRowDirection();
            if(v != -1)
                return v;
            else
                return calculateColDirection();

        } else {
            int h = calculateColDirection();

            if(h != -1)
                return h;
            else
                return calculateRowDirection();
        }
    }

    protected int calculateColDirection() {
        if(_bomber.getXTile() < _e.getXTile())
            return 3;
        else if(_bomber.getXTile() > _e.getXTile())
            return 1;

        return -1;
    }

    protected int calculateRowDirection() {
        if(_bomber.getYTile() < _e.getYTile())
            return 0;
        else if(_bomber.getYTile() > _e.getYTile())
            return 2;
        return -1;
    }
}
