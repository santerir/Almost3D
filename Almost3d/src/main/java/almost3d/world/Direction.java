/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package almost3d.world;

/**
 * This enum lists direction in the game world.
 * 
 *
 * @author santeriraisanen
 */
public enum Direction {
    N(0,1), S(0,-1), E(1,0), W(-1,0), NW(-1,1), NE(1,1), SW(-1,-1), SE(1,-1), O(0,0);
    
    private final double x;
    private final double y;

    private Direction(double x,double y) {
        this.x=x;
        this.y=y;

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    
}
