/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.dynamics;

import static entities.dynamics.DynamicEntity.default_health;
import static entities.dynamics.DynamicEntity.default_speed;
import gfx.Animation;
import gfx.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import wearefine.Handler;

/**
 *
 * @author Anthony Nguyen
 */
public class Plantie extends DynamicEntity {
    private Animation plantie_left, plantie_right;            
    private boolean faceDirection;
    
    public Plantie(Handler handler, float x, float y) {
        super(handler, x, y, DynamicEntity.default_creature_width, DynamicEntity.default_creature_height + 15);
        health = default_health;
        speed = default_speed;        
        hitbox.x = 5;
        hitbox.y = 5;
        hitbox.width = width - 10;
        hitbox.height = height - 5;
        plantie_left = new Animation(120, Assets.plantie_left);
        plantie_right = new Animation(120, Assets.plantie_right);
    }

    @Override
    public void update() {
        faceDirection(handler.getWorld().getEntityManager().getPlayer().getX());
        plantie_left.update();
        plantie_right.update();
        move();
    }    
    
    // This is bad design, this is just a test to see how bad the coupling is without an observer
    // basicly this object will constantly get the Player's x coordination and face towards the Player
    public void faceDirection(float playerX){
        if(this.x > playerX){
            faceDirection = false;
        }
        else{
            faceDirection = true;
        }
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getXOffset()), (int) (y - handler.getGameCamera().getYOffset()), width, height, null);
        // Draw hitbox
        // g.setColor(Color.red);
        // g.fillRect((int) (x + hitbox.x - handler.getGameCamera().getXOffset()), (int) (y + hitbox.y - handler.getGameCamera().getYOffset()), hitbox.width, hitbox.height);
    }
    
    private BufferedImage getCurrentAnimationFrame() {
        if(faceDirection){
            return plantie_right.getCurrentFrame();
        }
        else{
            return plantie_left.getCurrentFrame();
        }
    }
    
        @Override
    public boolean checkEntityCollisions(float xOffset, float yOffset) {
        boolean check = super.checkEntityCollisions(xOffset, yOffset);
        if (check) {
            handler.getWorld().getEntityManager().getPlayer().die();
        }
        return check;
    }
    
    /*@Override
    public boolean isSolid(){
    return true;
    }*/
}
