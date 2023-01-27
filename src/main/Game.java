package main;

import javax.swing.JPanel;
import java.awt.*;

import entity.Player;
import map.Map;

public class Game extends JPanel implements Runnable{
    private static final int originalTileSize = 16;
    private static final int tileScale = 3;

    public static final int tileSize = originalTileSize * tileScale;
    private static final int columns = 20;
    private static final int rows = 15;

    public static final int screenWidth = columns * tileSize;
    public static final int screenHeight = rows * tileSize;

    public static final int worldCols = 30;
    public static final int worldRows = 20;

    private static final int FPS = 60;

    public Map map;
    public Thread gameThread;
    public Player player;
    public KeyboardInput keyInput = new KeyboardInput();

    public Game() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.setBackground(new Color(0, 0, 0));
        this.map = new Map(this);
        this.addKeyListener(keyInput);
        this.setFocusable(true);

        player = new Player(keyInput);
    }

    public void startGame() {
        this.gameThread = new Thread(this);
        gameThread.start();
    }

    public void execute() {
        this.update();
        this.repaint();
    }

    @Override
    public void run() {
        int delta = 0;
        long currentTime = System.nanoTime();
        long fpsCounter = 0;
        long t = System.nanoTime();

        while (gameThread != null) {
            if (System.nanoTime() - currentTime >= Math.pow(10, 9)/FPS) {
                fpsCounter += 1;
                currentTime = System.nanoTime();
                this.execute();
            }

            if (System.nanoTime() - t >= Math.pow(10, 9)) {
                System.out.println(fpsCounter);
                t = System.nanoTime();
                fpsCounter = 0;
            }
        }
    }

    public void update() {
        this.player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        this.map.draw(g2);
        this.player.draw(g2);

        g2.dispose();
    }
}
