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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import states.ScoreState;
import wearefine.Handler;

/**
 *
 * @author Anthony Nguyen
 */
public class EnergyBall extends DynamicEntity {
    private int patrolDistance;
    private final float startY;
    private float destinationUp, destinationDown;       
    private boolean faceDirection;
    
    public EnergyBall(Handler handler, float x, float y, int patrolDistance) {
        super(handler, x, y, DynamicEntity.default_creature_width * 3, DynamicEntity.default_creature_height * 3);
        this.patrolDistance = patrolDistance;
        startY = y;
        destinationUp = startY - patrolDistance;
        destinationDown = startY + patrolDistance;
        health = default_health;
        speed = default_speed;        
        speed = 1;
        hitbox.x = 5;
        hitbox.y = 5;
        hitbox.width = width - 10;
        hitbox.height = height - 10;
    }

    @Override
    public void update() {       
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
            yMove -= 0.01;
            if(yMove < -speed){
                yMove = -speed;
            }
        }        
        else{
            yMove += 0.01;
            if(yMove > speed){
                yMove = speed;
            }
        }        
    }    
    
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.energyball, (int) (x - handler.getGameCamera().getXOffset()), (int) (y - handler.getGameCamera().getYOffset()), width, height, null);
        // Draw hitbox
        // g.setColor(Color.red);
        // g.fillRect((int) (x + hitbox.x - handler.getGameCamera().getXOffset()), (int) (y + hitbox.y - handler.getGameCamera().getYOffset()), hitbox.width, hitbox.height);
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
    
    @Override
    public boolean checkEntityCollisions(float xOffset, float yOffset) {
        boolean check = super.checkEntityCollisions(xOffset, yOffset);
        if (check && handler.getWorld().getKeys() == handler.getWorld().getMaxKeys()) {  
            handler.getWorld().finishWorld();
            try {
                ((ScoreState)handler.getGame().getScoreState()).setScore();
                handler.getGameStateManager().setState(handler.getGame().getScoreState());
            } catch (IOException ex) {
                Logger.getLogger(EnergyBall.class.getName()).log(Level.SEVERE, null, ex);
            }
            handler.getGameStateManager().setState(handler.getGame().getScoreState());
        }
        return check;
    }
}
