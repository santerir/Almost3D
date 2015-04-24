
package almost3d.game;

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
        if (actionValue > 0 && actionValue < 4) {
            g.initialize(actionValue);
            g.start();
        }
        if (actionValue == 4) {
            MapBuilder mb = new MapBuilder(g);
            int retValue = mb.runMapBuilder();
        }
    }
}

