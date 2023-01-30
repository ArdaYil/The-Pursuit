package main;

import entity.MartMerchant;
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

        this.merchants.push(merchant);
    }

    public void draw(Graphics2D g2) {
        for (Object merchant : this.merchants.getArray()) {
            if (merchant == null) return;
            System.out.println(merchant);
            if (merchant instanceof MartMerchant) {
                ((MartMerchant)merchant).draw(g2);
            }
        }
    }
}
