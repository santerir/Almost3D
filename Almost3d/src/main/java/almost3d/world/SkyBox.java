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
public class SkyBox extends WorldObject{
    
    public SkyBox() {
        this.permeable = false;
        this.physical = true;
        this.visible = false;
    }

    @Override
    public int checkCollision(double X, double Y) {
        return -1;
    }
    
      @Override
    public String toString() {
        return "SKY";
    }

    @Override
    public BufferedImage getTextureColumn(int n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
