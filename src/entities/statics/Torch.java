/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.statics;

import gfx.Animation;
import gfx.Assets;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import wearefine.Handler;

/**
 *
 * @author Anthony Nguyen
 */
public class Torch extends StaticEntity {
    private Animation torch_animation;
    private boolean lit;
    private static int objectWidth = 40;
    private static int objectHeight = 80;

    public Torch(Handler handler, float x, float y) {
        super(handler, x, y, objectWidth, objectHeight);
        hitbox.x = 5;
        hitbox.y = 20;
        hitbox.width = 25;
        hitbox.height = 50;
        lit = false;
        torch_animation = new Animation(60, Assets.torch);
    }

    @Override
    public void update() {
        torch_animation.update();
        checkEntityCollisions(0f, 0f);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getXOffset()), (int) (y - handler.getGameCamera().getYOffset()), objectWidth, objectHeight, null);
        // Draw hitbox
        // g.setColor(Color.red);
        // g.fillRect((int) (x + hitbox.x - handler.getGameCamera().getXOffset()), (int) (y + hitbox.y - handler.getGameCamera().getYOffset()), hitbox.width, hitbox.height);
    }

    @Override
    public boolean checkEntityCollisions(float xOffset, float yOffset) {
        boolean check = super.checkEntityCollisions(xOffset, yOffset);
        if (check) {
            if(!lit){
                lit = true;
                handler.getWorld().getEntityManager().getPlayer().setRespawnX(x - 40);
                handler.getWorld().getEntityManager().getPlayer().setRespawnY(y - 20);
            }
        }
        return check;
    }
    
    private BufferedImage getCurrentAnimationFrame() {
       if(lit){
            return torch_animation.getCurrentFrame();
       }
       else{
           return Assets.torch_unlit;
       }
    }     
}
