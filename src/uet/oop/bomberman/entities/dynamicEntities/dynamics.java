package uet.oop.bomberman.entities.dynamicEntities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class dynamics extends Entity {

    // so pixel 1 lan nhan phim
    protected int moving;

    // chuyen anh dong
    protected int changeImange;

    // huong di cua bomberman
    protected String direction;

    // dem so buoc trong mot lan
    protected int count;

    //chay sau khi dem frame
    protected int countToRun;

    public dynamics(int x, int y, Image img) { super(x, y, img); }

    public int getMoving() {
        return moving;
    }

    public int getChangeImange() {
        return changeImange;
    }

    public void setChangeImange(int changeImange) {
        this.changeImange = changeImange;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCountToRun() {
        return countToRun;
    }

    public void setCountToRun(int countToRun) {
        this.countToRun = countToRun;
    }

    public dynamics() {}

    @Override
    public void update() {}


}
