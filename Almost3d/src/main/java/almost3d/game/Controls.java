
package almost3d.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Listens to the game window for key presses and updates player controls.
 * <p>
 * On key press and release events, the class sets the relevant control flag to
 * true or false respectively. The current state of the controls can then be
 * retrieved with the getControls method.
 * 
 *
 * @author santeriraisanen
 */
public class Controls implements KeyListener {
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    int[] controls;
    private final Game game;

    /**
     *
     * @param game
     */
    
    public Controls(Game game) {
        this.up = false;
        this.down = false;
        this.left = false;
        this.right = false;
        this.controls = new int[2];
        this.game = game;
    }
    
    /**
     * Only for debugging purposes
     */
    public void debugControls() {  
        this.up = true;
        this.down=true;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
    /**
     *
     * @return {x,y}, x is -1 (down) or 1 (up), y is -1 (left) or 1 (right)
     */
    public int[] getControls() {
        controls[0] = 0;
        controls[1] = 0;
        if(this.up&&!this.down) {
            controls[0] = 1;
        }
        if(this.down&&!this.up) {
            controls[0] = -1;
        }
        if(this.left&&!this.right) {
            controls[1] = -1;
        }
        if(this.right&&!this.left) {
            controls[1] = 1;
        }
        return controls;
        

    }

    /**
     *
     */
    public void clear() {
        this.up = this.down = this.left = this.right = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        switch (e.getKeyChar()) {
            case 'w':   this.up = true;
                        break;
            case 's':   this.down = true;
                        break;
            case 'a':   this.left = true;
                        break;
            case 'd':   this.right = true;
                        break;
            case 'p':   this.game.pause();
                        break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'w':   this.up = false;
                        break;
            case 's':   this.down = false;
                        break;
            case 'a':   this.left = false;
                        break;
            case 'd':   this.right = false;
                        break;
        }
    }
    
}
