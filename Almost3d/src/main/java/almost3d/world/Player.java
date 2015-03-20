/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package almost3d.world;

/**
 *
 * @author santeriraisanen
 */
public class Player {
    
    private double xLoc;
    private double yLoc;
    private double theta;

    public Player() {
    }
    

    public double getTheta() {
        return theta;
    }

    public double getyLoc() {
        return yLoc;
    }

    public double getxLoc() {
        return xLoc;
    }
    
    public void setLocation(double x, double y, double theta) {
        this.theta=theta;
        this.xLoc=x;
        this.yLoc=y;
    }
    
    
     
}
