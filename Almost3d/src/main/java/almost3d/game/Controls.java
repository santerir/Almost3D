/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package almost3d.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author santeriraisanen
 */
public class Controls implements KeyListener {
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    int[] controls;
    
    public Controls(Game game) {
        this.up = false;
        this.down = false;
        this.left = false;
        this.right = false;
        this.controls = new int[2];
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
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
