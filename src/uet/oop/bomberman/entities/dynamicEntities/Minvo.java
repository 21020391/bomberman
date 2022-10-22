package uet.oop.bomberman.entities.dynamicEntities;


import javafx.scene.image.Image;
import uet.oop.bomberman.act.move;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame._width;
import static uet.oop.bomberman.BombermanGame.enemy;

public class Minvo extends dynamics {
    private static int swapKill = 1;
    private static int countKill = 0;
    private static boolean dir;

    public Minvo(int x, int y, Image img) {
        super( x, y, img);
    }

    public Minvo(int isMove, int swap, String direction, int count, int countToRun) {
        super(4, 1, "up", 0, 0);
    }

    public Minvo(boolean life) {
        super(life);
    }

    public Minvo() {
    }

    private void killMinvo(dynamics object) {
        if (countKill % 16 == 0) {
            if (swapKill == 1) {
                object.setImg(Sprite.minvo_dead.getFxImage());
                swapKill = 2;
            } else if (swapKill == 2) {
                object.setImg(Sprite.player_dead3.getFxImage());
                swapKill = 3;
            } else {
                enemy.remove(object);
                swapKill = 1;
            }
        }
    }
    @Override
    public void update() {
        countKill++;
        for (dynamics object : enemy) {
            if (object instanceof Minvo && !object.living)
                killMinvo(object);
        }

        if (this.y % 16 == 0 && this.x % 16 == 0) {
            if (this.x / 32 <= 1 || this.x / 32 >= _width - 2)
                dir = !dir;

            if (dir)
                move.left(this);
            else
                move.right(this);
        }

    }
}
