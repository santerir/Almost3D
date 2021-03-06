
package almost3d.graphics;

import almost3d.game.Game;
import almost3d.world.Map;
import almost3d.world.WorldObject;
import static java.lang.Math.*;

/**
 * This class creates Ray -objects.
 * <p>
 * The class goes through all points in the map array which the ray passes through,
 * checking whether the point contains a game object. If no object is located at the point,
 * it adds the NullObject to the ray. If the ray hits a non-permeable WorldObject 
 * (ie a wall or the skybox), it will stop checking any further.
 *
 * @author santeriraisanen
 */
public class Raycaster {

    private final Map map;

    public Raycaster(Game game) {
        this.map = game.map;
    }

    public Raycaster(Map map) {
        this.map = map;
    }

    public Ray cast(double x, double y, double theta) {
        Ray ray = new Ray();
        double x_comp = Math.cos(theta);
        double y_comp = Math.sin(theta);
        double step[] = new double[]{x, y};

        boolean cont = true;

        while (cont) {
            step = nextStep(step, x_comp, y_comp);
            int coord_x = (int) floor(step[0] + x_comp * 0.00001);
            int coord_y = (int) floor(step[1] + y_comp * 0.00001);

            WorldObject hit = this.map.checkObject(coord_x, coord_y);

            if (!hit.isPhysical()) {
                continue;
            }
            if (!hit.isVisible()) {
                ray.addHit(hit, Math.sqrt((step[0] - x) * (step[0] - x) + (step[1] - y) * (step[1] - y)), null);
            } else {
                double distance = 0;
                if (hit.toString().equals("WALL")) {
                    distance = Math.sqrt((step[0] - x) * (step[0] - x) + (step[1] - y) * (step[1] - y));
                }
                ray.addHit(hit, distance + hit.checkDistance(x, y, theta), hit.getTextureColumn(x, y, theta));
            }
            if (!hit.isPermeable()) {
                cont = false;
            }
        }
        return ray;
    }

    public double[] nextStep(double[] step, double x_comp, double y_comp) {
        if (x_comp == 0) {
            double[] ret = new double[2];
            ret[0] = step[0];
            ret[1] = y_comp > 0 ? floor(step[1] + 1) : ceil(step[1] - 1);
            return ret;
        }
        if (y_comp == 0) {
            double[] ret = new double[2];
            ret[1] = step[1];
            ret[0] = x_comp > 0 ? floor(step[0] + 1) : ceil(step[0] - 1);
            return ret;
        }
        double delta_x = x_comp > 0 ? floor(step[0] + 1) - step[0] : ceil(step[0] - 1) - step[0];
        double delta_y = y_comp > 0 ? floor(step[1] + 1) - step[1] : ceil(step[1] - 1) - step[1];

        if (delta_x / x_comp < delta_y / y_comp) {
            double[] ret = new double[2];
            ret[0] = step[0] + delta_x;
            ret[1] = step[1] + ((delta_x / x_comp) * y_comp);
            return ret;
        }

        double[] ret = new double[2];
        ret[1] = step[1] + delta_y;
        ret[0] = step[0] + ((delta_y / y_comp) * x_comp);
        return ret;

    }

}
