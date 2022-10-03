package uet.oop.bomberman.entities;

public class AbstractEnemy {
    private final static int SIZE = 30;
    private int x;
    private int y;
    private int Step;

    protected AbstractEnemy(int x, int y, int Step) {
        this.x = x;
        this.y = y;
    }
}