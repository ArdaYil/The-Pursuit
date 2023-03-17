package entity;

import main.Game;
import util.Array;
import util.LinkedList;

public class Slime extends Entity {
    public boolean canCollide;
    private LinkedList<Slime> slimes = new LinkedList<>();

    public Slime(Game game) {
        super(game);

        this.canCollide = true;

        slimes.addLast(this);
    }

    public void draw() {

    }

    public static void drawAllSlimes() {

    }
}
