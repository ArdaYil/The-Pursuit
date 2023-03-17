package map;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

import entity.Player;
import main.Game;
import vector.Vector2D;

public class Map {
    private static int renderBoundary = 60;
    public Tile[] tiles = new Tile[53];
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

        new Tile.TileInitializer(this.tiles);

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

    public Vector2D getDrawingPosition(Vector2D worldPosition, Player player) {
        int drawX = worldPosition.getX() - player.position.getX() + player.screenX;
        int drawY = worldPosition.getY() - player.position.getY() + player.screenY;

        return new Vector2D(drawX, drawY);
    }

    public boolean checkBoundaries(Vector2D drawPosition, Player player) {
        int xLeft = player.screenX - this.game.screenWidth/2 - renderBoundary;
        int xRight = player.screenX + this.game.screenWidth/2 + renderBoundary;
        int yTop = player.screenY - this.game.screenHeight/2 - renderBoundary;
        int yBottom = player.screenY + this.game.screenHeight/2 + renderBoundary;
        int x = drawPosition.getX();
        int y = drawPosition.getY();

        if (x < xLeft || x > xRight || y <  yTop || y > yBottom) return false;

        return true;
    }

    private Vector2D findRandomFreeSpot() {
        for (int row = 0; row < this.worldRows; row++) {
            for (int col = 0; col < this.worldCols; col++) {
                int number = this.map[col][row];

                Tile tile = this.tiles[number];
            }
        }
    }

    private Vector2D findRandomFreeSpot(int rightX, int leftX, int lowerY, int upperY) {

    }

    public void draw(Graphics2D g2) {
        int tileSize = this.game.tileSize;

        for (int row = 0; row < this.worldRows; row++) {
            for (int col = 0; col < this.worldCols; col++) {
                int number = this.map[col][row];

                Tile tile = this.tiles[number];

                Player player = this.game.player;
                int worldX = col * tileSize;
                int worldY = row * tileSize;

                Vector2D drawPosition = this.getDrawingPosition(new Vector2D(worldX, worldY), this.game.player);

                if (!this.checkBoundaries(drawPosition, this.game.player)) continue;

                g2.drawImage(tile.image, drawPosition.getX(), drawPosition.getY(), tileSize, tileSize, null);
            }
        }
    }
}
