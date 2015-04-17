package almost3d.game;

import almost3d.game.Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;



/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {

         
        Game g = new Game();
        StartGUI startGui = new StartGUI(new JFrame());
        int actionValue = startGui.openGUI();
        if (actionValue == 1) {
            g.initialize();
            g.start();
        }
        
    }
}

