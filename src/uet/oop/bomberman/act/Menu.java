package uet.oop.bomberman.act;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
public class Menu {
    public static Text level, bomberman, time;
    public static int bombNumber = 20, timeNumber = 120;

    public static void createMenu(Group root) {
        level = new Text("Level: 1");
        level.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        level.setFill(Color.WHITE);
        level.setX(416);
        level.setY(20);
        bomberman = new Text("Bombs: 20");
        bomberman.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        bomberman.setFill(Color.WHITE);
        bomberman.setX(512);
        bomberman.setY(20);
        time = new Text("Times: 120");
        time.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        time.setFill(Color.WHITE);
        time.setX(608);
        time.setY(20);
        Pane pane = new Pane();
        pane.getChildren().addAll(level, bomberman, time);
        pane.setMinSize(800, 32);
        pane.setMaxSize(800, 480);

        root.getChildren().add(pane);
        updateMenu();
    }

    public static void updateMenu() {
        level.setText("Level: " + 1);
        bomberman.setText("Bombs: " + bombNumber);
    }
}
