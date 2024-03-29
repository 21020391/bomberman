package uet.oop.bomberman.entities;

import uet.oop.bomberman.graphics.IRender;
import uet.oop.bomberman.level.Screen;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.Coordinates;

/**
 * Lớp đại diện cho tất cả thực thể trong game (Bomber, Enemy, Wall, Brick,...)
 */
public abstract class Entity implements IRender {

    protected double _x, _y;
    protected boolean _removed = false;
    protected Sprite _sprite;

    //Entity có hiệu ứng hoạt hình
    protected int _animate = 0;
    protected final int MAX_ANIMATE = 7500;

    protected void animate() {
        if(_animate < MAX_ANIMATE) _animate++;
        else _animate = 0;
    }

    /**
     * Phương thức này được gọi liên tục trong vòng lặp game,
     * mục đích để xử lý sự kiện và cập nhật trạng thái Entity
     */
    @Override
    public abstract void update();

    /**
     * Phương thức này được gọi liên tục trong vòng lặp game,
     * mục đích để cập nhật hình ảnh của entity theo trạng thái
     */
    @Override
    public abstract void render(Screen screen);

    public void remove() {
        _removed = true;
    }

    public boolean isRemoved() {
        return _removed;
    }

    public Sprite getSprite() {
        return _sprite;
    }

    /**
     * Phương thức này được gọi để xử lý khi hai entity va chạm vào nhau
     */
    public abstract boolean collide(Entity e);

    public double getX() {
        return _x;
    }

    public double getY() {
        return _y;
    }

    public int getXTile() {
        return Coordinates.pixelToTile(_x + _sprite.SIZE / 2);
    }

    public int getYTile() {
        return Coordinates.pixelToTile(_y - _sprite.SIZE / 2);
    }
}
