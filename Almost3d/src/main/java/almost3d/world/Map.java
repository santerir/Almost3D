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
 * The map class keeps track of the locations of all game objects.
 * <p>
 * This class can load a map from file, or use a default map in absence of one.
 * It keeps track of the locations of moving objects (sprites) and walls. It can
 * also call for each object to update it's state and check weather objects
 * collide with each other.
 * 
 * 
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
                    this.objects.add(new Sprite(i + 0.5, j + 0.5, this.game,this.objects.size()));
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
                    this.objects.add(new Sprite(i + 0.5, j + 0.5, this.game,this.objects.size()));
                    map[i][j] = this.objects.size() - 1;
                }
            }
        }

    }
    
    public WorldObject getObject(int objId) {
        return this.objects.get(objId);
    }

    public void updateMap(WorldObject obj) {        //move object's map marker
        double[] prevLoc = obj.getPrevLocation();
        double[] loc = obj.getLocation();
                
        int dx;
        int dy;
        for (Direction d : Direction.values()) {         
            dx = (int) (d.getX() * prevLoc[2] + prevLoc[0]);
            dy = (int) (d.getY() * prevLoc[2] + prevLoc[1]);
            this.map[dx][dy] = 0;
            dx = (int) (d.getX() * loc[2] + loc[0]);
            dy = (int) (d.getY() * loc[2] + loc[1]);
            this.map[dx][dy] = obj.getId();
        }
    }

    public void update(double deltaTime) {
        for (WorldObject o : this.objects) {
            if (o.update(deltaTime) == 1) {
                this.updateMap(o);
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
