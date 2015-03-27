/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almost3d.world;

import almost3d.game.Game;
import static java.lang.Math.*;
import java.util.ArrayList;

/**
 *
 * @author santeriraisanen
 */
public class Map {

    private int[][] map;
    private int dim_y;
    private int dim_x;
    private ArrayList<WorldObject> objects;
    private SkyBox SkyBox;
    private Game game;

    public Map(Game game) {
        this.objects = new ArrayList<>();
        this.game = game;
    }

    public Map() {                                         // for debugging purposes
        this.objects = new ArrayList<>();
    }

    public void load(String a) {                // for loading map from file, obviously not supported yet

    }

    public void load(int[][] map) {             // this is just for debugging purposes
        this.SkyBox = new SkyBox();
        this.dim_x = map.length;
        this.dim_y = map[0].length;
        this.map = map;
        this.objects.add(new NullObj());
        this.objects.add(new Wall());
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 1) {
                    this.objects.add(new Sprite(i + 0.5, j + 0.5, this.game));
                    map[i][j] = this.objects.size() - 1;
                }
            }
        }
    }

    public void load() {                        // as is this
        this.SkyBox = new SkyBox();
        this.dim_x = 11;
        this.dim_y = 11;
        this.map = new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 1 indicates a wall, 2 a sprite
        {0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0},
        {0, 0, 2, 0, 0, 1, 0, 2, 1, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
        {0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0},
        {0, 1, 1, 1, 0, 2, 1, 0, 1, 0, 0},
        {0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0},
        {0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0},
        {0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0},
        {0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        this.objects.add(new NullObj());
        this.objects.add(new Wall());
        for (int i = 0; i < dim_x; i++) {
            for (int j = 0; j < dim_y; j++) {
                if (map[i][j] > 1) {
                    this.objects.add(new Sprite(i + 0.5, j + 0.5, this.game));
                    map[i][j] = this.objects.size() - 1;
                }
            }
        }

    }

    public void updateMap(double X, double Y, double Radius, int objIndex) {

        int dx;
        int dy;
        for (Direction d : Direction.values()) {
            if (this.map[(int) (d.getX() + X)][(int) (d.getY() + Y)] == objIndex) {
                this.map[(int) (d.getX() + X)][(int) (d.getY() + Y)] = 0;
            }
            dx = (int) (d.getX() * Radius + X);
            dy = (int) (d.getY() * Radius + Y);
            this.map[dx][dy] = objIndex;
        }
    }

    public void update(double deltaTime) {
        for (WorldObject o : this.objects) {
            if (o.update(deltaTime) == 1) {
                double[] Loc = o.getLocation();
                this.updateMap(Loc[0], Loc[1], Loc[2], this.objects.indexOf(o));
            }
        }
    }

    public WorldObject checkObject(int X, int Y) {
        if (X < 0 || X >= this.dim_x || Y < 0 || Y >= this.dim_y) {
            return this.SkyBox;
        }
        return this.objects.get(this.map[X][Y]);

    }

    public boolean checkCollisions(double x, double y, double radius) {
        for (Direction d : Direction.values()) {
            double dx = d.getX() * radius + x;
            double dy = d.getY() * radius + y;
            if (checkObject((int) floor(dx), (int) floor(dy)).checkCollision(dx, dy)) {
                return true;
            }
        }
        return false;
    }

}
