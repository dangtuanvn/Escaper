/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.statics;

import gfx.Assets;
import java.awt.Color;
import java.awt.Graphics;
import wearefine.Handler;

/**
 *
 * @author s3500291
 */
public class Spike3 extends StaticEntity {
    private static int objectWidth = 70;
    private static int objectHeight = 50;
    public Spike3(Handler handler, float x, float y) {
        super(handler, x, y, objectWidth, objectHeight);        
        hitbox.x = 5;
        hitbox.y = 10;
        hitbox.width = 60;
        hitbox.height = 40;
    }

    @Override
    public void update() {
        checkEntityCollisions(0f, 0f);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.spike3, (int)(x - handler.getGameCamera().getXOffset()), (int)(y - handler.getGameCamera().getYOffset()), objectWidth, objectHeight, null);
        // g.setColor(Color.red);
        // g.fillRect((int) (x + hitbox.x - handler.getGameCamera().getXOffset()), (int) (y + hitbox.y - handler.getGameCamera().getYOffset()), hitbox.width, hitbox.height);
    }    
    
    @Override
    public boolean checkEntityCollisions(float xOffset, float yOffset) {
        boolean check = super.checkEntityCollisions(xOffset, yOffset);
        if (check) {

            handler.getWorld().getEntityManager().getPlayer().die();
        }

        return check;
    }
}