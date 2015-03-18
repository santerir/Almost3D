/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package almost3d.almost3d;

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
    
    
    public abstract int checkCollision(double X, double Y);
}
