package uet.oop.bomberman.entities.dynamicEntities;


import javafx.scene.image.Image;
import uet.oop.bomberman.act.move;

import java.util.Random;

public class Ballom extends dynamics {
    public Ballom() {
    }
    public Ballom(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() {
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
