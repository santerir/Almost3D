/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package almost3d.graphics.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author santeriraisanen
 */
public class Controls implements KeyListener {
    private boolean down;
    private boolean up;
    private boolean left;
    private boolean right;

    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
    public void clear() {
        this.down = this.up = this.left = this.up = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'w': this.up = true;
            case 's': this.down = true;
            case 'a': this.left = true;
            case 'd': this.right = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
