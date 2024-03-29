package uet.oop.bomberman.graphics;

import uet.oop.bomberman.BombermanGame;

public class Coordinates {

    public static int pixelToTile(double i) {
        return (int)(i / BombermanGame.TILES_SIZE);
    }

    public static int tileToPixel(int i) {
        return i * BombermanGame.TILES_SIZE;
    }

    public static int tileToPixel(double i) {
        return (int)(i * BombermanGame.TILES_SIZE);
    }


}
