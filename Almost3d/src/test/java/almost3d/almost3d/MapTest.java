/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package almost3d.almost3d;

import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author santeriraisanen
 */
public class MapTest {
    
    public MapTest() {
    }
    
    @Test
    public void testCollisions() {
        Map map = new Map();
        map.load(new int[][]{{0,0,0,0,0},{0,1,0,1,0},{0,0,0,0,0},{0,1,0,1,0},{0,0,0,0,0}});
        
        Random r = new Random();
        double radius = 1 * r.nextDouble();
        
        boolean collisions = map.checkCollisions(2.5, 2.5, radius);
        boolean trueCollisions = (radius >= 1/Math.sqrt(2));
        
        System.out.println(radius);
        System.out.println(collisions);
        System.out.println(trueCollisions);
        
        assertTrue(collisions == trueCollisions);
    }
}
