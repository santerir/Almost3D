/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package almost3d.world;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author santeriraisanen
 */
public class Wall extends WorldObject {
    
    public Wall() {
        this.texture = new BufferedImage(100,500,6);
        this.permeable = false;
        this.physical = true;;
        this.visible = true;
        ClassLoader cl = this.getClass().getClassLoader();
        try {
        this.texture = ImageIO.read(cl.getResource("wall.png"));
        }
        
        catch (IOException e) {
        }
        
    }
    
    @Override
    public BufferedImage getTextureColumn(int n) {              // this solution may not be optimal, but we need a copy of texture for shading/scaling
        return this.texture.getSubimage(n,0,1,500);
    } 

    @Override
    public int checkCollision(double X, double Y) {
        if (X%1==0) {
            return (int) (Y%1*100);
        }
        return (int) (X%1*100);
    }
    
    @Override
    public String toString() {
        return "WALL";
    }
    
    
}
