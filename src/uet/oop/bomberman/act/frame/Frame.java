package uet.oop.bomberman.act.frame;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.act.frame.panel.GamePanel;
import uet.oop.bomberman.act.frame.panel.InfoPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Swing Frame chứa toàn bộ các component
 */
public class Frame extends JFrame {

    public GamePanel _gamepane;
    private InfoPanel _infopanel;

    public Frame() {

        JPanel _containerpane = new JPanel(new BorderLayout());
        _gamepane = new GamePanel(this);
        _infopanel = new InfoPanel();

        _containerpane.add(_infopanel, BorderLayout.PAGE_START);
        _containerpane.add(_gamepane, BorderLayout.PAGE_END);

        BombermanGame _game = _gamepane.getGame();

        add(_containerpane);

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        _game.start();
    }

    public void setTime(int time) {
        _infopanel.setTime(time);
    }

    public void setPoints(int points) {
        _infopanel.setPoints(points);
    }

    public void setRate (int updates, int frames) {
        _infopanel.setRate(updates, frames);
    }

    public void setMaxPointS(int points) {
        _infopanel.setMaxPoints(points);
    }

}
