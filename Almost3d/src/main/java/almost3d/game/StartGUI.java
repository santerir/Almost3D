/*
 * Copyright (C) 2015 santeriraisanen
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package almost3d.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author santeriraisanen
 */

    
public class StartGUI extends JDialog{
    class startListener implements ActionListener {
    Game g;
    StartGUI parent;
    public startListener(StartGUI parent){
        super();
        this.parent = parent;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        parent.setReturnValue(1);
        parent.dispose();
    }
}
    int value;
    JPanel panel;
    JButton startButton;
    ActionListener startListener;
    
    public StartGUI(JFrame parent) {
        super(parent,"Super Cool Awsome Game",true);
        this.value = 0;
        panel = new JPanel();
        startListener = new startListener(this);
        
        startButton = new JButton("OK");
        startButton.addActionListener(startListener);

        panel.add(startButton);
        this.add(panel);
        this.setTitle("Super Cool Awsome Game");
        this.setSize(300, 200);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    public int openGUI() {
         this.setVisible(true);
         return(this.value);
    }
    
    public void setReturnValue(int v) {
        this.value = v;
    }
}

