package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import uet.oop.bomberman.act.move;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.dynamic.Bomber;
import uet.oop.bomberman.entities.dynamic.dynamics;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BombermanGame extends Application {
    
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    public static int _width = 0;
    public static int _height = 0;
    public static int _level = 1;

    public static boolean running;

    // Lưu c sau khi đọc file
    public static char[][] toObjects;

    public static dynamics bomberman;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        scene.setOnKeyPressed(event -> {
            if (true)
                switch (event.getCode()) {
                    case UP:
                        move.up(bomberman);
                        break;
                    case DOWN:
                        move.down(bomberman);
                        break;
                    case LEFT:
                        move.left(bomberman);
                        break;
                    case RIGHT:
                        move.right(bomberman);
                        break;
                    case SPACE:
                        ;
                        break;
                }
        });

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        createMap();

        bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);
    }

    public void createMap() {
        final File level1 = new File("res/levels/Level1.txt");
        try (FileReader inputFile = new FileReader(level1)) {
            Scanner sc = new Scanner(inputFile);
            String line = sc.nextLine();

            //  Lấy kích thước bkground
            StringTokenizer tokens = new StringTokenizer(line);
            _level = Integer.parseInt(tokens.nextToken());
            _height = Integer.parseInt(tokens.nextToken());
            _width = Integer.parseInt(tokens.nextToken());

            while (sc.hasNextLine()) {
                toObjects = new char[_width][_height];
                for (int i = 0; i < _height; i++) {
                    String lineTile = sc.nextLine();
                    StringTokenizer tokenTile = new StringTokenizer(lineTile);

                    for (int j = 0; j < _width; j++) {
                        //red: StringIndexOutOfBoundsException
                        char c = lineTile.charAt(j);

                        Entity object;

                        switch (c) {
                            case '#':
                                object = new Wall(j, i, Sprite.wall.getFxImage());
                                break;
                            case '*':
                                object = new Brick(j, i, Sprite.brick.getFxImage());
                                break;
                            case'x':
                                object = new Portal(j, i, Sprite.portal.getFxImage());
                                c = ' ';
                                break;
                            case 'p':
                                object = new Bomber(j, i, Sprite.player_right.getFxImage());
                                break;
                            case '1':
                                object = new Ballom(j, i, Sprite.balloom_right1.getFxImage());
                                break;
                            case '2':
                                object = new Oneal(j, i, Sprite.oneal_right1.getFxImage());
                                break;
                            case 'b':
                                object = new Bomb(j, i, Sprite.bomb.getFxImage());
                                break;
                            default:
                                object = new Grass(j, i, Sprite.grass.getFxImage());
                        }
                        toObjects[j][i] = c;
                        stillObjects.add(object);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
