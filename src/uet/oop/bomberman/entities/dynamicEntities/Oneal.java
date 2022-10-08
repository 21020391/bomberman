package uet.oop.bomberman.entities.dynamicEntities;


import javafx.scene.image.Image;
import uet.oop.bomberman.act.move;

import static uet.oop.bomberman.BombermanGame.bomberman;

public class Oneal extends dynamics {

    public Oneal(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() {
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
