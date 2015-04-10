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
public class MapTest {
    
    public MapTest() {
    }
    
    @Test
    public void testCheckObject() {
        Map map = new Map();
        int m[][] = new int[][]{{0,0,0,0,0},{0,1,0,1,0},{0,0,0,0,0},{0,1,0,1,0},{0,0,0,0,0}};
        map.load(m);
        
        Random r = new Random();
        int x = r.nextInt(5);
        int y = r.nextInt(5);
        
        WorldObject obj = map.checkObject(x, y);
       
        assertTrue((obj.toString().equals("WALL")&&m[x][y]==1)||(obj.toString().equals("NULL OBJECT")&&m[x][y]==0));
    }
    
    @Test
    public void testLoad() {
        Map map = new Map();
        map.load();
        assertTrue(map.checkObject(11, 11).getId()==-1);
    }
    
    @Test
    public void updateMapTest() {
         Map map = new Map();
         map.load(new int[][]{{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,2}});
         WorldObject sprite = map.getObject(2);
         sprite.setLocation(1, 1);
         map.updateMap(sprite);
         assertTrue(map.checkObject(0, 0).getId()==2&&map.checkObject(4, 4).getId()==0);
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
