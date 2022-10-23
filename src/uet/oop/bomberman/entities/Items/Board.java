package uet.oop.bomberman.entities.Items;

import com.sun.glass.ui.Pixels;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.staticEntities.Grass;

public class Board {
    private Entity[] _entities;

    public Pixels getLevel() {
        return null;
  }


    public void addEntity(int pos, Entity e)
    {
        _entities[pos] = e;
    }
}
