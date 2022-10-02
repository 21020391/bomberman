package uet.oop.bomberman.act;

import uet.oop.bomberman.entities.dynamic.*;

import uet.oop.bomberman.entities.Ballom;
import uet.oop.bomberman.entities.Doll;
import uet.oop.bomberman.entities.Kondoria;
import uet.oop.bomberman.entities.Oneal;
import uet.oop.bomberman.graphics.Sprite;



public class move {
    public static int speed = 1;

    public static void isRunning(dynamics object) {
        if (object instanceof Bomber && object.getCount() > 0) {
            setDirect(object.getDirection(), object, 8 * speed);
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
            case"right":
                right_step(object);
                object.setX(object.getX() + moving);
                break;
        }
    }

    public static void down(dynamics object) {
        if (true) {
            if (object instanceof Bomber) {

            }
        }
    }

    private static void down_step(dynamics object) {
        if (object instanceof Bomber) {

        }
    }

    public static void up(dynamics object) {
        if (true) {
            if (object instanceof Bomber) {

            }
        }
    }

    public static void up_step(dynamics object) {
        if (object instanceof Bomber) {

        }
    }

    public static void left(dynamics object) {
        if (true) {
            if (object instanceof Bomber) {

            }
        }
    }

    public static void left_step(dynamics object) {
        if (object instanceof Bomber) {

        }
    }

    public static void right(dynamics object) {
        if (true) {
            if (object instanceof Bomber) {

            }
        }
    }

    public static void right_step(dynamics object) {
        if (object instanceof Bomber) {

        }
    }
}
