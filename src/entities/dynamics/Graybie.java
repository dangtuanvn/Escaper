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
public class Graybie extends DynamicEntity {
    private Animation graybie_left, graybie_right;
    private int patrolDistance;
    private final float startX;
    private float destinationLeft, destinationRight;        
    private boolean faceDirection;
    
    public Graybie(Handler handler, float x, float y, int patrolDistance) {
        super(handler, x, y, DynamicEntity.default_creature_width, DynamicEntity.default_creature_height);
        this.patrolDistance = patrolDistance;
        startX = x;
        destinationRight = startX + patrolDistance;
        destinationLeft = startX - patrolDistance;
        health = default_health;
        speed = default_speed;        
        speed = 1;
        hitbox.x = 5;
        hitbox.y = 5;
        hitbox.width = width - 10;
        hitbox.height = height - 5;
        graybie_left = new Animation(120, Assets.graybie_left);   
        graybie_right = new Animation(120, Assets.graybie_right);   
    }

    @Override
    public void update() {        
        graybie_left.update();
        graybie_right.update();
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
            xMove += 0.1;
            if(xMove > speed){
                xMove = speed;
            }
        }        
        else{
            xMove -= 0.1;
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
        return graybie_right.getCurrentFrame();
        }
        else{
        return graybie_left.getCurrentFrame();
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

