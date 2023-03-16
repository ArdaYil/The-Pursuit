package entity;

import main.Game;

public class Slime extends Entity {
    public boolean canCollide;

    public Slime(Game game) {
        super(game);

        this.canCollide = true;
    }

    public void draw() {

    }
}
