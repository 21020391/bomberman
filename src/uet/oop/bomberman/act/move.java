package uet.oop.bomberman.act;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.dynamicEntities.*;
import uet.oop.bomberman.entities.staticEntities.Brick;
import uet.oop.bomberman.entities.staticEntities.Grass;
import uet.oop.bomberman.entities.staticEntities.Wall;
import uet.oop.bomberman.graphics.Sprite;

public class move {
    public static void isRunning(dynamics object) {
        if (object instanceof Bomber && object.getCount() > 0) {
            setDirect(object.getDirection(), object, 8);
            object.setCount(object.getCount() - 1);
        }
    }
    public static boolean isCanMove( int a , int b ) {
        Entity entity = BombermanGame.getEntity(a , b);
        if (entity == null) {
            return true;
        } else if (entity instanceof Wall || entity instanceof Brick) {
            return false;
        } else {
            return (entity instanceof Grass);
        }
    }
    public static void setDirect(String direction, dynamics object, int moving) {
        switch (direction) {
            case "up":
                if (isCanMove(object.getX(), object.getY() - moving)) {
                    up_step(object);
                    object.setY(object.getY() - moving);
                }
                break;
            case "down":
                if (isCanMove(object.getX(), object.getY() + moving)) {
                    down_step(object);
                    object.setY(object.getY() + moving);
                }
                break;
            case "left":
                if (isCanMove(object.getX() - moving, object.getY())) {
                    left_step(object);
                    object.setX(object.getX() - moving);
                }
                break;
            case "right":
                if (isCanMove(object.getX() + moving, object.getY())) {
                    right_step(object);
                    object.setX(object.getX() + moving);
                }
                break;
        }
    }

    public static void down(dynamics object) {
        if (object.getX() % 32 == 0 && object.getY() % 32 == 0) {
            if (object instanceof Bomber && isCanMove(object.getX(), object.getY() + Sprite.DEFAULT_SIZE / 2)) {
                object.setDirection("down");
                object.setCount(4);
                isRunning(object);
            }
        }
    }

    private static void down_step(dynamics object) {
        // doi tuong chuyen dong
        if (object instanceof Bomber && object.getY() % 8 == 0) {
            if (object.getChangeImange() == 1) {
                object.setImg(Sprite.player_down.getFxImage());
                object.setChangeImange(2);
            } else if (object.getChangeImange() == 2) {
                object.setImg(Sprite.player_down_1.getFxImage());
                object.setChangeImange(3);
            } else if (object.getChangeImange() == 3) {
                object.setImg(Sprite.player_down.getFxImage());
                object.setChangeImange(4);
            } else {
                object.setImg(Sprite.player_down_2.getFxImage());
                object.setChangeImange(1);
            }
        }
    }

    public static void up(dynamics object) {
        if (object.getX() % 32 == 0 && object.getY() % 32 == 0) {
            if (object instanceof Bomber && isCanMove(object.getX(), object.getY() - Sprite.DEFAULT_SIZE / 2)) {
                object.setDirection("up");
                object.setCount(4);
                isRunning(object);
            }
        }
    }

    public static void up_step(dynamics object) {
        if (object instanceof Bomber && object.getY() % 8 == 0) {
            if (object.getChangeImange() == 1) {
                object.setImg(Sprite.player_up.getFxImage());
                object.setChangeImange(2);
            } else if (object.getChangeImange() == 2) {
                object.setImg(Sprite.player_up_1.getFxImage());
                object.setChangeImange(3);
            } else if (object.getChangeImange() == 3) {
                object.setImg(Sprite.player_up.getFxImage());
                object.setChangeImange(4);
            } else {
                object.setImg(Sprite.player_up_2.getFxImage());
                object.setChangeImange(1);
            }
        }
    }

    public static void left(dynamics object) {
        if (object.getMoving() % 32 == 0 && object.getY() % 32 == 0) {
            if (object instanceof Bomber && isCanMove(object.getX() - Sprite.DEFAULT_SIZE / 2, object.getY())) {
                object.setDirection("left");
                object.setCount(4);
                isRunning(object);
            }
        }
    }

    public static void left_step(dynamics object) {
        if (object instanceof Bomber && object.getX() % 8 == 0) {
            if (object.getChangeImange() == 1) {
                object.setImg(Sprite.player_left.getFxImage());
                object.setChangeImange(2);
            } else if (object.getChangeImange() == 2) {
                object.setImg(Sprite.player_left_1.getFxImage());
                object.setChangeImange(3);
            } else if (object.getChangeImange() == 3) {
                object.setImg(Sprite.player_left.getFxImage());
                object.setChangeImange(4);
            } else {
                object.setImg(Sprite.player_left_2.getFxImage());
                object.setChangeImange(1);
            }
        }
    }

    public static void right(dynamics object) {
        if (object.getX() % 32 == 0 && object.getY() % 32 == 0) {
            if (object instanceof Bomber && isCanMove(object.getX() + Sprite.DEFAULT_SIZE / 2, object.getY())) {
                object.setDirection("right");
                object.setCount(4);
                isRunning(object);
            }
        }
    }

    public static void right_step(dynamics object) {
        if (object instanceof Bomber && object.getY() % 8 == 0) {
            if (object.getChangeImange() == 1) {
                object.setImg(Sprite.player_right.getFxImage());
                object.setChangeImange(2);
            } else if (object.getChangeImange() == 2) {
                object.setImg(Sprite.player_right_1.getFxImage());
                object.setChangeImange(3);
            } else if (object.getChangeImange() == 3) {
                object.setImg(Sprite.player_right.getFxImage());
                object.setChangeImange(4);
            } else {
                object.setImg(Sprite.player_right_2.getFxImage());
                object.setChangeImange(1);
            }
        }
    }
}
