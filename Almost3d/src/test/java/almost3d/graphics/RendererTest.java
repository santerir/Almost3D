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

package almost3d.graphics;

import almost3d.game.Game;
import almost3d.world.Wall;
import java.awt.image.BufferedImage;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author santeriraisanen
 */
public class RendererTest {
    public RendererTest(){

    }
    
    @Test
    public void scaleColumnTest() {
        Wall w = new Wall();
        Game g = new Game();
        BufferedImage bi = g.renderer.scaleColumn(w.getTextureColumn(1, 1, 1), 1, 0);
        assertTrue(bi.getWidth()==1);
    }
    
    @Test
    public void initializeWidthTest() {
        Game g = new Game();
        g.renderer.initialize();
        assertTrue(g.renderer.getWidth()==800);
    }
    
    @Test
    public void initializeListenerTest() {
        Game g = new Game();
        g.initialize();
        assertTrue(g.renderer.getKeyListeners().length==1);
    }
    
}
