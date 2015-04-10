/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package almost3d.world;

import java.awt.image.BufferedImage;

/**
 * This the abstract class of all objects in the game world<
 * <p>
 * All objects in the game world, be they visible or not, extend this class. Some
 * of the parameters are not relevant for all game objects. Visible objects must
 * have a texture defined. Non permeable objects stop rays, ie nothing behind them
 * will be rendered. Collisions and distances are calculated by the extending classes
 * as the way they are calculated depends on the object geometry. So far only the Sprite
 * objects keep track of their own location. The update function is called every frame, and
 * the WorldObject implementations can define their own behaviors on update events.
 * 
 * 
 * 
 * 
 * 
 *
 * @author santeriraisanen
 */
public abstract class WorldObject {

    BufferedImage texture;
    boolean permeable;
    boolean physical;
    boolean visible;
    int objectId;
    
    public boolean isPermeable() {
        return this.permeable;
    }
    
    public boolean isPhysical() {
        return this.physical;
    }
    
    public boolean isVisible() {
        return this.visible;
    }
    
    public int getId() {
        return this.objectId;
    }
    
    /**
     *
     * @return location on map
     */
    public abstract double[] getLocation();
    
    /**
     *
     * @return location in previous frame
     */
    public abstract double[] getPrevLocation();
    
    public abstract void setLocation(double X,double Y);
    
    public abstract double checkDistance(double X, double Y, double theta);
    
   

    /**
     * Returns texture column which is hit by cast from location (X,Y) in direction
     * theta.
     *
     * @param X
     * @param Y
     * @param theta
     * @return column of pixels from texture
     */
        public abstract BufferedImage getTextureColumn(double X, double Y, double theta);
    
    //  Checks if point (X,Y) is within objects collider
    public abstract boolean checkCollision(double X, double Y);
    
    /**
     * Defines object behavior between frame renders.
     * <p>
     * The deltaTime parameter is passed by the render loop, and tells how much
     * time has passed since last update. This allows for smooth movement, regardless
     * of the fact that processing time may vary between frames.
     * 
     * @param deltaTime is time elapsed since last update in ns.
     * @return 0 for no action taken, 1 otherwise
     */
    public abstract int update(double deltaTime);
}
