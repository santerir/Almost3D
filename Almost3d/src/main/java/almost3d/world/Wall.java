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
        this.texture = new BufferedImage(100,500,6);
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
    public BufferedImage getTextureColumn(int n) {              
        return this.texture.getSubimage(n,0,1,500);
    } 

    @Override
    public int checkCollision(double X, double Y) {
        if (X%1==0) {
            return (int) (Y%1*100);
        }
        if (Y%1==0) {
            return (int) (X%1*100);
        }
        else return 1;
    }
    
    @Override
    public String toString() {
        return "WALL";
    }
    
    
}
