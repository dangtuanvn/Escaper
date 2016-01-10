/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.statics;

import gfx.Assets;
import java.awt.Graphics;
import wearefine.Handler;

/**
 *
 * @author Anthony Nguyen
 */
public class Door extends StaticEntity {
    private static int objectWidth = 70;
    private static int objectHeight = 70;
    public Door(Handler handler, float x, float y) {
        super(handler, x, y, 64, 68);        
        hitbox.x = 0;
        hitbox.y = 0;
        hitbox.width = 64;
        hitbox.height = 68;
    }

    @Override
    public void update() {
        checkEntityCollisions(0f , 0f );
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.door[0], (int)(x - handler.getGameCamera().getXOffset()), (int)(y - handler.getGameCamera().getYOffset()), 64, 68, null);
    }
    
    @Override
    public boolean checkEntityCollisions(float xOffset, float yOffset)
    {       
        boolean check = super.checkEntityCollisions(xOffset, yOffset); 
        if(check){            
            // handler.getWorld().getEntityManager().getEntities().remove(this);
        }
        return check;       
    }
}
