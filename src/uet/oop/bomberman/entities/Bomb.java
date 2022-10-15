package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.*;

public class Bomb extends Entity {
    private static long timeBomb;
    private static long timeTmp;
    private static int bombNumber = 20;
    private static Entity bomb;
    private static int swapActive = 1;

    private static int swapExplosion = 1;

    private static final List<Entity> listBombMiddleW = new ArrayList<>();
    private static final List<Entity> listBombMiddleH = new ArrayList<>();

    public static int powerBomb = 0;
    private static int powerBombDown = 0;
    private static int powerBombUp = 0;
    private static int powerBombLeft = 0;
    private static int powerBombRight = 0;

    private static Entity edge_down = null;
    private static Entity edge_up = null;
    private static Entity edge_left = null;
    private static Entity edge_right = null;
    private static boolean isEdge = false;
    private static boolean isMiddle = false;
    public static int isBomb = 0;   //0 no bomb /1 had bomb /2 explosion

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }

    public static void putBomb() {
        if (isBomb == 0 && bombNumber > 0) {
            bombNumber--;
            isBomb = 1;
            timeBomb = System.currentTimeMillis();
            timeTmp = timeBomb;
            bomb = new Bomb(bomberman.getX() / Sprite.SCALED_SIZE,
                    bomberman.getY() / Sprite.SCALED_SIZE, Sprite.bomb.getFxImage());
            fixedEntities.add(bomb);
            idObjects[bomberman.getX() / 32][bomberman.getY() / 32] = ' ';
        }
    }

    // hinh anh bomb chuan bi no
    public static void activeBomb() {
        if (swapActive == 1) {
            bomb.setImg(Sprite.bomb.getFxImage());
            swapActive = 2;
        } else if (swapActive == 2) {
            bomb.setImg(Sprite.bomb_1.getFxImage());
            swapActive = 3;
        } else if (swapActive == 3) {
            bomb.setImg(Sprite.bomb_2.getFxImage());
            swapActive = 4;
        } else {
            bomb.setImg(Sprite.bomb_1.getFxImage());
            swapActive = 1;
        }
    }


    public static void explosionCenter() {
        if (swapExplosion == 1) {
            bomb.setImg(Sprite.bomb_exploded.getFxImage());
            dead_position[bomb.getX() / 32][bomb.getY() / 32] = ' ';
            if (block_down_bomb(bomb, powerBombDown)) {
                edge_down.setImg(Sprite.explosion_vertical_down_last.getFxImage());
                dead_position[edge_down.getX() / 32][edge_down.getY() / 32] = ' ';
            }
            if (block_up_bomb(bomb, powerBombUp)) {
                edge_up.setImg(Sprite.explosion_vertical_top_last.getFxImage());
                dead_position[edge_up.getX() / 32][edge_up.getY() / 32] = ' ';
            }
            if (block_left_bomb(bomb, powerBombLeft)) {
                edge_left.setImg(Sprite.explosion_horizontal_left_last.getFxImage());
                dead_position[edge_left.getX() / 32][edge_left.getY() / 32] = ' ';
            }
            if (block_right_bomb(bomb, powerBombRight)) {
                edge_right.setImg(Sprite.explosion_horizontal_right_last.getFxImage());
                dead_position[edge_right.getX() / 32][edge_right.getY() / 32] = ' ';
            }
            if (listBombMiddleH.size() > 0)
                for (Entity e : listBombMiddleH) {
                    e.setImg(Sprite.explosion_vertical.getFxImage());
                    dead_position[e.getX() / 32][e.getY() / 32] = ' ';
                }
            if (listBombMiddleW.size() > 0)
                for (Entity e : listBombMiddleW) {
                    e.setImg(Sprite.explosion_horizontal.getFxImage());
                    dead_position[e.getX() / 32][e.getY() / 32] = ' ';
                }
            swapExplosion = 2;

        } else if (swapExplosion == 2) {
            bomb.setImg(Sprite.bomb_exploded1.getFxImage());
            if (block_down_bomb(bomb, powerBombDown))
                edge_down.setImg(Sprite.explosion_vertical_down_last1.getFxImage());
            if (block_up_bomb(bomb, powerBombUp))
                edge_up.setImg(Sprite.explosion_vertical_top_last1.getFxImage());
            if (block_left_bomb(bomb, powerBombLeft))
                edge_left.setImg(Sprite.explosion_horizontal_left_last1.getFxImage());
            if (block_right_bomb(bomb, powerBombRight))
                edge_right.setImg(Sprite.explosion_horizontal_right_last1.getFxImage());
            if (isMiddle) {
                for (Entity e : listBombMiddleH) {
                    e.setImg(Sprite.explosion_vertical1.getFxImage());
                }
                for (Entity e : listBombMiddleW) {
                    e.setImg(Sprite.explosion_horizontal1.getFxImage());
                }
            }
            swapExplosion = 3;

        } else if (swapExplosion == 3) {
            bomb.setImg(Sprite.bomb_exploded2.getFxImage());
            if (block_down_bomb(bomb, powerBombDown))
                edge_down.setImg(Sprite.explosion_vertical_down_last2.getFxImage());
            if (block_up_bomb(bomb, powerBombUp))
                edge_up.setImg(Sprite.explosion_vertical_top_last2.getFxImage());
            if (block_left_bomb(bomb, powerBombLeft))
                edge_left.setImg(Sprite.explosion_horizontal_left_last2.getFxImage());
            if (block_right_bomb(bomb, powerBombRight))
                edge_right.setImg(Sprite.explosion_horizontal_right_last2.getFxImage());
            if (isMiddle) {
                for (Entity e : listBombMiddleH) {
                    e.setImg(Sprite.explosion_vertical2.getFxImage());
                }
                for (Entity e : listBombMiddleW) {
                    e.setImg(Sprite.explosion_horizontal2.getFxImage());
                }
            }
            swapExplosion = 1;
        }
    }

    public static void createEdge() {
        if (block_down_bomb(bomb, 0)) {
            edge_down = new Bomb(bomb.getX() / 32, bomb.getY() / 32 + 1,
                    Sprite.bomb_exploded.getFxImage());
            if (powerBomb > 0)
                for (int i = 1; i <= powerBomb; i++)
                    if (block_down_bomb(bomb, i)) {
                        edge_down.setY(bomb.getY() + 32 + i * 32);
                        powerBombDown++;
                    } else break;
            fixedEntities.add(edge_down);
        }
        if (block_up_bomb(bomb, 0)) {
            edge_up = new Bomb(bomb.getX() / 32, bomb.getY() / 32 - 1,
                    Sprite.bomb_exploded.getFxImage());
            if (powerBomb > 0)
                for (int i = 1; i <= powerBomb; i++)
                    if (block_up_bomb(bomb, i)) {
                        edge_up.setY(bomb.getY() - 32 - i * 32);
                        powerBombUp++;
                    } else break;
            fixedEntities.add(edge_up);
        }
        if (block_left_bomb(bomb, 0)) {
            edge_left = new Bomb(bomb.getX() / 32 - 1, bomb.getY() / 32,
                    Sprite.bomb_exploded.getFxImage());
            if (powerBomb > 0)
                for (int i = 1; i <= powerBomb; i++)
                    if (block_left_bomb(bomb, i)) {
                        edge_left.setX(bomb.getX() - 32 - i * 32);
                        powerBombLeft++;
                    } else break;
            fixedEntities.add(edge_left);
        }
        if (block_right_bomb(bomb, 0)) {
            edge_right = new Bomb(bomb.getX() / 32 + 1, bomb.getY() / 32,
                    Sprite.bomb_exploded.getFxImage());
            if (powerBomb > 0)
                for (int i = 1; i <= powerBomb; i++)
                    if (block_right_bomb(bomb, i)) {
                        edge_right.setX(bomb.getX() + 32 + i * 32);
                        powerBombRight++;
                    } else break;
            fixedEntities.add(edge_right);
        }
    }

    public static void createMiddle() {
        Entity middle;
        for (int i = 1; i <= powerBombDown; i++) {
            middle = new Bomb(bomb.getX() / 32, bomb.getY() / 32 + i,
                    Sprite.bomb_exploded.getFxImage());
            listBombMiddleH.add(middle);
        }
        for (int i = 1; i <= powerBombUp; i++) {
            middle = new Bomb(bomb.getX() / 32, bomb.getY() / 32 - i,
                    Sprite.bomb_exploded.getFxImage());
            listBombMiddleH.add(middle);
        }
        for (int i = 1; i <= powerBombLeft; i++) {
            middle = new Bomb(bomb.getX() / 32 - i, bomb.getY() / 32,
                    Sprite.bomb_exploded.getFxImage());
            listBombMiddleW.add(middle);
        }
        for (int i = 1; i <= powerBombRight; i++) {
            middle = new Bomb(bomb.getX() / 32 + i, bomb.getY() / 32,
                    Sprite.bomb_exploded.getFxImage());
            listBombMiddleW.add(middle);
        }
        fixedEntities.addAll(listBombMiddleW);
        fixedEntities.addAll(listBombMiddleH);
    }

    private static void checkActive() {
        if (isBomb == 1) {
            if (System.currentTimeMillis() - timeBomb < 2000) {
                if (System.currentTimeMillis() - timeTmp > 100) {
                    activeBomb();
                    timeTmp += 100;
                }
            } else {
                isBomb = 2;
                timeBomb = System.currentTimeMillis();
                timeTmp = timeBomb;
            }
        }
    }
    public static void checkExplosion () {
        if (isBomb == 2)
            if (System.currentTimeMillis() - timeBomb < 1000) {
                if (System.currentTimeMillis() - timeTmp > 100) {
                    if (!isEdge) {
                        createEdge();
                        isEdge = true;
                    }
                    if (powerBomb > 0) {
                        if (!isMiddle) {
                            createMiddle();
                            isMiddle = true;
                        }
                    }
                    explosionCenter();
                    timeTmp += 100;
                }
            } else {
                isBomb = 0;
                idObjects[bomb.getX() / 32][bomb.getY() / 32] = ' ';
                dead_position[bomb.getX() / 32][bomb.getY() / 32] = ' ';
                if (block_down_bomb(bomb, powerBombDown)) {
                    idObjects[edge_down.getX() / 32][edge_down.getY() / 32] = ' ';
                    dead_position[edge_down.getX() / 32][edge_down.getY() / 32] = ' ';
                }
                if (block_up_bomb(bomb, powerBombUp)) {
                    idObjects[edge_up.getX() / 32][edge_up.getY() / 32] = ' ';
                    dead_position[edge_up.getX() / 32][edge_up.getY() / 32] = ' ';
                }
                if (block_left_bomb(bomb, powerBombLeft)) {
                    idObjects[edge_left.getX() / 32][edge_left.getY() / 32] = ' ';
                    dead_position[edge_left.getX() / 32][edge_left.getY() / 32] = ' ';
                }
                if (block_right_bomb(bomb, powerBombRight)) {
                    idObjects[edge_right.getX() / 32][edge_right.getY() / 32] = ' ';
                    dead_position[edge_right.getX() / 32][edge_right.getY() / 32] = ' ';
                }
                if (isMiddle) {
                    for (Entity e : listBombMiddleW) {
                        dead_position[e.getX() / 32][e.getY() / 32] = ' ';
                        idObjects[e.getX() / 32][e.getY() / 32] = ' ';
                    }
                    for (Entity e : listBombMiddleH) {
                        dead_position[e.getX() / 32][e.getY() / 32] = ' ';
                        idObjects[e.getX() / 32][e.getY() / 32] = ' ';
                    }
                }
                fixedEntities.removeAll(listBombMiddleH);
                fixedEntities.removeAll(listBombMiddleW);
                listBombMiddleH.clear();
                listBombMiddleW.clear();

                isEdge = false;
                isMiddle = false;
                powerBombDown = 0;
                powerBombUp = 0;
                powerBombLeft = 0;
                powerBombRight = 0;
            }
    }


    @Override
    public void update() {
        checkActive();
        checkExplosion();
    }
}