package uet.oop.bomberman;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Bomb.FlameSegment;
import uet.oop.bomberman.entities.dynamicEntities.Bomber;
import uet.oop.bomberman.entities.dynamicEntities.Character;
import uet.oop.bomberman.graphics.IRender;
import uet.oop.bomberman.level.Screen;
import uet.oop.bomberman.act.event.KeyBoard;
import uet.oop.bomberman.level.loadFile;
import uet.oop.bomberman.level.LevelLoader;

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

    private int _time = BombermanGame.TIME;
    private int _points = BombermanGame.POINTS;
    private ArrayList<Integer> _maxPoint = new ArrayList<>(5); // luu diem so ma nguoi choi dat duoc sau moi lan bomber chet
    public int maxPoint = _points;

    public Board(BombermanGame game, KeyBoard input, Screen screen) {
        _game = game;
        _input = input;
        _screen = screen;

        loadLevel(1); //start in level 1
    }

    @Override
    public void update() {
        if( _game.isPaused() ) return;

        //updateEntities();
        for (Entity entity : _entities) {
            entity.update();
        }

        //updateCharacters();
        Iterator<Character> itr = _characters.iterator();

        while(itr.hasNext() && !_game.isPaused())
            itr.next().update();

        //updateBombs()
        for (Bomb bomb : _bombs) bomb.update();

        //detectEndGame()
        if(_time <= 0)
            endGame();

        for (int i = 0; i < _characters.size(); i++) {
            Character a = _characters.get(i);
            if(a.isRemoved()) _characters.remove(i);
        }
    }

    @Override
    public void render(Screen screen) {
        if( _game.isPaused() ) return;

        for (int y = 0; y < BombermanGame.HEIGHT / BombermanGame.TILES_SIZE; y++) {
            for (int x = 0; x < BombermanGame.WIDTH / BombermanGame.TILES_SIZE; x++) {
                _entities[x + y * _levelLoader.getWidth()].render(screen);
            }
        }

        //renderBombs(screen)
        for (Bomb bomb : _bombs) bomb.render(screen);

        //renderCharacter(screen)
        for (Character character : _characters) character.render(screen);

    }

    public void nextLevel() {
        loadLevel(_levelLoader.getLevel() + 1);
    }

    public void loadLevel(int level) {
        _time = BombermanGame.TIME;
        BombermanGame.set_screenToShow(2);
        _game.resetScreenDelay();
        _game.resetBomberSpeed();
        _game.resetBombRadius();
        _game.resetBombRate();
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

    public void endGame() {
        BombermanGame.set_screenToShow(1);
        _game.resetScreenDelay();
        _game.resetBombRate();
        _game.resetBombRadius();
        _game.resetBomberSpeed();
        _game.pause();
        _screen.clear();
        _maxPoint.add(_points);
    }

    public void afterEndGame() {
        BombermanGame.set_screenToShow(3);
        loadLevel(1);
    }

    public boolean detectNoEnemies() {
        int total = 0;
        for (Character character : _characters) {
            if (!(character instanceof Bomber))
                ++total;
        }

        return total == 0;
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

    public int getTime() {
        return _time;
    }

    public int getPoints() {
        return _points;
    }

    public void addPoints(int points) {
        this._points += points;
    }

    public int get_maxPoint() {
        for (Integer integer : _maxPoint) {
            if (integer > maxPoint) {
                maxPoint = integer;
            }
        }
        return maxPoint;
    }

    public void resetPoint() {
        _points = BombermanGame.POINTS;
    }

    public LevelLoader get_levelLoader() {
        return _levelLoader;
    }
}
