

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package almost3d.graphics;
import almost3d.game.Game;
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
 *This class handles drawing of the 3d world.
 * <p>
 * The class creates a swing JFrame, on to which it draws frames when the Game
 * object calls for a new one. The renderer casts a Ray from the player location
 * for each column of pixels in the JFrame. It then overlays the retrieved texture
 * columns, scaling them on the y-axis in respect to their distance to the player.
 * Right now the resolution is set to 600x800 but there is no reason it couldn't be more.
 * Later this will also implement distance based shading.
 * 
 * 
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
    private final Game game;
    
    
    public Renderer(Game game) {
        this.game = game;
        this.raycaster = game.raycaster;
        this.player = game.player;
        this.fov = Math.PI/3;
        this.dimx = 800;
        this.dimy = 600;
        this.angleIncrement = this.fov/this.dimx;
        ClassLoader cl = this.getClass().getClassLoader();
        try {
        this.sky_texture = ImageIO.read(cl.getResource("sky.png"));
        }
        
        catch (IOException e) {
        }
    }
    
    public void initialize() {

        this.addKeyListener(this.game.controls);
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
        double theta = this.player.getTheta();
        double dTheta = -this.fov/2;
        double xLoc = this.player.getxLoc();
        double yLoc = this.player.getyLoc();
        
        for (int i = 0; i < this.dimx; i++) {
            Ray ray = this.raycaster.cast(xLoc, yLoc, theta+dTheta);
            for(int j=ray.numberOfHits()-1; j >= 0; j--) {
                if(ray.objectHit(j).isVisible()) {
                    BufferedImage textureColumn = scaleColumn(ray.textureColumn(j),ray.distanceToHit(j),dTheta);
                    g.drawImage(textureColumn, i, 0, null);
                }
            }
            dTheta = dTheta+this.angleIncrement;
        }
        
    }
        
   public BufferedImage scaleColumn(BufferedImage im, double dist,double theta) {
        BufferedImage img_s = new BufferedImage(im.getWidth(),im.getHeight(),6);
        Graphics2D g = img_s.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        AffineTransform at = new AffineTransform();
        at.setTransform(1, 0, 0, 1/(dist*Math.cos(theta)), 0, (300-600/(2*dist*Math.cos(theta))));
        g.drawImage(im, at, this);
        return img_s;
       
   }
   
   public BufferedImage shadeColumn() {
       return null;
   }
       
    
    
}
