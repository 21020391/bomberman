package uet.oop.bomberman.act.frame;

import uet.oop.bomberman.BombermanGame;

import java.awt.*;

import static uet.oop.bomberman.BombermanGame.SCALE;


public class MenuStage {

    public MenuStage() {}
    public void drawEndGame(Graphics g, int points) {
        g.setColor(Color.black);
        g.fillRect(0, 0, BombermanGame.WIDTH * SCALE, BombermanGame.HEIGHT * SCALE);

        Font font = new Font("Arial", Font.PLAIN, 20 * SCALE);
        g.setFont(font);
        g.setColor(Color.white);
        drawCenteredString("GAME OVER", BombermanGame.WIDTH * SCALE, BombermanGame.HEIGHT * SCALE, g);

        font = new Font("Arial", Font.PLAIN, 10 * SCALE);
        g.setFont(font);
        g.setColor(Color.yellow);
        drawCenteredString("POINTS: " + points, BombermanGame.WIDTH * SCALE, BombermanGame.HEIGHT * SCALE + (BombermanGame.TILES_SIZE * 2) * SCALE, g);
    }

    public void drawChangeLevel(Graphics g, int level) {
        g.setColor(Color.black);
        g.fillRect(0, 0, BombermanGame.WIDTH * SCALE, BombermanGame.HEIGHT * SCALE);

        Font font = new Font("Arial", Font.PLAIN, 20 * SCALE);
        g.setFont(font);
        g.setColor(Color.white);
        drawCenteredString("LEVEL " + level, BombermanGame.WIDTH * SCALE, BombermanGame.HEIGHT * SCALE, g);

    }

    public void drawPaused(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, BombermanGame.WIDTH * SCALE, BombermanGame.HEIGHT * SCALE);

        Font font = new Font("Arial", Font.PLAIN, 20 * SCALE);
        g.setFont(font);
        g.setColor(Color.white);
        drawCenteredString("PAUSED", BombermanGame.WIDTH * SCALE, BombermanGame.HEIGHT * SCALE, g);

    }

    public void drawCenteredString(String s, int w, int h, Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        //real width - len(s)
        int x = (w - fm.stringWidth(s)) / 2;
        //real height - (nua tren duong can + nua duoi duong can)
        int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
        g.drawString(s, x, y);
    }
}