package uet.oop.bomberman.level;

import uet.oop.bomberman.entities.dynamicEntities.*;
import uet.oop.bomberman.entities.staticEntities.Brick;
import uet.oop.bomberman.entities.staticEntities.Grass;
import uet.oop.bomberman.entities.staticEntities.Portal;
import uet.oop.bomberman.entities.staticEntities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.StringTokenizer;

import static uet.oop.bomberman.BombermanGame.*;

public class loadFile {
    public static void createMap(int level) {
        fixedEntities.clear();
        enemy.clear();
        final File levelx = new File("res/levels/Level" + level + ".txt");
        try (FileReader inputFile = new FileReader(levelx)) {
            Scanner sc = new Scanner(inputFile);
            String line = sc.nextLine();

            //  Lấy kích thước bkground
            StringTokenizer tokens = new StringTokenizer(line);
            _level = Integer.parseInt(tokens.nextToken());
            _height = Integer.parseInt(tokens.nextToken());
            _width = Integer.parseInt(tokens.nextToken());

            while (sc.hasNextLine()) {
                idObjects = new char[_width][_height];
                dead_position = new char[_width][_height];
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
                            case '3':
                                enemy.add(new Doll(j, i, Sprite.doll_left1.getFxImage()));
                                fixedEntities.add(new Grass(j, i, Sprite.grass.getFxImage()));
                                break;
                            case '4':
                                enemy.add(new Minvo(j, i, Sprite.minvo_left1.getFxImage()));
                                fixedEntities.add(new Grass(j, i, Sprite.grass.getFxImage()));
                                break;
                            case '5':
                                enemy.add( new Kondoria(j, i , Sprite.kondoria_left1.getFxImage()));
                                fixedEntities.add(new Grass(j, i, Sprite.grass.getFxImage()));
                                break;
                            case 'x':
                                portal = new Portal(j, i, Sprite.portal.getFxImage());
                                fixedEntities.add(new Brick(j, i, Sprite.brick.getFxImage()));
                                break;
                                /*
                                case 's':
                                    fixedEntities.add(new SpeedItem(j, i, Sprite.powerup_speed.getFxImage()));
                                    fixedEntities.add(new Brick(j, i, Sprite.brick.getFxImage()));
                                    break;
                                case 'b':
                                    fixedEntities.add(new SpeedItem(j, i, Sprite.powerup_bombs.getFxImage()));
                                    fixedEntities.add(new Brick(j, i, Sprite.brick.getFxImage()));
                                    break;
                                case 'f':
                                    fixedEntities.add(new SpeedItem(j, i, Sprite.powerup_flames.getFxImage()));
                                    fixedEntities.add(new Brick(j, i, Sprite.brick.getFxImage()));
                                    break;
                                */
                            default:
                                fixedEntities.add(new Grass(j, i, Sprite.grass.getFxImage()));
                                break;
                        }
                        idObjects[j][i] = lineTile.charAt(j);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
