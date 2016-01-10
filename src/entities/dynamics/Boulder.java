/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.dynamics;

import static entities.dynamics.DynamicEntity.default_health;
import static entities.dynamics.DynamicEntity.default_speed;
import gfx.Assets;
import java.awt.Color;
import java.awt.Graphics;
import wearefine.Handler;

/**
 *
 * @author Anthony Nguyen
 */
public class Boulder extends DynamicEntity {
    
    private int patrolDistance;
    private final float startY;    
    private float destinationUp, destinationDown;        
    private boolean moving;
    
    public Boulder(Handler handler, float x, float y, int patrolDistance) {
        super(handler, x, y, DynamicEntity.default_creature_width * 2, DynamicEntity.default_creature_height * 2);
        this.patrolDistance = patrolDistance;
        startY = y;
        destinationUp = startY - patrolDistance;
        destinationDown = startY + patrolDistance;
        health = default_health;
        speed = default_speed;        
        speed = 15;
        hitbox.x = 10;
        hitbox.y = 60;
        hitbox.width = width - 20;
        hitbox.height = height - 70;
    }

    @Override
    public void update() {             
        patrol();        
        move();
    }
      
    public void patrol(){  
        if(yMove < 0){
            y =-width;
            moving = true;
        }
        if (y > destinationDown){
            moving = false;            
        }
        if(!moving){
            yMove -= 0.5;
            if(yMove < -speed){
                yMove = -speed;
            }
        }        
        else{
            yMove += 0.5;
            if(yMove > speed){
                yMove = speed;
            }
        }        
    }    
    
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.boulder, (int) (x - handler.getGameCamera().getXOffset()), (int) (y - handler.getGameCamera().getYOffset()), width, height, null);
        // Draw hitbox
        // g.setColor(Color.red);
        // g.fillRect((int) (x + hitbox.x - handler.getGameCamera().getXOffset()), (int) (y + hitbox.y - handler.getGameCamera().getYOffset()), hitbox.width, hitbox.height);
    }

    @Override
    public void moveY() {
        if (yMove < 0) { // Moving up
            y += yMove;
        }
        if (yMove > 0) { // Moving down
            y += yMove;
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


    /*    @Override
    public boolean isSolid(){
    return true;
    }*/

}

