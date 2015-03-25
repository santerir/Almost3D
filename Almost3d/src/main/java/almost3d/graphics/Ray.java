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
 *
 * @author santeriraisanen
 */
public class Ray {
    private ArrayList<WorldObject> objects;
    private ArrayList<Double> distance;
    private ArrayList<BufferedImage> textureColumn;
    
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
