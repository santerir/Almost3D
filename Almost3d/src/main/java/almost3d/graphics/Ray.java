/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package almost3d.graphics;

import almost3d.world.WorldObject;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * An instance of this class holds refrences to all the world objects which intersect a ray cast from
 * location (x,y) on the map in the direction theta. The class also records the distance to the object from the ray's origin, as well as the column of
 * pixels on the object's texture which is hit by the ray.
 * 
 * @author santeriraisanen
 */
public class Ray {
    private final ArrayList<WorldObject> objects;
    private final ArrayList<Double> distance;
    private final ArrayList<BufferedImage> textureColumn;
    
    public Ray() {
        this.objects = new ArrayList<>();
        this.distance = new ArrayList<>();
        this.textureColumn = new ArrayList<>();
                
    }
    
    public void addHit(WorldObject obj, double dits, BufferedImage texture) {
        this.objects.add(obj);
        this.distance.add(dits);
        this.textureColumn.add(texture);
    }
    
    public int numberOfHits() {
        return(this.objects.size());
    }
    
    public double distanceToHit(int n) {
        return(this.distance.get(n).doubleValue());
    }
    
    public BufferedImage textureColumn(int n) {
        return(this.textureColumn.get(n));
    }
     
    public WorldObject objectHit(int n) {
        return(this.objects.get(n));
    }
    
}
