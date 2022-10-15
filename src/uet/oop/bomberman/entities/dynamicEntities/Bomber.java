package uet.oop.bomberman.entities.dynamicEntities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;

public class Bomber extends dynamics {
    public static int changeKill = 1;
    private static int countKill = 0;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }
    public Bomber(int moving, int changImage, String direction, int count, int countToRun) {
        super(8, 1, "down", 0, 0);
    }
    public Bomber() {}

    private void killBomber(dynamics bomberman) {
        if (countKill % 16 == 0) {
            if (changeKill == 1) {
                bomberman.setImg(Sprite.player_dead1.getFxImage());
                changeKill = 2;
            } else if (changeKill == 2) {
                bomberman.setImg(Sprite.player_dead2.getFxImage());
                changeKill = 3;
            } else if (changeKill == 3) {
                bomberman.setImg(Sprite.player_dead3.getFxImage());
                changeKill = 4;
            } else {
                running = false;
            }
        }
    }

    private void checkBombs() {
        if (dead_position[bomberman.getX() / 32][bomberman.getY() / 32] == ' ')
            bomberman.setLiving(false);
    }

    private void checkEnemy() {
        int bombermanX = bomberman.getX() / 32;
        int bombermanY = bomberman.getY() / 32;
        for (dynamics Enemy : enemy) {
            int enemyX = Enemy.getX() / 32;
            int enemyY = Enemy.getY() / 32;

            if (bombermanX == enemyX && bombermanY == enemyY
                || bombermanX == enemyX && bombermanY == enemyY + 1
                || bombermanX == enemyX && bombermanY == enemyY - 1
                || bombermanX == enemyX + 1 && bombermanY == enemyY
                || bombermanX == enemyX - 1 && bombermanY == enemyY) {
                bomberman.living = false;
                break;
            }
        }
    }

    @Override
    public void update() {
        checkBombs();
        checkEnemy();
        countKill++;
        if (!bomberman.living) {
            killBomber(bomberman);
        }
    }
}
