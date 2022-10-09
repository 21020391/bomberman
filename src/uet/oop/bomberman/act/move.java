package uet.oop.bomberman.act;

import uet.oop.bomberman.entities.dynamicEntities.*;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;


public class move {
    public static void isRunning(dynamics object) {
        if (object instanceof Bomber && object.getCount() > 0) {
            setDirect(object.getDirection(), object, 8);
            object.setCount(object.getCount() - 1);
        }
        if ((object instanceof Ballom || object instanceof Oneal
                || object instanceof Doll || object instanceof Kondoria)
                && object.getCount() > 0) {
            setDirect(object.getDirection(), object, 4);
            object.setCount(object.getCount() - 1);
        }
    }
    public static void setDirect(String direction, dynamics object, int moving) {
        switch (direction) {
            case "up":
                up_step(object);
                object.setY(object.getY() - moving);
                break;
            case "down":
                down_step(object);
                object.setY(object.getY() + moving);
                break;
            case "left":
                left_step(object);
                object.setX(object.getX() - moving);
                break;
            case "right":
                right_step(object);
                object.setX(object.getX() + moving);
                break;
        }
    }
    public static void down(dynamics object) {
        if (object.getX() % 32 == 0 && object.getY() % 32 == 0) {
            if (object instanceof Bomber && isCanMove_down(object)) {
                object.setDirection("down");
                object.setCount(4);
                isRunning(object);
            }
            if ((object instanceof Ballom || object instanceof Oneal || object instanceof Doll)
                    && isCanMove_down(object)) {
                object.setDirection("down");
                object.setCount(8);
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
        if (object instanceof Ballom && object.getY() % 8 == 0) {
            if (object.getChangeImange() == 1) {
                object.setImg(Sprite.balloom_right1.getFxImage());
                object.setChangeImange(2);
            } else if (object.getChangeImange() == 2) {
                object.setImg(Sprite.balloom_right2.getFxImage());
                object.setChangeImange(3);
            } else if (object.getChangeImange() == 3) {
                object.setImg(Sprite.balloom_right3.getFxImage());
                object.setChangeImange(4);
            } else {
                object.setImg(Sprite.balloom_right2.getFxImage());
                object.setChangeImange(1);
            }
        }
        if (object instanceof Oneal && object.getY() % 8 == 0) {
            if (object.getChangeImange() == 1) {
                object.setImg(Sprite.oneal_right1.getFxImage());
                object.setChangeImange(2);
            } else if (object.getChangeImange() == 2) {
                object.setImg(Sprite.oneal_right2.getFxImage());
                object.setChangeImange(3);
            } else if (object.getChangeImange() == 3) {
                object.setImg(Sprite.oneal_right3.getFxImage());
                object.setChangeImange(4);
            } else {
                object.setImg(Sprite.oneal_right2.getFxImage());
                object.setChangeImange(1);
            }
        }
        if (object instanceof Doll && object.getY() % 8 == 0) {
            if (object.getChangeImange() == 1) {
                object.setImg(Sprite.doll_left1.getFxImage());
                object.setChangeImange(2);
            } else if (object.getChangeImange() == 2) {
                object.setImg(Sprite.doll_left2.getFxImage());
                object.setChangeImange(3);
            } else if (object.getChangeImange() == 3) {
                object.setImg(Sprite.doll_left3.getFxImage());
                object.setChangeImange(4);
            } else {
                object.setImg(Sprite.doll_left2.getFxImage());
                object.setChangeImange(1);
            }
        }
    }

    public static void up(dynamics object) {
        if (object.getX() % 32 == 0 && object.getY() % 32 == 0) {
            if (object instanceof Bomber && isCanMove_up(object)) {
                object.setDirection("up");
                object.setCount(4);
                isRunning(object);
            }
            if ((object instanceof Ballom || object instanceof Oneal || object instanceof Doll)
                    && isCanMove_up(object)) {
                object.setDirection("up");
                object.setCount(8);
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
        if (object instanceof Ballom && object.getY() % 8 == 0) {
            if (object.getChangeImange() == 1) {
                object.setImg(Sprite.balloom_left1.getFxImage());
                object.setChangeImange(2);
            } else if (object.getChangeImange() == 2) {
                object.setImg(Sprite.balloom_left2.getFxImage());
                object.setChangeImange(3);
            } else if (object.getChangeImange() == 3) {
                object.setImg(Sprite.balloom_left3.getFxImage());
                object.setChangeImange(4);
            } else {
                object.setImg(Sprite.balloom_left2.getFxImage());
                object.setChangeImange(1);
            }
        }
        if (object instanceof Oneal && object.getY() % 8 == 0) {
            if (object.getChangeImange() == 1) {
                object.setImg(Sprite.oneal_left1.getFxImage());
                object.setChangeImange(2);
            } else if (object.getChangeImange() == 2) {
                object.setImg(Sprite.oneal_left2.getFxImage());
                object.setChangeImange(3);
            } else if (object.getChangeImange() == 3) {
                object.setImg(Sprite.oneal_left3.getFxImage());
                object.setChangeImange(4);
            } else {
                object.setImg(Sprite.oneal_left2.getFxImage());
                object.setChangeImange(1);
            }
        }
        if (object instanceof Doll && object.getY() % 8 == 0) {
            if (object.getChangeImange() == 1) {
                object.setImg(Sprite.doll_right1.getFxImage());
                object.setChangeImange(2);
            } else if (object.getChangeImange() == 2) {
                object.setImg(Sprite.doll_right2.getFxImage());
                object.setChangeImange(3);
            } else if (object.getChangeImange() == 3) {
                object.setImg(Sprite.doll_right3.getFxImage());
                object.setChangeImange(4);
            } else {
                object.setImg(Sprite.doll_right2.getFxImage());
                object.setChangeImange(1);
            }
        }
    }

    public static void left(dynamics object) {
        if (object.getMoving() % 32 == 0 && object.getY() % 32 == 0) {
            if (object instanceof Bomber && isCanMove_left(object)) {
                object.setDirection("left");
                object.setCount(4);
                isRunning(object);
            }
            if ((object instanceof Ballom || object instanceof Oneal
                    || object instanceof Doll || object instanceof Kondoria)
                    && isCanMove_left(object)) {
                object.setDirection("left");
                object.setCount(8);
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
        if (object instanceof Ballom && object.getY() % 8 == 0) {
            if (object.getChangeImange() == 1) {
                object.setImg(Sprite.balloom_right1.getFxImage());
                object.setChangeImange(2);
            } else if (object.getChangeImange() == 2) {
                object.setImg(Sprite.balloom_right2.getFxImage());
                object.setChangeImange(3);
            } else if (object.getChangeImange() == 3) {
                object.setImg(Sprite.balloom_right3.getFxImage());
                object.setChangeImange(4);
            } else {
                object.setImg(Sprite.balloom_right2.getFxImage());
                object.setChangeImange(1);
            }
        }
        if (object instanceof Oneal && object.getY() % 8 == 0) {
            if (object.getChangeImange() == 1) {
                object.setImg(Sprite.oneal_right1.getFxImage());
                object.setChangeImange(2);
            } else if (object.getChangeImange() == 2) {
                object.setImg(Sprite.oneal_right2.getFxImage());
                object.setChangeImange(3);
            } else if (object.getChangeImange() == 3) {
                object.setImg(Sprite.oneal_right3.getFxImage());
                object.setChangeImange(4);
            } else {
                object.setImg(Sprite.oneal_right2.getFxImage());
                object.setChangeImange(1);
            }
        }
        if (object instanceof Doll && object.getY() % 8 == 0) {
            if (object.getChangeImange() == 1) {
                object.setImg(Sprite.doll_left1.getFxImage());
                object.setChangeImange(2);
            } else if (object.getChangeImange() == 2) {
                object.setImg(Sprite.doll_left2.getFxImage());
                object.setChangeImange(3);
            } else if (object.getChangeImange() == 3) {
                object.setImg(Sprite.doll_left3.getFxImage());
                object.setChangeImange(4);
            } else {
                object.setImg(Sprite.doll_left2.getFxImage());
                object.setChangeImange(1);
            }
        }
        if (object instanceof Kondoria && object.getY() % 8 == 0) {
            if (object.getChangeImange() == 1) {
                object.setImg(Sprite.kondoria_left1.getFxImage());
                object.setChangeImange(2);
            } else if (object.getChangeImange() == 2) {
                object.setImg(Sprite.kondoria_left2.getFxImage());
                object.setChangeImange(3);
            } else if (object.getChangeImange() == 3) {
                object.setImg(Sprite.kondoria_left3.getFxImage());
                object.setChangeImange(4);
            } else {
                object.setImg(Sprite.kondoria_left2.getFxImage());
                object.setChangeImange(1);
            }
        }
    }

    public static void right(dynamics object) {
        if (object.getX() % 32 == 0 && object.getY() % 32 == 0) {
            if (object instanceof Bomber && isCanMove_right(object)) {
                object.setDirection("right");
                object.setCount(4);
                isRunning(object);
            }
            if ((object instanceof Ballom || object instanceof Oneal
                    || object instanceof Doll || object instanceof Kondoria)
                    && isCanMove_right(object)) {
                object.setDirection("right");
                object.setCount(8);
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
        if (object instanceof Ballom && object.getY() % 8 == 0) {
            if (object.getChangeImange() == 1) {
                object.setImg(Sprite.balloom_left1.getFxImage());
                object.setChangeImange(2);
            } else if (object.getChangeImange() == 2) {
                object.setImg(Sprite.balloom_left2.getFxImage());
                object.setChangeImange(3);
            } else if (object.getChangeImange() == 3) {
                object.setImg(Sprite.balloom_left3.getFxImage());
                object.setChangeImange(4);
            } else {
                object.setImg(Sprite.balloom_left2.getFxImage());
                object.setChangeImange(1);
            }
        }
        if (object instanceof Oneal && object.getY() % 8 == 0) {
            if (object.getChangeImange() == 1) {
                object.setImg(Sprite.oneal_left1.getFxImage());
                object.setChangeImange(2);
            } else if (object.getChangeImange() == 2) {
                object.setImg(Sprite.oneal_left2.getFxImage());
                object.setChangeImange(3);
            } else if (object.getChangeImange() == 3) {
                object.setImg(Sprite.oneal_left3.getFxImage());
                object.setChangeImange(4);
            } else {
                object.setImg(Sprite.oneal_left2.getFxImage());
                object.setChangeImange(1);
            }
        }
        if (object instanceof Doll && object.getY() % 8 == 0) {
            if (object.getChangeImange() == 1) {
                object.setImg(Sprite.doll_right1.getFxImage());
                object.setChangeImange(2);
            } else if (object.getChangeImange() == 2) {
                object.setImg(Sprite.doll_right2.getFxImage());
                object.setChangeImange(3);
            } else if (object.getChangeImange() == 3) {
                object.setImg(Sprite.doll_right3.getFxImage());
                object.setChangeImange(4);
            } else {
                object.setImg(Sprite.doll_right2.getFxImage());
                object.setChangeImange(1);
            }
        }
        if (object instanceof Kondoria && object.getY() % 8 == 0) {
            if (object.getChangeImange() == 1) {
                object.setImg(Sprite.kondoria_right1.getFxImage());
                object.setChangeImange(2);
            } else if (object.getChangeImange() == 2) {
                object.setImg(Sprite.kondoria_right2.getFxImage());
                object.setChangeImange(3);
            } else if (object.getChangeImange() == 3) {
                object.setImg(Sprite.kondoria_right3.getFxImage());
                object.setChangeImange(4);
            } else {
                object.setImg(Sprite.kondoria_right2.getFxImage());
                object.setChangeImange(1);
            }
        }
    }
}
