package uet.oop.bomberman.entities.dynamicEntities.enemy;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Bomb.Flame;
import uet.oop.bomberman.entities.dynamicEntities.Bomber;
import uet.oop.bomberman.entities.dynamicEntities.Character;
import uet.oop.bomberman.entities.dynamicEntities.intelligent.AI;
import uet.oop.bomberman.level.Screen;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.Coordinates;
import uet.oop.bomberman.sound.Audio;

import java.io.File;

public abstract class Enemy extends Character {

    protected int _points;

    protected double _speed;
    protected AI _ai;

    protected final double MAX_STEPS;
    protected final double rest;
    protected double _steps;

    protected int _finalAnimation = 30;
    protected Sprite _deadSprite;

    //TODO:
    private Audio EnemyDieSound;

    public Enemy(int x, int y, Board board, Sprite dead, double speed, int points) {
        super(x, y, board);

        _points = points;
        _speed = speed;

        MAX_STEPS = BombermanGame.TILES_SIZE / _speed;
        rest = (MAX_STEPS - (int) MAX_STEPS) / MAX_STEPS;
        _steps = MAX_STEPS;

        _timeAfter = 20;
        _deadSprite = dead;
        EnemyDieSound = new Audio(new File("res/Sound/EnemyDie.wav"));
    }

    @Override
    public void update() {
        animate();

        if(!_alive) {
            afterKill();
        } else {
            calculateMove();
        }
    }

    @Override
    public void render(Screen screen) {

        if(_alive)
            chooseSprite();
        else {
            if(_timeAfter > 0) {
                _sprite = _deadSprite;
                _animate = 0;
            } else {
                _sprite = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, _animate, 60);
            }

        }

        screen.renderEntity((int)_x, (int)_y - _sprite.SIZE, this);
    }

    @Override
    public void calculateMove() {
        // TODO: Tính toán hướng đi và di chuyển Enemy theo _ai và cập nhật giá trị cho _direction
        // TODO: sử dụng canMove() để kiểm tra xem có thể di chuyển tới điểm đã tính toán hay không
        // TODO: sử dụng move() để di chuyển
        // TODO: nhớ cập nhật lại giá trị cờ _moving khi thay đổi trạng thái di chuyển
        int xa = 0, ya = 0;
        if(_steps <= 0){
            _direction = _ai.calculateDirection();
            _steps = MAX_STEPS;
        }

        if(_direction == 0) ya--;
        if(_direction == 2) ya++;
        if(_direction == 3) xa--;
        if(_direction == 1) xa++;

        if(canMove(xa, ya)) {
            _steps -= 1 + rest;
            move(xa * _speed, ya * _speed);
            _moving = true;
        } else {
            _steps = 0;
            _moving = false;
        }
    }

    @Override
    public void move(double xa, double ya) {
        if(!_alive) return;
        _y += ya;
        _x += xa;
    }

    @Override
    public boolean canMove(double x, double y) {
        // TODO: kiểm tra có đối tượng tại vị trí chuẩn bị di chuyển đến và có thể di chuyển tới đó hay không
        double xr = _x, yr = _y - 16; //trừ y để có kết quả chính xác hơn
        //System.out.println(xr + " " + yr);

        if(_direction == 0) { yr += _sprite.getSize() -1 ; xr += _sprite.getSize()/2; }
        if(_direction == 1) {yr += _sprite.getSize()/2; xr += 1;}
        if(_direction == 2) { xr += _sprite.getSize()/2; yr += 1;}
        if(_direction == 3) { xr += _sprite.getSize() -1; yr += _sprite.getSize()/2;}

        int xx = Coordinates.pixelToTile(xr) +(int)x;
        int yy = Coordinates.pixelToTile(yr) +(int)y;

        Entity a = _board.getEntity(xx, yy, this);

        return a.collide(this);
    }

    @Override
    public boolean collide(Entity e) {
        // TODO: xử lý va chạm với Flame
        // TODO: xử lý va chạm với Bomber
        //TODO: xét va chạm với Enemy khác

        if(e instanceof Flame) {
            kill();
            System.out.println("enemy chết !");
            return false;
        }

        if(e instanceof Bomber) {
            ((Bomber) e).kill();
            return false;
        }

        return !(e instanceof Enemy);
    }

    @Override
    public void kill() {
        EnemyDieSound.play();
        if(!_alive) return;
        _alive = false;

        _board.addPoints(_points);
    }


    @Override
    protected void afterKill() {
        if(_timeAfter > 0) --_timeAfter;
        else {
            if(_finalAnimation > 0) --_finalAnimation;
            else
                remove();
        }
    }

    protected abstract void chooseSprite();
}
