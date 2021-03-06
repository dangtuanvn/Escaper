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
public class Grass1 extends StaticEntity {
    private static int objectWidth = 70;
    private static int objectHeight = 40;
    public Grass1(Handler handler, float x, float y) {
        super(handler, x, y, objectWidth, objectHeight);        
        hitbox.x = 0;
        hitbox.y = 0;
        hitbox.width = 0;
        hitbox.height = 0;
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.grass1, (int)(x - handler.getGameCamera().getXOffset()), (int)(y - handler.getGameCamera().getYOffset()), objectWidth, objectHeight, null);
    }    
}