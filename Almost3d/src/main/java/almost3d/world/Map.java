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
    
    public Map(Game game) {
        this.objects = new ArrayList<>();
    }
    
     public Map() {                                         // for debugging purposes
        this.objects = new ArrayList<>();
    }
    
    public void load(String a) {
       
    }
    
    public void load(int[][] map) {             // this is just for debugging purposes
        this.SkyBox = new SkyBox();
        this.dim_x=map.length;
        this.dim_y=map[0].length;
        this.map = map;
        this.objects.add(new NullObj());
        this.objects.add(new Wall());
    }
    
    public void load() {                        // as is this
        this.SkyBox = new SkyBox();
        this.dim_x=11;
        this.dim_y=11;
        this.map = new int[][]{{0,0,0,0,0,0,0,0,0,0,0},
                               {0,0,0,0,0,1,1,1,1,0,0},
                               {0,0,0,0,0,1,0,0,1,0,0},
                               {0,0,0,0,0,0,0,0,1,0,0},
                               {0,0,0,1,0,1,1,0,1,0,0},
                               {0,1,1,1,0,0,1,0,1,0,0},
                               {0,1,0,0,0,0,1,0,1,0,0},
                               {0,1,0,1,1,1,1,0,1,0,0},
                               {0,1,0,0,0,0,0,0,1,0,0},
                               {0,1,1,1,0,1,1,1,1,0,0},
                               {0,0,0,0,0,0,0,0,0,0,0}};
        this.objects.add(new NullObj());
        this.objects.add(new Wall());
        
        
    
               
    }
    
    public WorldObject checkObject(int X, int Y) {
        if(X < 0 || X>=this.dim_x || Y < 0 || Y>=this.dim_y) {
            return this.SkyBox;
        }
        return this.objects.get(this.map[X][Y]);
        
    }
    
    public boolean checkCollisions(double x, double y, double radius) {
        for(Direction d:Direction.values()) {
            double dx = d.getX()*radius+x;
            double dy = d.getY()*radius+y;
            if (checkObject((int) floor(dx),(int) floor(dy)).checkCollision(dx, dy)!=-1) {
                return true;
            }
        }
        return false;
    }
    
}
