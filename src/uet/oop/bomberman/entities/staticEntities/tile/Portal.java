package uet.oop.bomberman.entities.staticEntities.tile;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.dynamicEntities.Bomber;
import uet.oop.bomberman.entities.staticEntities.Tile;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Audio;

import java.io.File;


public class Portal extends Tile {
    private Audio portalSound;


    public Portal(int x, int y, Sprite sprite) {
        super(x, y, sprite);

        portalSound = new Audio(new File("res/Sound/PortalSound.wav"));
    }

    @Override
    public boolean collide(Entity e) {
        // TODO: xử lý khi Bomber đi vào
        if(e instanceof Bomber) {

            if(!BombermanGame.getBoard().detectNoEnemies()) //Đi vào khi tiêu diệt hết Enemy
                return false;

            if(e.getXTile() == getX() && e.getYTile() == getY()) {
                if(BombermanGame.getBoard().detectNoEnemies()) {
                    portalSound.play();
                    BombermanGame.getBoard().nextLevel();
                }
            }

            return true;
        }
        return false;
    }

}
