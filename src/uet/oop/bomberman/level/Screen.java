package uet.oop.bomberman.level;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Arrays;

/**
 * Xử lý render cho tất cả Entity và một số màn hình phụ ra Game Panel
 */
public class Screen {
    public int[] _pixels;
    private final int _transparentColor = 0xffff00ff;

    public static int xOffset = 0, yOffset = 0;

    public Screen() {

        _pixels = new int[BombermanGame.WIDTH * BombermanGame.HEIGHT];

    }

    public void clear() {
        Arrays.fill(_pixels, 0);
    }

    public void renderEntity(int xp, int yp, Entity entity) { //save entity pixels
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < entity.getSprite().getSize(); y++) {
            int ya = y + yp; //add offset
            for (int x = 0; x < entity.getSprite().getSize(); x++) {
                int xa = x + xp; //add offset
                if(xa < -entity.getSprite().getSize() || xa >= BombermanGame.WIDTH || ya < 0 || ya >= BombermanGame.HEIGHT) break; //fix black margins
                if(xa < 0) xa = 0; //start at 0 from left
                int color = entity.getSprite().getPixel(x + y * entity.getSprite().getSize());
                if(color != _transparentColor) _pixels[xa + ya * BombermanGame.WIDTH] = color;
            }
        }
    }

    public void renderEntityWithBelowSprite(int xp, int yp, Entity entity, Sprite below) {
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < entity.getSprite().getSize(); y++) {
            int ya = y + yp;
            for (int x = 0; x < entity.getSprite().getSize(); x++) {
                int xa = x + xp;
                if(xa < -entity.getSprite().getSize() || xa >= BombermanGame.WIDTH || ya < 0 || ya >= BombermanGame.HEIGHT) break; //fix black margins
                if(xa < 0) xa = 0;
                int color = entity.getSprite().getPixel(x + y * entity.getSprite().getSize());
                if(color != _transparentColor)
                    _pixels[xa + ya * BombermanGame.WIDTH] = color;
                else
                    _pixels[xa + ya * BombermanGame.WIDTH] = below.getPixel(x + y * below.getSize());
            }
        }
    }
}
