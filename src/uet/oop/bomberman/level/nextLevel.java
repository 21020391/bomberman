package uet.oop.bomberman.level;

import static uet.oop.bomberman.BombermanGame._level;
public class nextLevel {
    public static boolean wait;
    public static long waitingTime;

    public static void waitToLevelUp() {
        if (wait) {
            long now = System.currentTimeMillis();
            if (now - waitingTime > 3000) {
                loadFile.createMap(_level + 1);
                wait = false;
            }
        }
    }
}
