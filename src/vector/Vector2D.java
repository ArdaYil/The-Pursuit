package vector;

/*
Denna fil är för vector 2d objekt dvs vectorer som håller data för en x och y axel. Ett vector 3d objekt skulle isåfall hålla
data för xyz axlarna. Vector 2d objekt kan typ inte används för rotation (inte hastighet häller i mitt spel då man inte kan
röra sig mellan två axlar sammtidigt), så här används vector 2d objeken endast för att ange position.
 */

public class Vector2D extends Vector {
    public Vector2D() {

    }

    public Vector2D(int x, int y) {
        super(x, y);
    }
}
