package map;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Tile {
    public BufferedImage image;
    public boolean canCollide;

    public Tile(InputStream image, boolean canCollide) {

        this.canCollide = canCollide;

        try {
            this.image = ImageIO.read(image);
        }

        catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public static class TileInitializer {
        Tile[] tiles;

        public TileInitializer(Tile[] tiles) {
            this.tiles = tiles;

            this.tiles[0] = new Tile(getClass().getResourceAsStream("/tiles/Grass.png"), false);
            this.tiles[1] = new Tile(getClass().getResourceAsStream("/tiles/BigHouse1.png"), false);
            this.tiles[2] = new Tile(getClass().getResourceAsStream("/tiles/BigHouse10.png"), true);
            this.tiles[3] = new Tile(getClass().getResourceAsStream("/tiles/BigHouse11.png"), true);
            this.tiles[4] = new Tile(getClass().getResourceAsStream("/tiles/BigHouse12.png"), true);
            this.tiles[5] = new Tile(getClass().getResourceAsStream("/tiles/BigHouse13.png"), false);
            this.tiles[6] = new Tile(getClass().getResourceAsStream("/tiles/BigHouse14.png"), false);
            this.tiles[7] = new Tile(getClass().getResourceAsStream("/tiles/BigHouse15.png"), true);
            this.tiles[8] = new Tile(getClass().getResourceAsStream("/tiles/BigHouse16.png"), false);
            this.tiles[9] = new Tile(getClass().getResourceAsStream("/tiles/BigHouse2.png"), true);
            this.tiles[10] = new Tile(getClass().getResourceAsStream("/tiles/BigHouse3.png"), true);
            this.tiles[11] = new Tile(getClass().getResourceAsStream("/tiles/BigHouse4.png"), true);
            this.tiles[12] = new Tile(getClass().getResourceAsStream("/tiles/BigHouse5.png"), true);
            this.tiles[13] = new Tile(getClass().getResourceAsStream("/tiles/BigHouse6.png"), true);
            this.tiles[14] = new Tile(getClass().getResourceAsStream("/tiles/BigHouse7.png"), true);
            this.tiles[15] = new Tile(getClass().getResourceAsStream("/tiles/BigHouse8.png"), true);
            this.tiles[16] = new Tile(getClass().getResourceAsStream("/tiles/BigHouse9.png"), true);
            this.tiles[17] = new Tile(getClass().getResourceAsStream("/tiles/BigTree1.png"), false);
            this.tiles[18] = new Tile(getClass().getResourceAsStream("/tiles/BigTree2.png"), true);
            this.tiles[19] = new Tile(getClass().getResourceAsStream("/tiles/BigTree3.png"), false);
            this.tiles[20] = new Tile(getClass().getResourceAsStream("/tiles/BigTree4.png"), true);
            this.tiles[21] = new Tile(getClass().getResourceAsStream("/tiles/BigTree5.png"), true);
            this.tiles[22] = new Tile(getClass().getResourceAsStream("/tiles/BigTree6.png"), true);
            this.tiles[23] = new Tile(getClass().getResourceAsStream("/tiles/BigTree7.png"), true);
            this.tiles[24] = new Tile(getClass().getResourceAsStream("/tiles/BigTree8.png"), true);
            this.tiles[25] = new Tile(getClass().getResourceAsStream("/tiles/BigTree9.png"), false);
            this.tiles[26] = new Tile(getClass().getResourceAsStream("/tiles/BrickWall.png"), true);
            this.tiles[27] = new Tile(getClass().getResourceAsStream("/tiles/Dirt.png"), false);
            this.tiles[28] = new Tile(getClass().getResourceAsStream("/tiles/Fence1.png"), true);
            this.tiles[29] = new Tile(getClass().getResourceAsStream("/tiles/Fence2.png"), true);
            this.tiles[30] = new Tile(getClass().getResourceAsStream("/tiles/Fence3.png"), true);
            this.tiles[31] = new Tile(getClass().getResourceAsStream("/tiles/House1.png"), true);
            this.tiles[32] = new Tile(getClass().getResourceAsStream("/tiles/House2.png"), true);
            this.tiles[33] = new Tile(getClass().getResourceAsStream("/tiles/House3.png"), true);
            this.tiles[34] = new Tile(getClass().getResourceAsStream("/tiles/House4.png"), true);
            this.tiles[35] = new Tile(getClass().getResourceAsStream("/tiles/House5.png"), true);
            this.tiles[36] = new Tile(getClass().getResourceAsStream("/tiles/House6.png"), true);
            this.tiles[37] = new Tile(getClass().getResourceAsStream("/tiles/House7.png"), false);
            this.tiles[38] = new Tile(getClass().getResourceAsStream("/tiles/House8.png"), true);
            this.tiles[39] = new Tile(getClass().getResourceAsStream("/tiles/House9.png"), false);
            this.tiles[40] = new Tile(getClass().getResourceAsStream("/tiles/UnlockedDoor.png"), true);
            this.tiles[41] = new Tile(getClass().getResourceAsStream("/tiles/TreeTile.png"), true);
            this.tiles[42] = new Tile(getClass().getResourceAsStream("/tiles/LockedDoor.png"), true);
            this.tiles[43] = new Tile(getClass().getResourceAsStream("/tiles/Water1.png"), true);
            this.tiles[44] = new Tile(getClass().getResourceAsStream("/tiles/Water2.png"), true);
            this.tiles[45] = new Tile(getClass().getResourceAsStream("/tiles/Water3.png"), true);
            this.tiles[46] = new Tile(getClass().getResourceAsStream("/tiles/Water4.png"), true);
            this.tiles[47] = new Tile(getClass().getResourceAsStream("/tiles/Water5.png"), true);
            this.tiles[48] = new Tile(getClass().getResourceAsStream("/tiles/Water6.png"), true);
            this.tiles[49] = new Tile(getClass().getResourceAsStream("/tiles/Water7.png"), true);
            this.tiles[50] = new Tile(getClass().getResourceAsStream("/tiles/Water8.png"), true);
            this.tiles[51] = new Tile(getClass().getResourceAsStream("/tiles/Water9.png"), true);
            this.tiles[52] = new Tile(getClass().getResourceAsStream("/tiles/WoodFloor.png"), true);
        }
    }
}
