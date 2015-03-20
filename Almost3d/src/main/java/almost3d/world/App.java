package almost3d.world;

import almost3d.graphics.Raycaster;
import almost3d.graphics.Renderer;
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
        Player player = new Player();
        Renderer renderer = new Renderer(rayCaster,player,800,600,Math.PI/2);
        renderer.initialize();
        
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("X:");
            double x = sc.nextDouble();
            System.out.println("Y:");
            double y = sc.nextDouble();
            System.out.println("Theta:");
            double theta = sc.nextDouble();
            
            player.setLocation(x, y, theta);
            renderer.repaint();
        }
    }
}

