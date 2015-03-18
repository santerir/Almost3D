/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package almost3d.almost3d;

import java.util.ArrayList;

/**
 *
 * @author santeriraisanen
 */
public class Ray {
    private ArrayList<worldObject> objects;
    private ArrayList<Double> distance;
    private ArrayList<Integer> hitLoc;
    
    public Ray() {
        this.objects = new ArrayList<>();
        this.distance = new ArrayList<>();
        this.hitLoc = new ArrayList<>();
                
    }
    
    public void addHit(worldObject obj, double dits, int hitLoc) {
        this.objects.add(obj);
        this.distance.add(dits);
        this.hitLoc.add(hitLoc);
    }
    
    public int numberOfHits() {
        return(this.objects.size());
    }
    
    public double distanceToHit(int n) {
        return(this.distance.get(n).doubleValue());
    }
    
    public int locationOfHit(int n) {
        return(this.hitLoc.get(n).intValue());
    }
     
    public worldObject objectHit(int n) {
        return(this.objects.get(n));
    }
    
}
