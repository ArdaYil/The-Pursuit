package entity;

import main.Game;
import util.Array;
import vector.Vector2D;

public class NPC extends Entity{
    public boolean canCollide;
    private static Array npcArray = new Array(100);

    public NPC(Game game) {
        super(game);

        this.canCollide = true;
    }

    public static void insertNPC(NPC npc) {
        npcArray.push(npc);
    }

    public static Object[] getNPCArray() {
        return npcArray.getArray();
    }

    public static NPC getNPCFromCoordinates(Vector2D position) {
        for (Object npc : getNPCArray()) {
            if (!(npc instanceof NPC)) return null;
            NPC castedNPC = (NPC) npc;

            int col = castedNPC.position.getX() / Game.tileSize;
            int row = castedNPC.position.getY() / Game.tileSize;

            if (position.getX() == col && position.getY() == row) return castedNPC;
        }

        return null;
    }
}
