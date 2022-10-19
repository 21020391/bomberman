package uet.oop.bomberman.entities.dynamicEntities;

import uet.oop.bomberman.entities.AnimateEntity;
import uet.oop.bomberman.entities.Items.Board;

public abstract class Character extends AnimateEntity {
    protected Board _board;
    protected int _direction = -1;
    protected boolean _alive = true;
    protected boolean _moving = false;
    public int _timeAfter = 40;

    public Character(int x, int y, Board board)
    {
        this.x = x;
        this.y = y;
        _board = board;
    }

    @Override
    public abstract void update();



    /**
     * Tính toán hướng đi
     */
    protected abstract void calculateMove();

    protected abstract void move(double xa, double ya);

    /**
     * Được gọi khi đối tượng bị tiêu diệt
     */
    public abstract void kill();

    /**
     * Xử lý hiệu ứng bị tiêu diệt
     */
    protected abstract void afterKill();

    /**
     * Kiểm tra xem đối tượng có di chuyển tới vị trí đã tính toán hay không
     */
    protected abstract boolean canMove(double x, double y);



    protected int _animate = 0;

    protected final int MAX_ANIMATE = 7500;

    protected void animate()
    {
        if (_animate < MAX_ANIMATE)
            _animate++;
        else _animate = 0;
    }
}
