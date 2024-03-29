package uet.oop.bomberman.level;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.dynamicEntities.enemy.*;
import uet.oop.bomberman.entities.items.BombItem;
import uet.oop.bomberman.entities.items.FlameItem;
import uet.oop.bomberman.entities.items.SpeedItem;
import uet.oop.bomberman.entities.LayeredEntity;
import uet.oop.bomberman.entities.dynamicEntities.Bomber;
import uet.oop.bomberman.entities.staticEntities.tile.Grass;
import uet.oop.bomberman.entities.staticEntities.tile.Portal;
import uet.oop.bomberman.entities.staticEntities.tile.Wall;
import uet.oop.bomberman.entities.staticEntities.tile.brick.Brick;
import uet.oop.bomberman.graphics.Coordinates;
import uet.oop.bomberman.graphics.Sprite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.StringTokenizer;

public class loadFile extends LevelLoader {

    /**
     * Ma trận chứa thông tin bản đồ, mỗi phần tử lưu giá trị kí tự đọc được
     * từ ma trận bản đồ trong tệp cấu hình
     */
    private static final String [][] _map = new String[50][50];

    public loadFile(Board board, int level) {
        super(board, level);
    }

    @Override
    public void loadLevel(int level) {
        // TODO: đọc dữ liệu từ tệp cấu hình /levels/Level{level}.txt
        // TODO: cập nhật các giá trị đọc được vào _width, _height, _level, _map
        try {
            URL absPath = loadFile.class.getResource("/Levels/Level" + level + ".txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(absPath.openStream()));

            String data = in.readLine();
            StringTokenizer tokens = new StringTokenizer(data);
            set_level(Integer.parseInt(tokens.nextToken()));
            set_height(Integer.parseInt(tokens.nextToken()));
            set_width(Integer.parseInt(tokens.nextToken()));

            for(int i=0; i<getHeight(); i++) {
                String line = in.readLine();
                for (int j = 0; j < getWidth(); j++) {
                    _map[i][j] = line.substring(j, j+1);
                }
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createEntities() {
        // TODO: tạo các Entity của màn chơi
        // TODO: sau khi tạo xong, gọi _board.addEntity() để thêm Entity vào game

        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                addLevelEntity(_map[i][j].charAt(0), j, i);
            }
        }
    }

    public void addLevelEntity(char c, int x, int y){
        int pos = x + y * _width;

        switch(c) {
            //them Wall
            case '#':
                _board.addEntity(pos, new Wall(x, y, Sprite.wall));
                break;

            //them Bomber
            case 'p':
                _board.addCharacter(new Bomber(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + BombermanGame.TILES_SIZE, _board));
                _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                break;

            //them enermy
            case '1':
                _board.addCharacter( new Ballom(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + BombermanGame.TILES_SIZE, _board));
                _board.addEntity(pos, new Grass(x, y, Sprite.grass) );
                break;
            case '2':
                _board.addCharacter( new Oneal(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + BombermanGame.TILES_SIZE, _board));
                _board.addEntity(pos, new Grass(x, y, Sprite.grass) );
                break;
            case '3':
                _board.addCharacter( new Doll(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + BombermanGame.TILES_SIZE, _board));
                _board.addEntity(pos, new Grass(x, y, Sprite.grass) );
                break;
            case '4':
                _board.addCharacter( new Minvo(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + BombermanGame.TILES_SIZE, _board));
                _board.addEntity(pos, new Grass(x, y, Sprite.grass) );
                break;
            case '5':
                _board.addCharacter( new Kondoria(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + BombermanGame.TILES_SIZE, _board));
                _board.addEntity(pos, new Grass(x, y, Sprite.grass) );
                break;
            case '6': {
                _board.addCharacter(new Pontan(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + BombermanGame.TILES_SIZE, _board));
                _board.addEntity(pos, new Grass(x, y, Sprite.grass) );
                break;
            }
            //them brick
            case '*':
                _board.addEntity(pos, new LayeredEntity(x, y, new Grass(x, y, Sprite.grass), new Brick(x, y, Sprite.brick)));
                break;

            //them portal
            case 'x':
                _board.addEntity(pos, new LayeredEntity(x, y, new Portal(x, y, Sprite.portal), new Brick(x, y, Sprite.brick)));
                break;

            // thêm Item kèm Brick che phủ ở trên
            case 's':
                _board.addEntity(pos, new LayeredEntity(x, y, new Grass(x, y, Sprite.grass), new SpeedItem(x, y, Sprite.powerup_speed),
                                new Brick(x, y, Sprite.brick)));
                break;
            case 'b':
                _board.addEntity(x + y * _width, new LayeredEntity(x, y, new Grass(x, y, Sprite.grass),
                                new BombItem(x, y, Sprite.powerup_bombs),new Brick(x, y, Sprite.brick) ));
                break;
            case 'f':
                _board.addEntity(pos, new LayeredEntity(x, y, new Grass(x, y, Sprite.grass), new FlameItem(x, y, Sprite.powerup_flames),
                                new Brick(x, y, Sprite.brick) ));
                break;
            default:
                _board.addEntity(pos, new Grass(x, y, Sprite.grass) );
                break;
        }
    }

}
