package almost3d.almost3d;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Map map = new Map();
        map.load();
        Raycaster rayCaster = new Raycaster(map);
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("X:");
            double x = sc.nextDouble();
            System.out.println("Y:");
            double y = sc.nextDouble();
            System.out.println("Theta:");
            double theta = sc.nextDouble();
            
            Ray ray = rayCaster.cast(x,y,theta);
            
            for (int i=0;i<ray.numberOfHits();i++) {
                System.out.println(ray.objectHit(i).toString() + ray.distanceToHit(i));
            }
        }
    }
}
