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
 * This sprite is a simple, visible, movable world object.
 * <p>
 * This object moves in a random direction on update. It can collide with world
 * objects, but passes through the player untouched. In the style of old school
 * first person shooters, it doesn't have a bearing, but rather it always faces
 * the player. Thus it is only a two dimensional model.
 * 
 *
 * @author santeriraisanen
 */
public class Sprite extends WorldObject{
    private double xLocation;
    private double yLocation;
    private double xPrevLocation;
    private double yPrevLocation;
    private double theta;
    private Random random;
    private final Game game;
    private int updateCycle;
    
    public Sprite(double X, double Y,Game g,int id) {
        this.updateCycle = 0;
        this.random = new Random();
        this.theta = 0;
        this.game = g;
        this.permeable = true;
        this.physical = true;
        this.visible = true;
        this.objectId=id;
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
    public double[] getPrevLocation() {
        return new double[]{this.xPrevLocation,this.yPrevLocation,0.4};
    }
    
    @Override
    public void setLocation(double X,double Y) {
        this.xPrevLocation = this.xLocation;
        this.yPrevLocation = this.yLocation;
        this.xLocation=X;
        this.yLocation=Y;
    }
    

    @Override
    public int update(double deltaTime) {
        this.xPrevLocation = this.xLocation;
        this.yPrevLocation = this.yLocation;
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
    

