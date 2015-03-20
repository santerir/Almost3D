/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package almost3d.almost3d;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 *
 * @author santeriraisanen
 */
public class Renderer {
    private double fov;
    private int dimx;
    private int dimy;
    private Raycaster raycaster;
    private Player player;
    private double angleIncrement;
    private BufferedImage next_frame;
    
    
    public void Renderer(Raycaster raycaster, Player player, int dimx, int dimy, double fov) {
        this.raycaster = raycaster;
        this.player = player;
        this.fov = fov;
        this.dimx = dimx;
        this.dimy = dimy;
        this.angleIncrement = this.fov/this.dimx; 
    }
    
    
    private void render3D(Graphics2D g) {
        double theta = this.player.getTheta()-this.fov/2;
        double xLoc = this.player.getxLoc();
        double yLoc = this.player.getyLoc();
        
        for (int i = 0; i < this.dimx; i++) {
            Ray ray = this.raycaster.cast(xLoc, xLoc, theta);
            for(int j=ray.numberOfHits()-1; j >= 0; j--) {
                if(ray.objectHit(j).isVisible()) {
                    BufferedImage textureColumn = scaleColumn(ray.objectHit(j).getTextureColumn(j),ray.distanceToHit(j));
                    g.drawImage(textureColumn, i, 0, null);
                }
            }
            theta = theta+this.angleIncrement;
        }
        
    }
        
   public BufferedImage scaleColumn(BufferedImage im, double dist) {
       Graphics2D g = im.createGraphics();
       AffineTransform at = new AffineTransform();
       at.setToScale(1/dist, 0);
       return im;
       
   }
   
   public BufferedImage shadeColumn() {
       return null;
   }
       
    
    
}
