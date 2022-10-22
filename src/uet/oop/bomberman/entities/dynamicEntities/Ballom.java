package uet.oop.bomberman.entities.dynamicEntities;


import javafx.scene.image.Image;
import uet.oop.bomberman.act.move;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

import static uet.oop.bomberman.BombermanGame.dead_position;
import static uet.oop.bomberman.BombermanGame.enemy;

public class Ballom extends dynamics {
    private static int swapKill = 1;
    private static int countKill = 0;
    public Ballom(int moving, int changeImage, String direction, int count, int countToRun) {
        super(4, 1, "up", 0, 0);
    }

    public Ballom() {
    }
    public Ballom(int x, int y, Image img) {
        super( x, y, img);
    }

    private void killBallom(dynamics object) {
        if (countKill % 16 == 0) {
            if (swapKill == 1) {
                object.setImg(Sprite.mob_dead1.getFxImage());
                swapKill = 2;
            } else if (swapKill == 2) {
                object.setImg(Sprite.mob_dead2.getFxImage());
                swapKill = 3;
            } else if (swapKill == 3) {
                object.setImg(Sprite.mob_dead3.getFxImage());
                swapKill = 4;
            } else {
                object.setLiving(false);
                enemy.remove(object);
                swapKill = 1;
            }
        }
    }
    @Override
    public void update() {
        for (dynamics object : enemy) {
            if (dead_position[object.getX() / 32][object.getY() / 32] == ' ') {
                object.setLiving(false);
            }
        }
        countKill++;
        for (dynamics object : enemy) {
            if (object instanceof Ballom && !object.living)
                killBallom(object);
        }
        if (this.y % 16 == 0 && this.x % 16 == 0) {
            Random random = new Random();
            int dir = random.nextInt(4);
            switch (dir) {
                case 0:
                    move.down(this);
                    break;
                case 1:
                    move.up(this);
                    break;
                case 2:
                    move.left(this);
                    break;
                case 3:
                    move.right(this);
                    break;
            }
        }
    }
}
