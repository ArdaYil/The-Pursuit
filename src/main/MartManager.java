package main;

/*
    Denna fil är en klass för som hanterar marten i spelet dvs afärerna. MartManager är en singleton dvs det finns endast
    en instans av denna klass som används av hela applikationen. MartManager objektet bästämmer när och hur merchants ska
    skapas, vart de ska placeras och hålla referenser till alla merchants
*/


import entity.MartMerchant;
import entity.NPC;
import vector.Vector2D;
import util.Array;

import java.awt.*;
import java.util.Arrays;

public class MartManager {
    public Game game;
    public Array merchants = new Array(10);

    public MartManager(Game game) {
        this.game = game;
    }

    public void createMerchant(Vector2D position) {
        int tileSize = this.game.tileSize;

        MartMerchant merchant = new MartMerchant(game);
        merchant.position.setX(position.getX() * tileSize);
        merchant.position.setY(position.getY() * tileSize);

        NPC.insertNPC(merchant);

        this.merchants.push(merchant);
    }

    public void draw(Graphics2D g2) {
        for (Object merchant : this.merchants.getArray()) {
            if (merchant == null) return;

            if (merchant instanceof MartMerchant) {
                ((MartMerchant)merchant).draw(g2);
            }
        }
    }
}
