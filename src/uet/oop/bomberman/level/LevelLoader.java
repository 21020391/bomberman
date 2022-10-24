package uet.oop.bomberman.level;

import uet.oop.bomberman.Board;

/**
 * Load và lưu trữ thông tin bản đồ các màn chơi
 */
public abstract class LevelLoader {

    protected int _width;
    protected int _height; // default values just for testing
    protected int _level;
    protected Board _board;

    public LevelLoader(Board board, int level) {
        _board = board;
        loadLevel(level);
    }

    public abstract void loadLevel(int level);

    public abstract void createEntities();

    public int getWidth() {
        return _width;
    }

    public int getHeight() {
        return _height;
    }

    public int getLevel() {
        return _level;
    }

    public void set_width(int _width) {
        this._width = _width;
    }

    public void set_height(int _height) {
        this._height = _height;
    }

    public void set_level(int _level) {
        this._level = _level;
    }
}
