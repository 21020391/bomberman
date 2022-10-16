package uet.oop.bomberman.entities;

public abstract class AnimateEntity extends Entity {
    /**
     * Entity co hieu ung hoat hinh.
     */
    private final static int Max_Animate = 7500;
    private int _animate = 0;

    protected void animate() {
        if (_animate < Max_Animate)
            _animate++;
        else _animate = 0;
    }
}
