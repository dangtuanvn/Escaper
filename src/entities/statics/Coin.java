/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.statics;

import gfx.Animation;
import gfx.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import audio.Sfx;
import wearefine.Handler;

/**
 *
 * @author Anthony Nguyen
 */
public class Coin extends StaticEntity {
    private static int objectWidth = 40;
    private static int objectHeight = 40;
    private static Animation coin_animation;
    public Coin(Handler handler, float x, float y) {
        super(handler, x, y, objectWidth, objectHeight);        
        hitbox.x = 0;
        hitbox.y = 0;
        hitbox.width = objectWidth;
        hitbox.height = objectHeight;
        coin_animation = new Animation(60, Assets.coin);
    }

    @Override
    public void update() {        
        coin_animation.update();
        checkEntityCollisions(0f, 0f);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getXOffset()), (int)(y - handler.getGameCamera().getYOffset()), objectWidth, objectHeight, null);
        // Draw hitbox
        // g.setColor(Color.red);
        // g.fillRect((int) (x + hitbox.x - handler.getGameCamera().getXOffset()), (int) (y + hitbox.y - handler.getGameCamera().getYOffset()), hitbox.width, hitbox.height);
    }
    
    @Override
    public boolean checkEntityCollisions(float xOffset, float yOffset) {
        boolean check = super.checkEntityCollisions(xOffset, yOffset);
        if (check) {          
            Sfx.COIN.play();
            handler.getWorld().coinCollect();
            handler.getWorld().getEntityManager().getEntities().remove(this);            
        }
        return check;
    }
    
    private BufferedImage getCurrentAnimationFrame() {
       return coin_animation.getCurrentFrame();
    }          
    
    /*@Override
    public boolean isSolid(){
    return true;
    }*/
}
