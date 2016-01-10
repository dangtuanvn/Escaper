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
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import wearefine.Handler;

/**
 *
 * @author Anthony Nguyen
 */
public class Flybie extends DynamicEntity {
    private Animation flybie_left, flybie_right;
    private int patrolDistance;
    private final float startX;
    private float destinationLeft, destinationRight;        
    private boolean faceDirection;
    
    public Flybie(Handler handler, float x, float y, int patrolDistance) {
        super(handler, x, y, DynamicEntity.default_creature_width + 10, DynamicEntity.default_creature_height + 10);
        this.patrolDistance = patrolDistance;
        startX = x;
        destinationRight = startX + patrolDistance;
        destinationLeft = startX - patrolDistance;
        health = default_health;
        speed = default_speed;        
        speed = 2;
        hitbox.x = 10;
        hitbox.y = 15;
        hitbox.width = width - 20;
        hitbox.height = height - 25;
        flybie_left = new Animation(180, Assets.flybie_left);   
        flybie_right = new Animation(180, Assets.flybie_right);   
    }

    @Override
    public void update() {        
        flybie_left.update();
        flybie_right.update();
        if(patrolDistance != 0){
            patrol();
        }
        move();
    }
    
    public void patrol(){  
        if(x > destinationRight){
        faceDirection = false;
        } else if(x < destinationLeft){
        faceDirection = true;
        }
        
        if(faceDirection){
            xMove += 0.2;
            if(xMove > speed){
                xMove = speed;
            }
        }        
        else{
            xMove -= 0.2;
            if(xMove < -speed){
                xMove = -speed;
            }
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
        return flybie_right.getCurrentFrame();
        }
        else{
        return flybie_left.getCurrentFrame();
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
