package map;

import javax.imageio.ImageIO;
import java.io.*;
import java.awt.Graphics2D;

import entity.Player;
import main.Game;

public class Map {
    private static int renderBoundary = 60;
    private Tile[] tiles = new Tile[10];
    private int[][] map;
    private Game game;

    public Map(Game game) {
        this.game = game;
        this.initializeTiles();
        this.map = new int[this.game.worldCols][this.game.worldRows];
        this.initializeMap("/maps/Test.txt");
    }

    public void initializeMap(String filePath) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line = reader.readLine();
            int row = 0;

            while (line != null) {
                String[] tileNumbers = line.split(" ");

                for (int col = 0; col < tileNumbers.length; col++) {
                    this.map[col][row] = Integer.parseInt(tileNumbers[col]);
                }

                line = reader.readLine();
                row++;
            }
        }

        catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public void initializeTiles() {
        try {
            this.tiles[0] = new Tile();
            this.tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/GrassTile.png"));

            this.tiles[1] = new Tile();
            this.tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/TreeTile.png"));
        }

        catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int tileSize = this.game.tileSize;

        for (int row = 0; row < this.game.worldRows; row++) {
            for (int col = 0; col < this.game.worldCols; col++) {
                int number = this.map[col][row];

                Tile tile = this.tiles[number];

                Player player = this.game.player;
                int worldX = col * tileSize;
                int worldY = row * tileSize;

                int drawX = worldX - player.getX() + player.getScreenX();
                int drawY = worldY - player.getY() + player.getScreenY();

                int xLeft = player.getScreenX() - this.game.screenWidth/2 - renderBoundary;
                int xRight = player.getScreenX() + this.game.screenWidth/2 + renderBoundary;
                int yTop = player.getScreenY() - this.game.screenHeight/2 - renderBoundary;
                int yBottom = player.getScreenY() + this.game.screenHeight/2 + renderBoundary;

                if (drawX < xLeft || drawX > xRight || drawY <  yTop || drawY > yBottom) continue;

                g2.drawImage(tile.image, drawX, drawY, tileSize, tileSize, null);
            }
        }
    }
}
