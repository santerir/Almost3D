/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package almost3d.graphics;


import almost3d.graphics.Raycaster;
import almost3d.graphics.Ray;
import almost3d.world.Map;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author santeriraisanen
 */
public class RaycasterTest {
    
    public RaycasterTest() {
    }
    


    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void TestWalls() {
        Map map = new Map();
        map.load(new int[][]{{1,1,1,1},{1,0,0,1},{1,0,0,1},{1,1,1,1}});
        Raycaster raycaster = new Raycaster(map);
        
        Random r = new Random();
        double randomValue = 2*Math.PI * r.nextDouble();
        
        Ray ray = raycaster.cast(2, 2, randomValue);
        
        assertTrue(ray.objectHit(ray.numberOfHits()-1).toString().equals("WALL"));
    
    }
    
    @Test
    public void TestSky() {
        Map map = new Map();
        map.load(new int[][]{{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}});
        Raycaster raycaster = new Raycaster(map);
        
        Random r = new Random();
        double randomValue = 2*Math.PI * r.nextDouble();
        
        Ray ray = raycaster.cast(2, 2, randomValue);
        
        assertTrue(ray.objectHit(ray.numberOfHits()-1).toString().equals("SKY"));
    }
    
    @Test
    public void TestDistance() {
        Map map = new Map();
        map.load(new int[][]{{1,1,1,1},{1,0,0,1},{1,0,0,1},{1,1,1,1}});
        Raycaster raycaster = new Raycaster(map);
        
        Random r = new Random();
        double theta = 2*Math.PI * r.nextDouble(); 
        
        Ray ray = raycaster.cast(2, 2, theta);
        double dist = 1/ray.distanceToHit(ray.numberOfHits()-1);
        double distTrue;
        distTrue = (theta%Math.PI>Math.PI/4&&theta%Math.PI<3*Math.PI/2) ? Math.abs(Math.sin(theta)): Math.abs(Math.cos(theta));
        long a = (long) (1000000*dist);
        long b = (long) (1000000*distTrue);

        assertTrue(a==b);
    }
}

