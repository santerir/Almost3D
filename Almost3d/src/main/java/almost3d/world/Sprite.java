/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package almost3d.world;

import almost3d.game.Game;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author santeriraisanen
 */
public class Sprite extends WorldObject{
    private double xLocation;
    private double yLocation;
    private double theta;
    private Random random;
    private final Game game;
    private int updateCycle;
    
    public Sprite(double X, double Y,Game g) {
        this.updateCycle = 0;
        this.random = new Random();
        this.theta = 0;
        this.game = g;
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
        return false;
    }
    
    @Override
    public String toString() {
        return "SPRITE";
    }

    @Override
    public double checkDistance(double X, double Y, double theta) {
        return Math.sqrt(Math.pow(this.xLocation-X,2)+Math.pow(this.yLocation-Y, 2));
    }
    
    @Override
    public double[] getLocation() {
        return new double[]{this.xLocation,this.yLocation,0.4};
    }
    

    @Override
    public int update(double deltaTime) {
        this.updateCycle = (this.updateCycle+1)%100;
        if(this.updateCycle==0) {
            this.theta = (this.theta + this.random.nextGaussian())%(Math.PI*2);
        }
        
        double deltaX = deltaTime / 1000000000 * Math.cos(this.theta)*0.7;     
        double deltaY = deltaTime / 1000000000 * Math.sin(this.theta)*0.7;
       
        if (!this.game.map.checkCollisions(this.xLocation + deltaX, this.yLocation + deltaY, 0.4)) {
            this.xLocation = this.xLocation + deltaX;
            this.yLocation = this.yLocation + deltaY;
            return 1;
        } 
        if (!this.game.map.checkCollisions(this.xLocation, this.yLocation + deltaY, 0.4)) {
            this.yLocation += deltaY;
            return 1;
        }
        if (!this.game.map.checkCollisions(this.xLocation + deltaX, this.yLocation, 0.4)) {
            this.xLocation += deltaX;
            return 1; 
        }
        return 0;
    }
}
    

