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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author santeriraisanen
 */
public class MapBuilder extends JFrame implements ActionListener {

    JLabel topLabel = new JLabel("MapBuilder");

    public static final int MAP_HEIGHT = 11;
    public static final int MAP_WIDTH = 11;

    JButton[][] buttons = new JButton[MAP_WIDTH][MAP_HEIGHT];
    JButton runButton;
    JPanel p1;
    JPanel p2;
    JPanel p3;
    int[][] map = new int[MAP_WIDTH][MAP_HEIGHT];

    public MapBuilder(Game g) {
        p1 = new JPanel();
        p1.add(topLabel, BorderLayout.CENTER);

        p2 = new JPanel();
        p2.setLayout(new GridLayout(MAP_WIDTH, MAP_HEIGHT));
        

        for (int x = 0; x < MAP_HEIGHT; x++) {
            for (int y = 0; y < MAP_WIDTH; y++) {
                buttons[x][y] = new JButton("");
                buttons[x][y].addActionListener(this);
                buttons[x][y].setBackground(Color.GRAY);
                p2.add(buttons[x][y]);
            }
        }
        this.runButton=new JButton("Let's Play");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - 450) / 2);
        int y = (int) ((dimension.getHeight() - 500) / 2);

        this.setLocation(x, y);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(p1, BorderLayout.NORTH);
        this.add(p2, BorderLayout.CENTER);
        this.setSize(450, 500);
    }
    
    
    public int runMapBuilder() {
        this.setVisible(true);
        return 1;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
