package main;

/*
    Denna fil är en klass för spelet. Spel objektet är en singelton som delas globalt bland alla objekt som använder det.
    Spel objektet innehåller all väsentlig information som spelet bör ha som exemeplvis tile storlek, antal tiles i skärmvyn
    Skärmstorleken, själva renderingen och uppdatering av alla objekt.
*/

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
    public static final int columns = 20;
    public static final int rows = 15;

    public static final int screenWidth = columns * tileSize;
    public static final int screenHeight = rows * tileSize;

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

    // Här skapas speltråden, vilket kommer göra det möjligt att stoppa spel koden utan att hela programmet stoppas

    public void startGame() {
        this.gameThread = new Thread(this);
        gameThread.start();
    }

    public void execute() {
        // Denna metod updaterar all speldata såsom positioner och sedan renderar spelet beroende på speldata.

        if (!this.gamePaused) {
            this.update();
        };

        // Genom att anropa repaint som finns på alla jkomponenter kommer detta objekts paintComponent metod att
        // köras med ett grafik objekt som parameter utan att jag behöver förse metoden med det som ett argument

        this.repaint();
    }


    // Run metoden körs så fort en tråd skapas som tar in detta objekt som argument, givet att objektet implementerar det
    // Funktionella gränssnitten runable

    @Override
    public void run() {
        long currentTime = System.nanoTime();
        long fpsCounter = 0;
        long t = System.nanoTime();

        while (gameThread != null) {
            // Genom att kolla nanosekunds skillnaden mellan varje loop kan vi se när en fps dels sekund har passerat
            // Då uppdaterar vi all speldata och renderar spelet

            if (System.nanoTime() - currentTime >= Math.pow(10, 9)/FPS) {
                fpsCounter += 1;
                currentTime = System.nanoTime();
                this.execute();
            }

            // Denna del av koden har ingen betydelse för spelet utan printar bara FPSen varje sekund.

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

    // Denna metod kommer från JComponent och ger mig ett grafik objekt som parameter.

    @Override
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
