/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.dynamics;
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
public class Golem extends DynamicEntity {
    private Animation golem_walk_right, golem_walk_left;
    private int patrolDistance;
    private final float startX;
    private float destinationLeft, destinationRight;        
    private boolean faceDirection;
    
    public Golem(Handler handler, float x, float y, int patrolDistance) {
        super(handler, x, y, DynamicEntity.default_creature_width * 5, DynamicEntity.default_creature_height * 4);
        this.patrolDistance = patrolDistance;
        startX = x;
        destinationRight = startX + patrolDistance;
        destinationLeft = startX - patrolDistance;
        health = default_health;
        speed = default_speed;        
        speed = 2;
        hitbox.x = 20;
        hitbox.y = 15;
        hitbox.width = width - 40;
        hitbox.height = height - 55;
        golem_walk_right = new Animation(60, Assets.golem_walk_right);
        golem_walk_left = new Animation(60, Assets.golem_walk_left);
    }

    @Override
    public void update() {
        faceDirection(handler.getWorld().getEntityManager().getPlayer().getX());
        golem_walk_right.update();
        golem_walk_left.update();
        if(patrolDistance != 0){
            patrol();
        }
        move();
    }
    
    public void patrol(){  
        /*if(x > destinationRight){
        faceDirection = false;
        } else if(x < destinationLeft){
        faceDirection = true;
        }*/
        
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
            return golem_walk_right.getCurrentFrame();
        }
        else{
            return golem_walk_left.getCurrentFrame();
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
