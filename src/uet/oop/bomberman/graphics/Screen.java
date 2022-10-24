package uet.oop.bomberman.graphics;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import java.awt.*;
import java.util.Arrays;

/**
 * Xử lý render cho tất cả Entity và một số màn hình phụ ra Game Panel
 */
public class Screen {
    protected int _width, _height;
    public int[] _pixels;
    private int _transparentColor = 0xffff00ff;

    public static int xOffset = 0, yOffset = 0;

    public Screen(int width, int height) {
        _width = width;
        _height = height;

        _pixels = new int[width * height];

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
                if(xa < -entity.getSprite().getSize() || xa >= _width || ya < 0 || ya >= _height) break; //fix black margins
                if(xa < 0) xa = 0; //start at 0 from left
                int color = entity.getSprite().getPixel(x + y * entity.getSprite().getSize());
                if(color != _transparentColor) _pixels[xa + ya * _width] = color;
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
                if(xa < -entity.getSprite().getSize() || xa >= _width || ya < 0 || ya >= _height) break; //fix black margins
                if(xa < 0) xa = 0;
                int color = entity.getSprite().getPixel(x + y * entity.getSprite().getSize());
                if(color != _transparentColor)
                    _pixels[xa + ya * _width] = color;
                else
                    _pixels[xa + ya * _width] = below.getPixel(x + y * below.getSize());
            }
        }
    }
    public void drawEndGame(Graphics g, int points) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getRealWidth(), getRealHeight());

        Font font = new Font("Arial", Font.PLAIN, 20 * BombermanGame.SCALE);
        g.setFont(font);
        g.setColor(Color.white);
        drawCenteredString("GAME OVER", getRealWidth(), getRealHeight(), g);

        font = new Font("Arial", Font.PLAIN, 10 * BombermanGame.SCALE);
        g.setFont(font);
        g.setColor(Color.yellow);
        drawCenteredString("POINTS: " + points, getRealWidth(), getRealHeight() + (BombermanGame.TILES_SIZE * 2) * BombermanGame.SCALE, g);
    }

    public void drawChangeLevel(Graphics g, int level) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getRealWidth(), getRealHeight());

        Font font = new Font("Arial", Font.PLAIN, 20 * BombermanGame.SCALE);
        g.setFont(font);
        g.setColor(Color.white);
        drawCenteredString("LEVEL " + level, getRealWidth(), getRealHeight(), g);

    }

    public void drawPaused(Graphics g) {
        Font font = new Font("Arial", Font.PLAIN, 20 * BombermanGame.SCALE);
        g.setFont(font);
        g.setColor(Color.white);
        drawCenteredString("PAUSED", getRealWidth(), getRealHeight(), g);

    }

    public void drawCenteredString(String s, int w, int h, Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        //real width - len(s)
        int x = (w - fm.stringWidth(s)) / 2;
        //real height - (nua tren duong can + nua duoi duong can)
        int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
        g.drawString(s, x, y);
    }

    public int getWidth() {
        return _width;
    }

    public int getHeight() {
        return _height;
    }

    public int getRealWidth() {
        return _width * BombermanGame.SCALE;
    }

    public int getRealHeight() {
        return _height * BombermanGame.SCALE;
    }
}
