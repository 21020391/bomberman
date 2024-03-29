package uet.oop.bomberman;

import uet.oop.bomberman.act.frame.MenuStage;
import uet.oop.bomberman.level.Screen;
import uet.oop.bomberman.act.frame.Frame;
import uet.oop.bomberman.act.event.KeyBoard;
import uet.oop.bomberman.sound.Audio;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;

/**
 * Tạo vòng lặp cho game, lưu trữ một vài tham số cấu hình toàn cục,
 * Gọi phương thức render(), update() cho tất cả các entity
 */
public class BombermanGame extends Canvas {

    public static final int TILES_SIZE = 16,
            WIDTH = TILES_SIZE * 31,
            HEIGHT = 13 * TILES_SIZE ;

    public static int SCALE = 2;

    public static final String TITLE = " Welcome to BombermanGame";
    public static int frames = 0;
    public static int updates = 0;

    //Bomb Rate: số lượng bom có thể đặt liên tiếp tại thời điểm hiện tại
    private static final int BOMBRATE = 1;
    private static final int BOMBRADIUS = 1;
    private static final double BOMBERSPEED = 1.0;

    public static final int TIME = 200;
    public static final int POINTS = 0;

    protected static int SCREENDELAY = 3;

    protected static int bombRate = BOMBRATE;
    protected static int bombRadius = BOMBRADIUS;
    protected static double bomberSpeed = BOMBERSPEED;


    protected int _screenDelay = SCREENDELAY;

    private KeyBoard _input;
    private boolean _paused = true;

    //TODO: thêm âm thanh nền của Game
    private static Audio backgoundSound;
    public static Audio getBackgoundSound() {
        return backgoundSound;
    }

    //TODO: thêm static
    private static Board _board;
    private Screen screen;
    private Frame _frame;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
    private static int _screenToShow = -1; //1:endgame, 2:changelevel, 3:paused
    protected MenuStage menu = new MenuStage();

    /**
     * khoi tao phuong thuc co tham so kieu Frame
     * BombermanGame se goi den cac doi tuong Frame, Screen, KeyBoard, Board, Audio
     */
    public BombermanGame(Frame frame) {
        _frame = frame;

        // create game icon
        Image icon = Toolkit.getDefaultToolkit().getImage("res/font/icon.png");
        _frame.setIconImage(icon);

        //set title
        _frame.setTitle(TITLE);

        screen = new Screen();
        _input = new KeyBoard();

        _board = new Board(this, _input, screen);
        addKeyListener(_input);
        backgoundSound = new Audio(new File("res/Sound/stage_start.wav"));
    }


    private void renderGame() {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(3); // tao bo dem 3
            return;
        }

        screen.clear();

        _board.render(screen);

        System.arraycopy(screen._pixels, 0, pixels, 0, pixels.length);

        Graphics g = bs.getDrawGraphics();

        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

        g.dispose();
        bs.show();
    }

    private void renderScreen() {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();

        Graphics g = bs.getDrawGraphics();

        drawScreen(g);

        g.dispose();
        bs.show();
    }

    private void update() {
        _input.update();
        _board.update();
    }

    public void start() {
        boolean _running = true;

        long  lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0; //nanosecond, 60 frames per second
        double delta = 0;
        requestFocus();

        System.out.println("START !");
        backgoundSound.play();

        while(_running) {


            //khi load level
            if(_paused) {
                if(_screenDelay <= 0) {
                    _screenToShow = -1;
                    _paused = false;
                }
                renderScreen();
            } else {
                renderGame();
            }

            //handle
            long now = System.nanoTime();
            delta += (now - lastTime) / ns; //frame fom lastTime to now ~60 frame per second
            lastTime = now;
            while(delta >= 1) {
                update();
                updates++;
                delta--;

            }

            frames++;
            if(System.currentTimeMillis() - timer > 1000) {
                _frame.setTime(_board.subtractTime());
                _frame.setPoints(_board.getPoints());
                _frame.setMaxPointS(_board.get_maxPoint());
                _frame.setRate(updates, frames);

                timer += 1000;
                _frame.setTitle(TITLE);
                updates = 0;
                frames = 0;

                if(_screenToShow == 2)
                    --_screenDelay;

                //khi end game
                if(_screenToShow == 1) {
                    --_screenDelay;
                    _board.afterEndGame();
                }

            }
        }

    }

    public void drawScreen(Graphics g) {

        switch (_screenToShow) {
            case 1:
                menu.drawEndGame(g, _board.getPoints());
                break;
            case 2:
                menu.drawChangeLevel(g, _board.get_levelLoader().getLevel());
                break;
            case 3:
                menu.drawPaused(g);
                break;
        }
    }
    public static double getBomberSpeed() {
        return bomberSpeed;
    }

    public static int getBombRate() {
        return bombRate;
    }

    public static int getBombRadius() {
        return bombRadius;
    }

    public static void addBomberSpeed(double i) {
        bomberSpeed += i;
    }

    public static void addBombRadius(int i) {
        bombRadius += i;
    }

    public static void addBombRate(int i) {
        bombRate += i;
    }

    public void resetScreenDelay() {
        _screenDelay = SCREENDELAY;
    }

    public void resetBomberSpeed() {
        bomberSpeed = BOMBERSPEED;
    }

    public void resetBombRate() {
        bombRate = BOMBRATE;
    }

    public void resetBombRadius() {
        bombRadius = BOMBRADIUS;
    }

    //TODO: Them static
    public static Board getBoard() {
        return _board;
    }

    public boolean isPaused() {
        return _paused;
    }

    public void pause() {
        _paused = true;
    }

    public static void set_screenToShow(int screenToShow) {
        _screenToShow = screenToShow;
    }

    public static void main(String[] args) {
        new Frame();
        //new Menu();
    }


}
