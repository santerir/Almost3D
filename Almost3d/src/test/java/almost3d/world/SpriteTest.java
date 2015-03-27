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

package almost3d.world;

import almost3d.game.Game;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author santeriraisanen
 */
public class SpriteTest {
    
    public SpriteTest() {
    }
    
    @Test
    public void ConstructorTest(){
        Game g = new Game();
        Sprite s = new Sprite(0,0,g);
        
        assertTrue(s.toString().equals("SPRITE"));
    }
}
