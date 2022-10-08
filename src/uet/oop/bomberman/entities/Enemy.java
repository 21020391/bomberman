package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

import java.lang.management.MemoryUsage;


public class Enemy extends Entity1 {
    //Dem de chay theo khung
    protected int countToRun;

    //Dem buoc nhay
    protected int countToJumps;

    //huong cua nguoi choi
    protected String direction;

    //di chuyen theo set background
    protected int move;

    //mang cua enemy
    protected boolean life;

    //Hoan doi hinh anh
    protected int swap;

    /**
     *khai bao ha, chua tham so.
     */

    public Enemy(int iUnit, int yUnit, Image img) {
        super(iUnit, yUnit, img);
    }

    public Enemy(int countToRun, int countToJumps, String direction, int move, int swap) {

        this.countToRun = countToRun;
        this.countToJumps = countToJumps;
        this.direction = direction;
        this.move = move;
        this.swap = swap;
    }

    public Enemy(boolean life) {
        this.life = life;
    }

    /**
     * getter/setter.
     */

    public int getCountToRun() {
        return countToRun;
    }

    public void setCountToRun(int countToRun) {
        this.countToRun = countToRun;
    }

    public int getCountToJumps() {
        return countToJumps;
    }

    public void setCountToJumps(int countToJumps) {
        this.countToJumps = countToJumps;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public boolean isLife() {
        return life;
    }

    public void setLife(boolean life) {
        this.life = life;
    }

    public int getSwap() {
        return swap;
    }

    public void setSwap(int swap) {
        this.swap = swap;
    }

    @Override
    public void update() {

    }
}


