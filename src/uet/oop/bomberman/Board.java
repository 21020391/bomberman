package uet.oop.bomberman;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Bomb.FlameSegment;
import uet.oop.bomberman.entities.dynamicEntities.Bomber;
import uet.oop.bomberman.entities.dynamicEntities.Character;
import uet.oop.bomberman.graphics.IRender;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.act.KeyBoard;
import uet.oop.bomberman.level.loadFile;
import uet.oop.bomberman.level.LevelLoader;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Quản lý thao tác điều khiển, load level, render các màn hình của game
 */
public class Board implements IRender {
    protected LevelLoader _levelLoader;
    protected BombermanGame _game;
    protected KeyBoard _input;
    protected Screen _screen;


    public Entity[] _entities;
    public List<Character> _characters = new ArrayList<>();
    protected List<Bomb> _bombs = new ArrayList<>();
    private int _screenToShow = -1; //1:endgame, 2:changelevel, 3:paused

    private int _time = BombermanGame.TIME;
    private int _points = BombermanGame.POINTS;

    public Board(BombermanGame game, KeyBoard input, Screen screen) {
        _game = game;
        _input = input;
        _screen = screen;

        loadLevel(1); //start in level 1
    }

    @Override
    public void update() {
        if( _game.isPaused() ) return;

        updateEntities();
        updateCharacters();
        updateBombs();
        detectEndGame();

        for (int i = 0; i < _characters.size(); i++) {
            Character a = _characters.get(i);
            if(a.isRemoved()) _characters.remove(i);
        }
    }

    @Override
    public void render(Screen screen) {
        if( _game.isPaused() ) return;

        for (int y = 0; y < screen.getHeight() / BombermanGame.TILES_SIZE; y++) {
            for (int x = 0; x < screen.getWidth() / BombermanGame.TILES_SIZE; x++) {
                _entities[x + y * _levelLoader.getWidth()].render(screen);
            }
        }

        renderBombs(screen);
        renderCharacter(screen);

    }

    public void nextLevel() {
        loadLevel(_levelLoader.getLevel() + 1);
    }

    public void loadLevel(int level) {
        _time = BombermanGame.TIME;
        _screenToShow = 2;
        _game.resetScreenDelay();
        _game.pause();
        _characters.clear();
        _bombs.clear();

        try {
            _levelLoader = new loadFile(this, level);
            _entities = new Entity[_levelLoader.getHeight() * _levelLoader.getWidth()];

            _levelLoader.createEntities();
        } catch (Exception e) {
            endGame();
        }
    }

    protected void detectEndGame() {
        if(_time <= 0)
            endGame();
    }

    public void endGame() {
        _screenToShow = 1;
        _game.resetScreenDelay();
        _game.pause();
    }

    public boolean detectNoEnemies() {
        int total = 0;
        for (Character character : _characters) {
            if (!(character instanceof Bomber))
                ++total;
        }

        return total == 0;
    }

    public void drawScreen(Graphics g) {
        switch (_screenToShow) {
            case 1:
                _screen.drawEndGame(g, _points);
                break;
            case 2:
                _screen.drawChangeLevel(g, _levelLoader.getLevel());
                break;
            case 3:
                _screen.drawPaused(g);
                break;
        }
    }

    public Entity getEntity(double x, double y, Character m) {

        Entity res = null;

        res = getFlameSegmentAt((int)x, (int)y);
        if( res != null) return res;

        res = getBombAt(x, y);
        if( res != null) return res;

        res = getCharacterAtExcluding((int)x, (int)y, m);
        if( res != null) return res;

        res = getEntityAt((int)x, (int)y);

        return res;
    }

    public List<Bomb> getBombs() {
        return _bombs;
    }

    public Bomb getBombAt(double x, double y) {
        Iterator<Bomb> bs = _bombs.iterator();
        Bomb b;
        while(bs.hasNext()) {
            b = bs.next();
            if(b.getX() == (int)x && b.getY() == (int)y)
                return b;
        }

        return null;
    }


    public Bomber getBomber() {
        Iterator<Character> itr = _characters.iterator();

        Character cur;
        while(itr.hasNext()) {
            cur = itr.next();

            if(cur instanceof Bomber)
                return (Bomber) cur;
        }

        return null;
    }

    //TODO: lấy ra Bomb trong Map
    public Bomb getBomb() {
        Iterator<Bomb> itr = _bombs.iterator();

        Bomb cur;
        while(itr.hasNext()) {
            cur = itr.next();

            if(cur != null)
                return cur;
        }

        return null;
    }

    public Character getCharacterAtExcluding(int x, int y, Character a) {
        Iterator<Character> itr = _characters.iterator();

        Character cur;
        while(itr.hasNext()) {
            cur = itr.next();
            if(cur == a) {
                continue;
            }

            if(cur.getXTile() == x && cur.getYTile() == y) {
                return cur;
            }

        }

        return null;
    }

    public FlameSegment getFlameSegmentAt(int x, int y) {
        Iterator<Bomb> bs = _bombs.iterator();
        Bomb b;
        while(bs.hasNext()) {
            b = bs.next();

            FlameSegment e = b.flameAt(x, y);
            if(e != null) {
                return e;
            }
        }

        return null;
    }

    public Entity getEntityAt(double x, double y) {
        return _entities[(int)x + (int)y * _levelLoader.getWidth()];
    }

    public void addEntity(int pos, Entity e) {
        _entities[pos] = e;
    }

    public void addCharacter(Character e) {
        _characters.add(e);
    }

    public void addBomb(Bomb e) {
        _bombs.add(e);
    }

    protected void renderCharacter(Screen screen) {

        for (Character character : _characters) character.render(screen);
    }

    protected void renderBombs(Screen screen) {

        for (Bomb bomb : _bombs) bomb.render(screen);
    }

    protected void updateEntities() {
        if( _game.isPaused() ) return;
        for (Entity entity : _entities) {
            entity.update();
        }
    }

    protected void updateCharacters() {
        if( _game.isPaused() ) return;
        Iterator<Character> itr = _characters.iterator();

        while(itr.hasNext() && !_game.isPaused())
            itr.next().update();
    }

    protected void updateBombs() {
        if( _game.isPaused() ) return;

        for (Bomb bomb : _bombs) bomb.update();
    }

    public int subtractTime() {
        if(_game.isPaused())
            return this._time;
        else
            return this._time--;
    }

    public KeyBoard getInput() {
        return _input;
    }

    public BombermanGame getGame() {
        return _game;
    }

    public int getShow() {
        return _screenToShow;
    }

    public void setShow(int i) {
        _screenToShow = i;
    }

    public int getTime() {
        return _time;
    }

    public int getPoints() {
        return _points;
    }

    public void addPoints(int points) {
        this._points += points;
    }
}
