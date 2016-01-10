/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.dynamics.Player;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import wearefine.Handler;

/**
 *
 * @author Anthony Nguyen
 */
public abstract class Entity {
    protected float x, y;    
    protected int width, height;
    protected Handler handler;
    protected Rectangle hitbox;
    
    public Entity(Handler handler, float x, float y, int width, int height){
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;        
        
        hitbox = new Rectangle(0, 0, width, height);
    }
    
    public abstract void update();
 
    public abstract void render(Graphics g);

    public boolean checkEntityCollisions(float xOffset, float yOffset) {
        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {            
            if (e instanceof Player) {
                if (e.equals(this)) {
                    continue;
                }
                if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public Rectangle getCollisionBounds(float xOffset, float yOffset){     
        return new Rectangle((int)(x + hitbox.x + xOffset), (int)(y + hitbox.y + yOffset), hitbox.width, hitbox.height);
    }
    
    public boolean isSolid()
    {
        return false;
    }
    
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public int getHitboxY(){
        return hitbox.y;
    }
    
    public void remove(){        
        handler.getWorld().getEntityManager().getEntities().remove(this);            
    }
}
