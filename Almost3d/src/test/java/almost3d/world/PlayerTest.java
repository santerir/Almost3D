/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package almost3d.world;

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
