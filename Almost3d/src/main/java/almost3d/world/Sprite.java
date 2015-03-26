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
public class Sprite extends WorldObject{
    private final double xLocation;
    private final double yLocation;
    
    public Sprite(double X, double Y) {
        this.permeable = true;
        this.physical = true;
        this.visible = true;
        ClassLoader cl = this.getClass().getClassLoader();
        try {
        this.texture = ImageIO.read(cl.getResource("sprite.png"));
        }
        
        catch (IOException e) {
        }
        this.xLocation = X;
        this.yLocation = Y;
    }

    @Override
    public BufferedImage getTextureColumn(double X, double Y, double theta) {
        double scalar_projection = (this.xLocation-X)*Math.cos(theta+Math.PI/2)+(this.yLocation-Y)*Math.sin(theta+Math.PI/2);
        if (Math.abs(scalar_projection)>0.5) {
            return this.texture.getSubimage(0, 0, 1, 600);
        }
        else {
            int column = (int) (((scalar_projection+0.5)%1)*600);
            return this.texture.getSubimage(column, 0, 1, 600);
        }
    }

    @Override
    public boolean checkCollision(double X, double Y) {
        return true;
    }
    
    @Override
    public String toString() {
        return "SPRITE";
    }

    @Override
    public double checkDistance(double X, double Y, double theta) {
        return Math.sqrt(Math.pow(this.xLocation-X,2)+Math.pow(this.yLocation-Y, 2));
    }
    
}
