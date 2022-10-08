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
import uet.oop.bomberman.entities.dynamicEntities.Ballom;
import uet.oop.bomberman.entities.dynamicEntities.Bomber;
import uet.oop.bomberman.entities.dynamicEntities.Oneal;
import uet.oop.bomberman.entities.dynamicEntities.dynamics;
import uet.oop.bomberman.entities.staticEntities.Brick;
import uet.oop.bomberman.entities.staticEntities.Grass;
import uet.oop.bomberman.entities.staticEntities.Portal;
import uet.oop.bomberman.entities.staticEntities.Wall;
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

    public static dynamics bomberman;

    public static Bomb bomb;
    public static Portal portal;
    public static boolean running = true;
    private GraphicsContext gc;
    private Canvas canvas;
    public static List<Entity> fixedEntities = new ArrayList<>();

    // mang chua cac enemy
    public static List<dynamics> enemy = new ArrayList<>();

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        canvas.requestFocus();
        canvas.setFocusTraversable(true);

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        scene.setOnKeyPressed(event -> {
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
                    bomb = new Bomb(bomberman.getX() / Sprite.SCALED_SIZE,
                                        bomberman.getY() / Sprite.SCALED_SIZE, Sprite.bomb.getFxImage());
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
    }

    public void createMap() {
        fixedEntities.clear();
        enemy.clear();
        canvas.setDisable(true);
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
                for (int i = 0; i < _height; i++) {
                    String lineTile = sc.nextLine();

                    for (int j = 0; j < _width; j++) {
                        //red: StringIndexOutOfBoundsException
                        switch (lineTile.charAt(j)) {
                            case '#':
                                fixedEntities.add(new Wall(j, i, Sprite.wall.getFxImage()));
                                break;
                            case '*':
                                fixedEntities.add(new Brick(j, i, Sprite.brick.getFxImage()));
                                break;
                            case 'p':
                                bomberman = new Bomber(j, i , Sprite.player_right.getFxImage());
                                fixedEntities.add(new Grass(j, i ,Sprite.grass.getFxImage()));
                                break;
                            case '1':
                                enemy.add(new Ballom(j, i, Sprite.balloom_left1.getFxImage()));
                                fixedEntities.add(new Grass(j, i ,Sprite.grass.getFxImage()));
                                break;
                            case '2':
                                enemy.add(new Oneal(j, i, Sprite.oneal_left1.getFxImage()));
                                fixedEntities.add(new Grass(j, i ,Sprite.grass.getFxImage()));
                                break;
                            case 'x':
                                portal = new Portal(j, i, Sprite.portal.getFxImage());
                                fixedEntities.add(new Brick(j, i, Sprite.brick.getFxImage()));
                                break;
                            default:
                                fixedEntities.add(new Grass(j, i, Sprite.grass.getFxImage()));
                                break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isCanMove_up(dynamics object) {
        for (Entity e : fixedEntities) {
            if (e instanceof Grass) {
                if (e.getX() == object.getX() && e.getY() == object.getY() - 32) {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean isCanMove_down(dynamics object) {
        for (Entity e : fixedEntities) {
            if (e instanceof Grass) {
                if (e.getX() == object.getX() && e.getY() == object.getY() + 32) {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean isCanMove_left(dynamics object) {
        for (Entity e : fixedEntities) {
            if (e instanceof Grass) {
                if (e.getX() == object.getX() - 32 && e.getY() == object.getY()) {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean isCanMove_right(dynamics object) {
        for (Entity e : fixedEntities) {
            if (e instanceof Grass) {
                if (e.getX() == object.getX() + 32 && e.getY() == object.getY()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void update() {
        fixedEntities.forEach(Entity::update);
        enemy.forEach(Entity::update);
        bomberman.update();
        bomberman.setCountToRun(bomberman.getCountToRun() + 1);
        if (bomberman.getCountToRun() == 4) {
            move.isRunning(bomberman);
            bomberman.setCountToRun(0);
        }
        for (dynamics a : enemy) {
            a.setCountToRun(a.getCountToRun() + 1);
            if (a.getCountToRun() == 8) {
                move.isRunning(a);
                a.setCountToRun(0);
            }
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        portal.render(gc);
        fixedEntities.forEach(g -> g.render(gc));
        enemy.forEach(g -> g.render(gc));
        bomberman.render(gc);
        if (bomb != null) {
            bomb.render(gc);
        }
    }
}
