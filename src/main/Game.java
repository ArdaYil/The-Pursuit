package main;

import javax.swing.JPanel;
import java.awt.*;

import entity.Enemy;
import entity.Entity;
import entity.Player;
import entity.Slime;
import map.Map;
import vector.Vector2D;

public class Game extends JPanel implements Runnable{
    private static final int originalTileSize = 16;
    private static final int tileScale = 3;

    public static int tileSize = originalTileSize * tileScale;
    private static final int columns = 20;
    private static final int rows = 15;

    public static final int screenWidth = columns * tileSize;
    public static final int screenHeight = rows * tileSize;

    public static final int worldCols = 30;
    public static final int worldRows = 20;

    public static final int baseFPS = 60;
    public static int FPS = 60;

    public boolean gamePaused = false;

    public MartManager martManager = new MartManager(this);
    public Map map;
    public Thread gameThread;
    public Player player;
    public KeyboardInput keyInput = new KeyboardInput();
    public GameMenu menu = new GameMenu(keyInput, this);

    public Game() {
        //tileSize = 16;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.setBackground(new Color(0, 0, 0));
        this.map = new Map(this);
        this.addKeyListener(keyInput);
        this.setFocusable(true);

        Enemy.manageEnemies(this);

        this.martManager.createMerchant(new Vector2D(31, 21));

        player = new Player(this, keyInput);
    }

    public void startGame() {
        this.gameThread = new Thread(this);
        gameThread.start();
    }

    public void execute() {
        if (!this.gamePaused) {
            this.update();
        };

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
                System.out.println("FPS: " + fpsCounter);
                t = System.nanoTime();
                fpsCounter = 0;
            }
        }
    }

    public void update() {
        this.player.update();
        Slime.updateSlimes();
        //System.out.println(Thread.activeCount());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        this.map.draw(g2);
        this.martManager.draw(g2);
        this.player.draw(g2);
        Slime.drawSlimes(g2);
        this.menu.draw(g2);

        g2.dispose();
    }
}
