package uet.oop.bomberman.act;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.io.InputStream;

import static uet.oop.bomberman.BombermanGame.*;

public class Menu {
    private Pane menuPane;
    private Scene menuScene;
    private Stage menuStage;
    private Button startButton;

    private static ImageView statusGame;

    public Menu() {
        menuPane = new Pane();
        menuScene = new Scene(menuPane, WIDTH * Sprite.SCALED_SIZE, HEIGHT * Sprite.SCALED_SIZE);
        menuStage = new Stage();
        menuStage.setScene(menuScene);
        Image background = new Image("res/font/hi.png");
        BackgroundImage backMenu = new BackgroundImage(background, null, null, null, null);
        menuPane.setBackground(new Background(backMenu));
        startButton = createButton("res/font/start2.png", 400, 300, "7fd5f7");
        menuPane.getChildren().add(startButton);
    }

    public static Button createButton(String path, int x, int y, String backgroundColor) {
        InputStream inputStream = BombermanGame.class.getResourceAsStream(path);
        javafx.scene.image.Image image = new Image(inputStream);
        ImageView imageView = new ImageView(image);
        Button button = new Button("", imageView);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setStyle("-fx-background-color: #" + backgroundColor + "; ");
        return button;
    }

    public Stage getMenuStage() {
        return menuStage;
    }

    public Button getStartButton() {
        return startButton;
    }

    public Scene getMenuScene() {
        return menuScene;
    }
}