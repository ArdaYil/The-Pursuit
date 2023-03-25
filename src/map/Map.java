package map;

/*
    Denna fil är en klass för mappen. Mappen är en singleton. Detta objekt är ansvarig för att hålla referenser till
    alla tiles, alla siffror för mappen i en 2D array, samt inläsning av textfiler till 2D array, map composition.
    Slutligen är detta objekt ansvarig över att rendera skärmen.

    Mappen är en 2D fil med siffror där varje siffra är kopplad till en bild så att programmet vet vilken bild som
    bör visas på en viss del av skärmen. Textfilen har samma radlängd som antal rader vilket sedan görrs om till ett 2D fält
    av programmet

    Just nu används endast en stor textfil men map objektet kan sätta ihop flera textfiler till en 2D array. Hur mapen ska
    komponeras besrkivs i en 2D array som heter map composition.
*/


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

import entity.Player;
import main.Game;
import util.Direction;
import util.LinkedList;
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
            "/Maps/map.txt",
    };

    private static int[][] mapComposition = {
            {0}
    };

    public Map(Game game) {
        this.game = game;

        new Tile.TileInitializer(this.tiles);

        Vector2D baseDimensions = this.getBaseDimensions();
        this.baseCols = baseDimensions.getX();
        this.baseRows = baseDimensions.getY();
        this.worldRows = this.baseRows * mapComposition.length;
        this.worldCols = this.baseCols * mapComposition[0].length;
        this.map = new int[this.worldCols][this.worldRows];
        this.initializeMap();
    }

    public Vector2D getBaseDimensions() {
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

        Vector2D baseDimensions = new Vector2D();
        baseDimensions.setX(cols);
        baseDimensions.setY(rows);

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

    public boolean isObstacle(Vector2D position) {
        int number = this.map[position.getX()][position.getY()];
        Tile tile = this.tiles[number];

        return tile.canCollide;
    }

    public Vector2D getCellFromCoordinate(Vector2D coordinate) {
        int tileSize = this.game.tileSize;

        return new Vector2D(coordinate.getX()/tileSize, coordinate.getY()/tileSize);
    }

    public Direction getDirection(Vector2D current, Vector2D target) {
        boolean isHorizontal = current.getY() == target.getY();

        if (isHorizontal) {
            if (current.getX() < target.getX()) return Direction.RIGHT;
            return Direction.LEFT;
        }

        if (current.getY() < target.getY()) return Direction.DOWN;

        return Direction.UP;
    }

    public Vector2D findRandomFreeSpot(int leftX, int rightX, int bottomY, int topY) {
        LinkedList<Vector2D> occupiedSpots = this.getAllOccupiedSpots(leftX, rightX, bottomY, topY);

        if (occupiedSpots.size() == (rightX - leftX) * (bottomY - topY))
            throw new IllegalArgumentException("There is no free spot for the given boundaries");

        while (true) {
            int randomX = MathUtil.random(leftX, rightX);
            int randomY = MathUtil.random(bottomY, topY);

            Vector2D newPosition = new Vector2D(randomX, randomY);

            if (!occupiedSpots.has(newPosition)) return newPosition;
        }
    }

    private LinkedList<Vector2D> getAllOccupiedSpots(int leftX, int rightX, int topY, int bottomY) {
        LinkedList<Vector2D> list = new LinkedList<>();

        for (int row = topY; row < bottomY; row++) {
            for (int col = leftX; col < rightX; col++) {
                int number = this.map[col][row];

                Tile tile = this.tiles[number];

                if (tile.canCollide == false) continue;

                list.addLast(new Vector2D(col, row));
            }
        }

        return list;
    }

    /*
        Mappen är betydligt större en det som får plats på skärmen
        sättet jag renderar mappen på är genom att ta rutans nummer exempelvis (4, 5) dvs kolumn 4 rad 5 och sedan ta det
        multiplicerad med rutstorleken som i detta fall är 48px detta ger oss den "logiska positionen" av rutan. Det jag sedan
        gör är att kolla distansen mellan rutans logiska position och spelarens logiska position + spelarens offset för
        att beräkna från spelarens mitt och inte det vänstra hörnet högst upp på spelaren. Distansen är differensen mellan
        spelarens logiska position + offset - rutans logiska position. Detta värde är pixel kordinaten för antingen x eller
        y värdet för rutan. Jag ritar sedan rutan på den x och y kordinat jag får. Rutans logiska position antal alltid samma värde
        spelarens logiska position däremot ändras beroende på vart spelaren befinner sig i världen. Om en ruta har den logiska
        positionen 100, 100 och spelaren har den logiska positionen 120, 120. Då ska ju rutan ritas 20 pixlar över spelaren
        och 20 pixlar till vänster om spelaren.
        Om rutan ritas utanför skärmens gränser så kommer renderingen för den rutan att skippas för att spelet ska vara mer optimerad
        Annars kommer det finnas bilder i heapen som ej används.

        För att klargöra: logisk position är positionen i spelvärlden och fysisk position är positionen i mappen eller
        kolumn och rad i textfilen för mappen.
    */

    public void draw(Graphics2D g2) {
        int tileSize = this.game.tileSize;

        Player player = this.game.player;

        int rows = this.game.rows/2;
        int cols = this.game.columns/2;

        int startRow = player.getY()/48 - rows;
        int endRow = startRow + this.game.rows;
        int startCol = player.getX()/48 - cols;
        int endCol = startCol + this.game.columns;

        int loops = 0;

        for (int row = startRow; row < endRow + 1; row++) {
            for (int col = startCol; col < endCol + 2; col++) {
                int number = this.map[col][row];

                Tile tile = this.tiles[number];

                int worldX = col * tileSize;
                int worldY = row * tileSize;

                Vector2D drawPosition = this.getDrawingPosition(new Vector2D(worldX, worldY), this.game.player);

                //if (!this.checkBoundaries(drawPosition, this.game.player)) continue;

                g2.drawImage(tile.image, drawPosition.getX(), drawPosition.getY(), tileSize, tileSize, null);
            }
        }
    }
}
