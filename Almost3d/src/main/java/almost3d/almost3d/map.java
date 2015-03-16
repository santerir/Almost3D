/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package almost3d.almost3d;

/**
 *
 * @author santeriraisanen
 */
public class map {

    private boolean[][] map;
    private int dim_y;
    private int dim_x;
    
    public map() {
        
    }
    
    public int load(String a) {
        return 0;
    }
    
    public boolean checkWall(int X, int Y) {
        if(X<0 && X>this.dim_x) {
            return false;
        }
        return this.map[X][Y];
    }

    public int getDimX() {
        return this.dim_x;
    }

    public int getDimY() {
        return this.dim_y;
    }
    
    
}
