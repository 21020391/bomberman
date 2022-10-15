package uet.oop.bomberman.entities.dynamicEntities;


import javafx.scene.image.Image;
import uet.oop.bomberman.act.move;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.bomberman;
import static uet.oop.bomberman.BombermanGame.enemy;

public class Oneal extends dynamics {
    private static int swapKill = 1;
    private static int countKill = 0;

    public Oneal(int x, int y, Image img) {
        super( x, y, img);
    }

    public Oneal(int isMove, int swap, String direction, int count, int countToRun) {
        super(4, 1, "up", 0, 0);
    }

    public Oneal(boolean life) {
        super(life);
    }

    public Oneal() {
    }
    private void killOneal(dynamics object) {
        if (countKill % 16 == 0) {
            if (swapKill == 1) {
                object.setImg(Sprite.oneal_dead.getFxImage());
                swapKill = 2;
            } else if (swapKill == 2) {
                object.setImg(Sprite.player_dead3.getFxImage());
                swapKill = 3;
            } else {
                object.setLiving(false);
                enemy.remove(object);
                swapKill = 1;
            }
        }
    }

    @Override
    public void update() {
        countKill++;
        for (dynamics object: enemy) {
            if (object instanceof Oneal && !object.living)
                killOneal(object);
        }
        if (this.y % 16 == 0 && this.x % 16 == 0) {
            if (bomberman.getX() < this.x) {
                move.left(this);
            }
            if (bomberman.getX() > this.x) {
                move.right(this);
            }
            if (bomberman.getY() > this.y) {
                move.down(this);
            }
            if (bomberman.getY() < this.y) {
                move.up(this);
            }
        }
    }
}
