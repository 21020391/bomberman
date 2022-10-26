package uet.oop.bomberman.entities.items;

import uet.oop.bomberman.entities.staticEntities.Tile;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Items extends Tile {

    public Items(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

}
