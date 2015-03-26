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
public class NullObj extends WorldObject{
    
    public NullObj() {
        this.permeable=true;
        this.physical=false;
        this.visible=false;
    }

    @Override
    public boolean checkCollision(double X, double Y) {
        return false;
    }
    
    @Override
    public String toString() {
        return "NULL OBJECT";
    }

    @Override
    public BufferedImage getTextureColumn(double X, double Y, double theta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double checkDistance(double X, double Y, double theta) {
        return 0;
    }
}
