/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almost3d.world;

import almost3d.game.Game;

/**
 *
 * @author santeriraisanen
 */
public class Player {

    private double xLoc;
    private double yLoc;
    private double theta;
    private Game game;
    private final double linSpeed;
    private final double rotSpeed;

    public Player(Game game) {
        this.game = game;
        this.linSpeed = 1;
        this.rotSpeed = Math.PI / 2.5;
        this.xLoc = 2;
        this.yLoc = 2;
    }

    public Player() {    // for debugging purposes
        this.linSpeed = 1;
        this.rotSpeed = Math.PI / 3;
    }

    public double getTheta() {
        return this.theta;
    }

    public double getyLoc() {
        return this.yLoc;
    }

    public double getxLoc() {
        return this.xLoc;
    }

    public void setLocation(double x, double y, double theta) {
        this.theta = theta;
        this.xLoc = x;
        this.yLoc = y;
    }
//!this.game.map.checkCollisions(this.xLoc + deltaX, this.yLoc + deltaY, 0.05)
    public void update(double delta) {
        int[] controls = game.controls.getControls();
        double deltaX = delta / 1000000000 * Math.cos(this.theta) * this.linSpeed * controls[0];            //why does this not work
        double deltaY = delta / 1000000000 * Math.sin(this.theta) * this.linSpeed * controls[0];
        double deltaTheta = delta / 1000000000 * this.rotSpeed * controls[1];
       
        if (!this.game.map.checkCollisions(this.xLoc + deltaX, this.yLoc + deltaY, 0.05)) {
            this.xLoc = this.xLoc + deltaX;
            this.yLoc = this.yLoc + deltaY;
        } 
        else if (!this.game.map.checkCollisions(this.xLoc, this.yLoc + deltaY, 0.05)) {
            this.yLoc += deltaY;
        }
        else if (!this.game.map.checkCollisions(this.xLoc + deltaX, this.yLoc, 0.05)) {
            this.xLoc += deltaX;
        }
        
        this.theta = (this.theta + deltaTheta)%(Math.PI*2);
    }

}
