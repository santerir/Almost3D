/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package almost3d.world;

import java.awt.image.BufferedImage;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author santeriraisanen
 */
public class WallTest {
    
    public WallTest() {
       
    }
    
    @Test
    public void textureSizeTest() {
         Wall w = new Wall();
         BufferedImage tex = w.getTextureColumn(1);
         
         assertTrue(tex.getWidth() == 1);
    }
    
}
