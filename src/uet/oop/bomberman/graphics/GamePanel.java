package uet.oop.bomberman.graphics;

import uet.oop.bomberman.BombermanGame;

import javax.swing.*;
import java.awt.*;

/**
 * Swing Panel chứa cảnh game
 */
public class GamePanel extends JPanel {

    private BombermanGame _game;

    public GamePanel(Frame frame) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(BombermanGame.WIDTH * BombermanGame.SCALE, BombermanGame.HEIGHT * BombermanGame.SCALE));

        _game = new BombermanGame(frame);

        add(_game);

        _game.setVisible(true);

        setVisible(true);
        setFocusable(true);

    }

    public BombermanGame getGame() {
        return _game;
    }

}
