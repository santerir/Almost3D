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
 *
 * @author santeriraisanen
 */
public class Wall extends WorldObject {
    
    public Wall() {
        this.permeable = false;
        this.physical = true;
        this.visible = true;
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
    
    
}
