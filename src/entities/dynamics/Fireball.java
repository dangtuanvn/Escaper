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
import tiles.Tile;
import wearefine.Handler;

/**
 *
 * @author Anthony Nguyen
 */
public class Fireball extends DynamicEntity {
    private Animation fireballDown, fireballUp;
    private int patrolDistance;
    private final float startY;
    private float destinationUp, destinationDown;        
    private boolean faceDirection;
    
    public Fireball(Handler handler, float x, float y, int patrolDistance) {
        super(handler, x, y, DynamicEntity.default_creature_width * 3, DynamicEntity.default_creature_height * 3);
        this.patrolDistance = patrolDistance;
        startY = y;
        destinationUp = startY - patrolDistance;
        destinationDown = startY + patrolDistance;
        health = default_health;
        speed = default_speed;        
        speed = 7;
        hitbox.x = 47;
        hitbox.y = 30;
        hitbox.width = width - 100;
        hitbox.height = height - 55;
        fireballDown = new Animation(90, Assets.fireballDown);       
        fireballUp = new Animation(90, Assets.fireballUp);
    }

    @Override
    public void update() {       
        fireballUp.update();                      
        fireballDown.update();
        patrol();        
        move();
    }
       
    public void patrol(){  
        if(y > destinationDown){
        faceDirection = true;
        } else if(y < destinationUp){
        faceDirection = false;
        }
        
        if(faceDirection){
            yMove -= 0.1;
            if(yMove < -speed){
                yMove = -speed;
            }
        }        
        else{
            yMove += 0.1;
            if(yMove > speed){
                yMove = speed;
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
        if(yMove > 0){
            return fireballDown.getCurrentFrame();
        }
       else{
            return fireballUp.getCurrentFrame();
        }
    }
    
    @Override
    public void moveY(){
        if(yMove < 0){ // Moving up

            
                y += yMove;
            

        }
        
        if(yMove > 0){ // Moving down

                y += yMove;
        
    }
    
    /*    @Override
    public boolean isSolid(){
    return true;
    }*/
}
}
