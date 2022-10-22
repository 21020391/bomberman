package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

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

    @Override
    public void update() {

    }
}
