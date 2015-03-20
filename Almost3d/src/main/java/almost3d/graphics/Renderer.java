package almost3d.graphics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import almost3d.world.Player;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 *
 * @author santeriraisanen
 */
public class Renderer extends JFrame {
    private final double fov;
    private final int dimx;
    private final int dimy;
    private final Raycaster raycaster;
    private final Player player;
    private final double angleIncrement;
    private BufferedImage sky_texture;
    
    
    public Renderer(Raycaster raycaster, Player player, int dimx, int dimy, double fov) {
        this.raycaster = raycaster;
        this.player = player;
        this.fov = fov;
        this.dimx = dimx;
        this.dimy = dimy;
        this.angleIncrement = this.fov/this.dimx;
        ClassLoader cl = this.getClass().getClassLoader();
        try {
        this.sky_texture = ImageIO.read(cl.getResource("sky.png"));
        }
        
        catch (IOException e) {
        }
    }
    
    public void initialize() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(this.dimx,this.dimy);
        this.setResizable(false);
        this.setVisible(true);
    }
    
    @Override
    public void paint(Graphics g) {
        ColorModel cm = this.sky_texture.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = this.sky_texture.copyData(null);
        BufferedImage preFrame = new BufferedImage(cm, raster, isAlphaPremultiplied, null);
        Graphics2D g2 = preFrame.createGraphics();
        render3D(g2);
        g.drawImage(preFrame, 0, 0, null);
    }
    
    private void render3D(Graphics2D g) {
        double theta = this.player.getTheta()-this.fov/2;
        double xLoc = this.player.getxLoc();
        double yLoc = this.player.getyLoc();
        
        for (int i = 0; i < this.dimx; i++) {
            Ray ray = this.raycaster.cast(xLoc, xLoc, theta);
            for(int j=ray.numberOfHits()-1; j >= 0; j--) {
                if(ray.objectHit(j).isVisible()) {
                    BufferedImage textureColumn = scaleColumn(ray.objectHit(j).getTextureColumn(ray.locationOfHit(j)),ray.distanceToHit(j));
                    g.drawImage(textureColumn, i, 50, null);
                }
            }
            theta = theta+this.angleIncrement;
        }
        
    }
        
   public BufferedImage scaleColumn(BufferedImage im, double dist) {
        BufferedImage img_s = new BufferedImage(im.getWidth(),im.getHeight(),6);
        Graphics2D g = img_s.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        AffineTransform at = new AffineTransform();
        at.setTransform(1, 0, 0, 1/dist, 0, (250-500/(2*dist)));
        g.drawImage(im, at, this);
        return img_s;
       
   }
   
   public BufferedImage shadeColumn() {
       return null;
   }
       
    
    
}
