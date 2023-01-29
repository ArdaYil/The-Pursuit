package map;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Dimension2D;
import java.io.*;

import entity.Player;
import main.Game;

public class Map {
    private static int renderBoundary = 60;
    public Tile[] tiles = new Tile[10];
    public int[][] map;
    private int baseRows;
    private int baseCols;
    private int worldRows;
    private int worldCols;
    private Game game;

    private static String[] mapFiles = {
            "/Maps/Acrine.txt",
    };

    private static int[][] mapComposition = {
            {0}
    };

    public Map(Game game) {
        this.game = game;
        this.initializeTiles();

        Dimension baseDimensions = this.getBaseDimensions();
        this.baseCols = baseDimensions.width;
        this.baseRows = baseDimensions.height;
        this.worldRows = this.baseRows * mapComposition.length;
        this.worldCols = this.baseCols * mapComposition[0].length;
        this.map = new int[this.worldCols][this.worldRows];
        this.initializeMap();
    }

    public Dimension getBaseDimensions() {
        int rows = 0;
        int cols = 0;

        try {
            String path = mapFiles[0];

            InputStream inputStream = getClass().getResourceAsStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line = reader.readLine();
            String[] columns = line.split(" ");

            cols += columns.length;

            while (line != null) {
                rows++;

                line = reader.readLine();
            }

            reader.close();
        }

        catch(IOException ex) {
            ex.printStackTrace();
        }

        Dimension baseDimensions = new Dimension();
        baseDimensions.width = cols;
        baseDimensions.height = rows;

        return baseDimensions;
    }

    public void initializeMap() {
        try {
            for (int rowIndex = 0; rowIndex < mapComposition.length; rowIndex++) {
                int[] compositionRow = mapComposition[rowIndex];

                for (int colIndex = 0; colIndex < compositionRow.length; colIndex++) {
                    String filePath = mapFiles[mapComposition[rowIndex][colIndex]];

                    Dimension dimensions = this.initializeChunk(filePath, colIndex * baseCols, rowIndex * baseRows);
                }
            }
        }

        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Dimension initializeChunk(String filePath, int colMax, int rowMax) throws IOException {
        System.out.println("RowMax: " + rowMax);
        System.out.println("ColMax: " + colMax);
        InputStream inputStream = getClass().getResourceAsStream(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line = reader.readLine();
        int row = rowMax;
        int rowHolder = 0;
        int colHolder = 0;

        while (line != null) {
            String[] tileNumbers = line.split(" ");

            for (int col = 0; col < tileNumbers.length; col++) {
                this.map[colMax + col][row] = Integer.parseInt(tileNumbers[col]);
                colHolder = colMax + col;
            }

            line = reader.readLine();
            row++;
        }

        reader.close();

        Dimension dimension = new Dimension();
        dimension.width = colHolder;
        dimension.height = rowHolder;

        return dimension;
    }

    public void initializeTiles() {
        try {
            this.tiles[0] = new Tile();
            this.tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/GrassTile.png"));
            this.tiles[0].canCollide = false;

            this.tiles[1] = new Tile();
            this.tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/TreeTile.png"));
            this.tiles[1].canCollide = true;
        }

        catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int tileSize = this.game.tileSize;
        System.out.println(this.worldCols);
        System.out.println(this.worldRows);
        for (int row = 0; row < this.worldRows; row++) {
            for (int col = 0; col < this.worldCols; col++) {
                int number = this.map[col][row];

                Tile tile = this.tiles[number];

                Player player = this.game.player;
                int worldX = col * tileSize;
                int worldY = row * tileSize;

                int drawX = worldX - player.getX() + player.screenX;
                int drawY = worldY - player.getY() + player.screenY;

                int xLeft = player.screenX - this.game.screenWidth/2 - renderBoundary;
                int xRight = player.screenX + this.game.screenWidth/2 + renderBoundary;
                int yTop = player.screenY - this.game.screenHeight/2 - renderBoundary;
                int yBottom = player.screenY + this.game.screenHeight/2 + renderBoundary;

                if (drawX < xLeft || drawX > xRight || drawY <  yTop || drawY > yBottom) continue;

                g2.drawImage(tile.image, drawX, drawY, tileSize, tileSize, null);
            }
        }
    }
}
