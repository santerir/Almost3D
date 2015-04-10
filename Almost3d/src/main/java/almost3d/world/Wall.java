/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package almost3d.world;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The wall object represents a wall.
 * <p>
 * This objects does not have a location, but rather the map objects keeps track
 * of wall locations, and passes reference to a single instance of this when
 * necessary to indicate that wall exists. This means that all walls in the game
 * are identical save for their locations.
 * 
 *
 * @author santeriraisanen
 */
public class Wall extends WorldObject {
    
    public Wall() {
        this.permeable = false;
        this.physical = true;
        this.visible = true;
        this.objectId=1;
        ClassLoader cl = this.getClass().getClassLoader();
        try {
        this.texture = ImageIO.read(cl.getResource("wall.png"));
        }
        
        catch (IOException e) {
        }
        
    }
    
    @Override
   public BufferedImage getTextureColumn(double X, double Y, double theta) { 
        int n;
        if (X%1==0) {
            n =  (int) (Y%1*100);
        }
        else if (Y%1==0) {
            n =  (int) (X%1*100);
        }
        else {n=1;}
        return this.texture.getSubimage(n,0,1,600);
    } 

    @Override
    public boolean checkCollision(double X, double Y) {
            return true;
    }
    
    @Override
    public String toString() {
        return "WALL";
    }

    @Override
    public double checkDistance(double X, double Y, double theta) {
        return 0;
    }
    
    @Override
    public void setLocation(double X,double Y) {
    }

    @Override
    public int update(double deltaTime) {
        return 0;
    }

    @Override
    public double[] getLocation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double[] getPrevLocation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
