package main;

import javax.swing.JPanel;
import java.awt.*;

import entity.Player;

public class Game extends JPanel implements Runnable{
    private static final int originalTileSize = 16;
    private static final int tileScale = 3;

    private static final int tileSize = originalTileSize * tileScale;
    private static final int columns = 20;
    private static final int rows = 15;

    private static final int screenWidth = columns * tileSize;
    private static final int screenHeight = rows * tileSize;

    private static final int FPS = 60;

    Thread gameThread;
    Player player;

    public Game() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.setBackground(new Color(0, 0, 0));

        player = new Player();
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

    }
}
