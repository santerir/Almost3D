/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package almost3d.world;

import almost3d.game.Game;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author santeriraisanen
 */
public class PlayerTest {
    
    public PlayerTest() {
    }
    
    @Test
    public void updateTest() {
        Game g = new Game();
        g.initialize();
        int delta = 10000;
        
        g.controls.debugControls();
        double X = g.player.getxLoc() + delta / 1000000000 * Math.cos(0.5) * 1.2;
        double Y = g.player.getxLoc() + delta / 1000000000 * Math.sin(0.5) * 1.2;
        
        g.player.update(delta);
        assertTrue(X == g.player.getxLoc() && Y == g.player.getyLoc());
        
    }
    
    @Test
    public void setLocationTest() {
        Player p = new Player();
        
        Random r = new Random();
        double x = r.nextDouble();
        double y = r.nextDouble();
        double theta = r.nextDouble();
        
        p.setLocation(x, y, theta);
        
        assertTrue(p.getxLoc()==x&&p.getyLoc()==y&&p.getTheta()==theta);
                
               
       
        
    }
    
}
