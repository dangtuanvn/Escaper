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
public class Crate extends StaticEntity {
    private static int objectWidth = 40;
    private static int objectHeight = 40;
    public Crate(Handler handler, float x, float y) {
        super(handler, x, y, objectWidth, objectHeight);        
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
        g.drawImage(Assets.crate, (int)(x - handler.getGameCamera().getXOffset()), (int)(y - handler.getGameCamera().getYOffset()), objectWidth, objectHeight, null);
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
