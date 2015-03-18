/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package almost3d.almost3d;
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
    private ArrayList<worldObject> objects;
    private SkyBox SkyBox;
    
    public Map() {
        this.objects = new ArrayList<>();
    }
    
    public boolean load(String a) {
        return false;
    }
    
    public boolean load() {
        this.SkyBox = new SkyBox();
        this.dim_x=5;
        this.dim_y=5;
        this.map = new int[][]{{0,0,1,1,1},{0,0,0,0,0},{1,0,0,0,0},{1,0,0,0,0},{1,0,0,0,0}};
        System.out.println(this.map.toString());
        
        this.objects.add(new NullObj());
        this.objects.add(new Wall());
        
        
        return true;
               
    }
    
    public worldObject checkObject(int X, int Y) {
        if(X < 0 || X>=this.dim_x || Y < 0 || Y>=this.dim_y) {
            return this.SkyBox;
        }
        return this.objects.get(this.map[X][Y]);
        
    }
    
    public boolean checkCollisions(double x, double y, double radius) {
        for(Direction d:Direction.values()) {
            if (checkObject((int) ceil(d.getX()*radius+x),(int) ceil(d.getY()*radius+x)).checkCollision(x, y)!=-1) {
                return true;
            }
        }
        return false;
    }

    public int getDimX() {
        return this.dim_x;
    }

    public int getDimY() {
        return this.dim_y;
    }
    
    
}
