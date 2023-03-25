package entity;

/*
    Denna fil är en klass för slimes. Denna klass inehåller viktig information som vägen slimes gå, vitiga objektvariabler
    såsom position och metoder såsom updatera bilden osv.
*/

import main.Game;
import util.Array;
import util.Direction;
import util.LinkedList;
import util.Pathfinding;
import vector.Vector2D;
import util.Pathfinding.Path;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Slime extends Enemy {
    public boolean canCollide;
    public static BufferedImage[] slimeImages = new BufferedImage[3];
    private static LinkedList<Slime> slimes = new LinkedList<>();
    private Pathfinding.Path currentPath = null;
    private Vector2D currentGoal = null;
    private boolean isSearching = false;

    public Slime(Game game) {
        super(game);

        this.canCollide = true;
        this.initializeSlimeImages();

        slimes.addLast(this);
    }

    private void updateImage() {
        this.counter++;

        if (this.counter < 10) return;

        this.counter = 0;
        this.imageCount++;

        if (this.imageCount < 3) return;

        this.imageCount = 0;
    }

    private void walk() {
        if (this.isWalking) return;

        if ((this.currentPath == null || !this.currentPath.hasNext()) && isSearching == false) {
            // Är använder jag ett lambda uttryck eftersom att trådar tar in ett funktionellt gränssnitt som argument
            // Gränssnittet kräver ett objekt som implementerar gränssnittets metod run. Med ett lambda uttryck
            // Kommer detta att ske automatiskt av java, vilket leder till renare och mer läsbar kod.

            new Thread(() -> {
                isSearching = true;

                while (this.currentPath == null) {
                    Vector2D goal = this.game.map.findRandomFreeSpot(100, 150, 126, 156);

                    Pathfinding pathfinding = new Pathfinding(this.game, this.position, goal);
                    Path path = pathfinding.computePath();

                    this.currentPath = path;
                }

                isSearching = false;
            }).start();
        }

        if (currentPath != null && currentPath.hasNext()) {
            Vector2D next = currentPath.getNext();

            Direction direction = this.game.map.getDirection(this.position, next);

            this.setDirection(direction);
            this.walkingTo = next;
            this.isWalking = true;
            this.currentGoal = next;
        }

        else currentPath = null;
    }

    private void update() {
        this.updateImage();

        this.walk();

        if (!this.isWalking) return;

        if (this.getDirection() == Direction.UP || this.getDirection() == Direction.DOWN) {
            this.walkY();
        }

        else {
            //System.out.println(this.position);
            this.walkX();
        }

        if (this.getDirection() == Direction.UP && this.position.getY() <= currentGoal.getY()) this.isWalking = false;
        if (this.getDirection() == Direction.DOWN && this.position.getY() >= currentGoal.getY()) this.isWalking = false;
        if (this.getDirection() == Direction.LEFT && this.position.getX() <= currentGoal.getX()) this.isWalking = false;
        if (this.getDirection() == Direction.RIGHT && this.position.getX() >= currentGoal.getX()) this.isWalking = false;
    }

    private void draw(Graphics2D g2) {
        int worldX = this.getX();
        int worldY = this.getY();

        Vector2D drawPosition = this.game.map.getDrawingPosition(new Vector2D(worldX, worldY), this.game.player);
        g2.drawImage(slimeImages[this.imageCount], drawPosition.getX(), drawPosition.getY(), Game.tileSize, Game.tileSize, null);
    }

    public static void drawSlimes(Graphics2D g2) {
        for (Slime slime : slimes) {
            slime.draw(g2);
        }
    }

    public static void updateSlimes() {
        for (Slime slime : slimes) {
            slime.update();
        }
    }

    private void initializeSlimeImages() {
        try {
            slimeImages[0] = ImageIO.read(getClass().getResourceAsStream("/greenSlime/SlimeIdle1.png"));
            slimeImages[1] = ImageIO.read(getClass().getResourceAsStream("/greenSlime/SlimeIdle2.png"));
            slimeImages[2] = ImageIO.read(getClass().getResourceAsStream("/greenSlime/SlimeIdle3.png"));
        }

        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
