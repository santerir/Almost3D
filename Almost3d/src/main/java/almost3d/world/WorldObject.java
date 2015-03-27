/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package almost3d.world;

import java.awt.image.BufferedImage;

/**
 *
 * @author santeriraisanen
 */
public abstract class WorldObject {

    BufferedImage texture;
    boolean permeable;
    boolean physical;
    boolean visible;
    
    public boolean isPermeable() {
        return this.permeable;
    }
    
    public boolean isPhysical() {
        return this.physical;
    }
    
    public boolean isVisible() {
        return this.visible;
    }
    
    public abstract double[] getLocation();
    
    
    public abstract double checkDistance(double X, double Y, double theta);
    
    // Returns the pixel column of the texture, which is hit by the ray from X,Y with angle theta
    public abstract BufferedImage getTextureColumn(double X, double Y, double theta);
    
    //  Checks if point (X,Y) is within objects collider
    public abstract boolean checkCollision(double X, double Y);
    
    public abstract int update(double deltaTime);
}
