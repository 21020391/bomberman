package uet.oop.bomberman.entities.dynamicEntities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import static uet.oop.bomberman.BombermanGame.enemy;

public class Doll extends dynamics {
    private static int swapKill = 1;
    private static int countKill = 0;

    public Doll(int x, int y, Image img) {
        super( x, y, img);
    }

    public Doll(int moving, int changeImage, String direction, int count, int countToRun) {
        super(4, 1, "up", 0, 0);
    }

    public Doll(boolean life) {
        super(life);
    }

    public Doll() {
    }

    private void killDoll(dynamics object) {
        if (countKill % 16 == 0) {
            if (swapKill == 1) {
                object.setImg(Sprite.doll_dead.getFxImage());
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
    //...
    private void moveDoll() {}

    @Override
    public void update() {
        countKill++;
        for (dynamics object : enemy) {
            if (object instanceof Doll && !object.living)
                killDoll(object);
        }
        moveDoll();
    }
}
