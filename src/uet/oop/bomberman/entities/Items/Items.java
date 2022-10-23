package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class Items extends Entity {
    protected boolean receive = false;


    /**
     * khai bao ham khong tham so.
     */

    public Items() {

    }

    /**
     * khai bao ham chua tham so.
     */

    public Items(int x, int y, Image img) {
        super(x, y, img);
    }
    private static final int BOMBRADIUS = 1;
    private static final double BOMBERSPEED = 1.0;
    protected static int bombRadius = BOMBRADIUS;
    protected static double bomberSpeed = BOMBERSPEED;
    public Items(boolean receive) {
        this.receive = receive;
    }

    /**
     * getter/setter.
     */

    public boolean isReceive() {
        return receive;
    }

    public void setReceive(boolean receive) {
        this.receive = receive;
    }

    public static int getBombRadius() {
        return bombRadius;
    }

    public static void setBombRadius(int bombRadius) {
        Items.bombRadius = bombRadius;
    }

    public static double getBomberSpeed() {
        return bomberSpeed;
    }

    public static void setBomberSpeed(double bomberSpeed) {
        Items.bomberSpeed = bomberSpeed;
    }



}
