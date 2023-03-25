
/*
    Denna fil är en superklass för alla fiender.
    Eftersom att det kan finnas olika typer av fiender så måste dessa finder ha någon basklass så att de kan dela
    funktionalitet. Sedan kan varje enemy klass ha egna implementationer dvs polymorphism.
    Denna klass har en statisk metod för att hantera fiender.
*/

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

        Vector2D randomSpot = this.game.map.findRandomFreeSpot(110, 140, 126, 156);
        randomSpot.multiply(this.game.tileSize);

        this.position = randomSpot;
        this.setSpeed(1);
    }

    public static void manageEnemies(Game game) {
        new Slime(game);
        new Slime(game);
        new Slime(game);
        new Slime(game);
        new Slime(game);
        new Slime(game);
        new Slime(game);
        new Slime(game);
        new Slime(game);
        new Slime(game);
        new Slime(game);
        new Slime(game);
        new Slime(game);
        new Slime(game);
        new Slime(game);
    }
}
