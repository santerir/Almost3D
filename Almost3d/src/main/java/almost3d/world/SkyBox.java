/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package almost3d.world;

import java.awt.image.BufferedImage;

/**
 * The SkyBox is a world object which delimits the edges of the map.
 * 
 *
 * @author santeriraisanen
 */
public class SkyBox extends WorldObject{
    
    public SkyBox() {
        this.permeable = false;
        this.physical = true;
        this.visible = false;
        this.objectId=-1;
    }

    @Override
    public boolean checkCollision(double X, double Y) {
        return true;
    }
    
    @Override
    public void setLocation(double X,double Y) {
    }
    
      @Override
    public String toString() {
        return "SKY";
    }

    @Override
    public BufferedImage getTextureColumn(double X, double Y, double theta) {
        throw new UnsupportedOperationException("No texture assigned");
    }

    @Override
    public double checkDistance(double X, double Y, double theta) {
        return 0;
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
