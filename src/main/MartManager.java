package main;

import entity.MartMerchant;
import vector.Vector2D;

import java.awt.*;
import java.util.Arrays;

public class MartManager {
    public Game game;
    public MartMerchant[] merchants = new MartMerchant[10];

    public MartManager(Game game) {
        this.game = game;
    }

    public void createMerchant(Vector2D position) {
        int tileSize = this.game.tileSize;

        MartMerchant merchant = new MartMerchant(game);
        merchant.position.setX(position.getX() * tileSize);
        merchant.position.setY(position.getY() * tileSize);

        this.merchants[this.merchants.length] = merchant;
    }

    public void draw(Graphics2D g2) {
        System.out.println(Arrays.toString(this.merchants));
        for (MartMerchant merchant : this.merchants) {
            if (merchant == null) return;

            merchant.draw(g2);
        }
    }
}
