package uet.oop.bomberman.entities.Bomb;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.dynamicEntities.Bomber;
import uet.oop.bomberman.entities.dynamicEntities.Character;
import uet.oop.bomberman.act.Screen;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.Coordinates;
import uet.oop.bomberman.sound.Audio;

import java.io.File;

public class Bomb extends Entity {

    protected double _timeToExplode = 120; //2 seconds
    public int _timeAfter = 20;

    protected Board _board;
    protected Flame[] _flames;
    protected boolean _exploded = false;
    protected boolean _allowedToPassThru = true;

    //TODO:
    private Audio explosionBombSound = new Audio(new File("res/Sound/bomb_explosion.wav"));

    public Bomb(int x, int y, Board board) {
        _x = x;
        _y = y;
        _board = board;
        _sprite = Sprite.bomb;
    }

    @Override
    public void update() {
        if(_timeToExplode > 0)
            _timeToExplode--;
        else {
            if(!_exploded)
                explode();
            else
                updateFlames();

            if(_timeAfter > 0)
                _timeAfter--;
            else
                remove();
        }

        animate();
    }

    @Override
    public void render(Screen screen) {
        if(_exploded) {
            _sprite =  Sprite.bomb_exploded2;
            renderFlames(screen);
        } else
            _sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, _animate, 60);

        int xt = (int)_x << 4; // dich x sang trai
        int yt = (int)_y << 4;

        screen.renderEntity(xt, yt , this);
    }

    public void renderFlames(Screen screen) {
        for (Flame flame : _flames) {
            flame.render(screen);
        }
    }

    public void updateFlames() {
        for (Flame flame : _flames) {
            flame.update();
        }
    }


    /**
     * Xử lý Bomb nổ
     */
    protected void explode() {
        // TODO: xử lý khi Character đứng tại vị trí Bomb
        // TODO: tạo các Flame

        _exploded = true;
        Character a = _board.getCharacterAtExcluding((int)_x, (int)_y, null);
        if(a != null)  {
            a.kill();
        }

        _flames = new Flame[4];
        for (int i = 0; i < _flames.length; i++) {
            _flames[i] = new Flame((int)_x, (int)_y, i, BombermanGame.getBombRadius(), _board);
        }

        explosionBombSound.play();
    }

    public FlameSegment flameAt(int x, int y) {
        if(!_exploded) return null;

        for (Flame flame : _flames) {
            if (flame == null) return null;
            FlameSegment e = flame.flameSegmentAt(x, y);
            if (e != null) return e;
        }

        return null;
    }

    @Override
    public boolean collide(Entity e) {
        // TODO: xử lý khi Bomber đi ra sau khi vừa đặt bom (_allowedToPassThru)
        // TODO: xử lý va chạm với Flame của Bomb khác
        if(e instanceof Bomber) {
            double diffX = e.getX() - Coordinates.tileToPixel(getX());
            double diffY = e.getY() - Coordinates.tileToPixel(getY());

            if(!(diffX >= -10 && diffX < 16 && diffY >= 1 && diffY <= 28)) { // sự khác biệt để xem nếu người chơi đã di chuyển ra khỏi bom, các giá trị được kiểm tra
                _allowedToPassThru = false;
            }

            return _allowedToPassThru;
        }

        if(e instanceof Flame) {
            _timeToExplode = 0;
            System.out.println("va cham voi flame cua bom khac!");
            return true;
        }

        return false;
    }
}
