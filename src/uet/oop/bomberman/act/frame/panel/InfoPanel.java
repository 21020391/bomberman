package uet.oop.bomberman.act.frame.panel;

import uet.oop.bomberman.BombermanGame;

import javax.swing.*;
import java.awt.*;

/**
 * Swing Panel hiển thị thông tin thời gian, điểm mà người chơi đạt được
 */
public class InfoPanel extends JPanel {

    private JLabel timeLabel;
    private JLabel pointsLabel;
    private JLabel rateLabel;
    private JLabel maxPointsLabel;

    public InfoPanel() {
        setLayout(new GridLayout());

        timeLabel = new JLabel("Time: " + BombermanGame.getBoard().getTime());
        timeLabel.setForeground(Color.yellow);
        timeLabel.setHorizontalAlignment(JLabel.CENTER);

        pointsLabel = new JLabel("Points: " + BombermanGame.getBoard().getPoints());
        pointsLabel.setForeground(Color.yellow);
        pointsLabel.setHorizontalAlignment(JLabel.CENTER);

        maxPointsLabel = new JLabel("Max Points: " + BombermanGame.getBoard().getPoints());
        maxPointsLabel.setForeground(Color.yellow);
        maxPointsLabel.setHorizontalAlignment(JLabel.CENTER);

        rateLabel = new JLabel("Rate: " + BombermanGame.updates + " | FPS: " + BombermanGame.frames);
        rateLabel.setForeground(Color.yellow);
        rateLabel.setHorizontalAlignment(JLabel.CENTER);

        add(timeLabel);
        add(pointsLabel);
        add(rateLabel);
        add(maxPointsLabel);

        setBackground(Color.darkGray);
        setPreferredSize(new Dimension(0, 40));
    }

    public void setTime(int t) {
        timeLabel.setText("Time: " + t);
    }

    public void setPoints(int t) {
        pointsLabel.setText("Score: " + t);
    }

    public void setRate (int r, int f) {
        rateLabel.setText("Rate: " + r + "   |   FPS: " + f);
    }

    public void setMaxPoints(int t) {
        maxPointsLabel.setText("Max Score: " + t);
    }

}
