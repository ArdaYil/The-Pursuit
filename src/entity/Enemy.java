package entity;

import main.Game;
import util.Direction;
import util.Pathfinding;
import util.Pathfinding.Path;
import vector.Vector2D;

public class Enemy extends Entity {
    protected Vector2D walkingTo;
    protected boolean isWalking = false;

    public Enemy(Game game) {
        super(game);

        //Vector2D randomSpot = this.game.map.findRandomFreeSpot(100, 150, 126, 156);
        Vector2D randomSpot = new Vector2D(120 * 48, 129 * 48);

        this.position = randomSpot;
        this.setSpeed(1);
    }

    public static void manageEnemies(Game game) {
        new Slime(game);
    }
}
