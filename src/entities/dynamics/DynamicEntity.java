/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.dynamics;

import entities.Entity;
import tiles.Tile;
import wearefine.Handler;

/**
 *
 * @author Anthony Nguyen
 */
public abstract class DynamicEntity extends Entity {
    
    public static int default_health  = 1;
    public static float default_speed = 5.0f;
    public static final int default_creature_width = 48,
                            default_creature_height = 48;             
    
    protected int health;
    protected float speed , xMove, yMove;           
    
    public DynamicEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        health = default_health;
        speed = default_speed;
        xMove = 0;
        yMove = 0;
    }
    
    public void move(){
        if(!checkEntityCollisions(xMove, 0f)){
            moveX();
        }
        if(!checkEntityCollisions(0f, yMove)){
            moveY();
        }
    }
    
    public void moveX(){
        if(xMove > 0){ // Moving right
            int tx = (int) (x + xMove + hitbox.x + hitbox.width) / Tile.tileWidth;
            if(!collideWithTile(tx, (int) (y + hitbox.y) / Tile.tileHeight) && 
                    !collideWithTile(tx, (int) (y + hitbox.y + hitbox.height) / Tile.tileHeight) &&
                    !collideWithTile(tx, (int) (y + hitbox.y + hitbox.height / 2) / Tile.tileHeight))
                // because the player's hitbox is bigger than the tile's, we need another check in the middle
            {
                x += xMove;
            }
            else{
                // x = tx * Tile.tileWidth; // cool phase through code, save for future use
                x = tx * Tile.tileWidth - hitbox.x - hitbox.width - 1; 
            }
        }
        
        else if(xMove < 0){ // Moving left
            int tx = (int) (x + xMove + hitbox.x) / Tile.tileWidth;
            if(!collideWithTile(tx, (int) (y + hitbox.y) / Tile.tileHeight) && 
                    !collideWithTile(tx, (int) (y + hitbox.y + hitbox.height) / Tile.tileHeight) &&
                    !collideWithTile(tx, (int) (y + hitbox.y + hitbox.height / 2) / Tile.tileHeight))
            {
                x += xMove;
            }
            else{
                x = tx * Tile.tileWidth + Tile.tileWidth - hitbox.x;
            }
        }
    }
    
    public void moveY(){
        if(yMove < 0){ // Moving up
            int ty = (int) (y + yMove + hitbox.y) / Tile.tileHeight;
            if(!collideWithTile((int) (x + hitbox.x) / Tile.tileWidth, ty) &&
                   !collideWithTile((int) (x + hitbox.x + hitbox.width) / Tile.tileWidth, ty))
            {
                y += yMove;
            }
            else{
                y = ty * Tile.tileHeight + Tile.tileHeight - hitbox.y;
            }
        }
        
        if(yMove > 0){ // Moving down
            int ty = (int) (y + yMove + hitbox.y + hitbox.height) / Tile.tileHeight;
            if(!collideWithTile((int) (x + hitbox.x) / Tile.tileWidth, ty) &&
                   !collideWithTile((int) (x + hitbox.x + hitbox.width) / Tile.tileWidth, ty))
            {
                y += yMove;
            }
            else{
                y = ty * Tile.tileHeight - hitbox.y - hitbox.height - 1;
            }           
        }
    }
    
    protected boolean collideWithTile(int x, int y){
        return handler.getWorld().getTile(x, y).isSolid();
    }
    
protected boolean collideWithEntity(Entity e){     
        if(e != null)
        {
            return e.isSolid();
        }
        else
        {
            return false;
        }
    }

    
    // Accessors and Mutators 
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }  
    
    public float getXMove() {
        return xMove;
    }

    public void setXMove(float xMove) {
        this.xMove = xMove;
    }
    
    public float getYMove() {
        return yMove;
    }

    public void setYMove(float yMove) {
        this.yMove = yMove;
    }    
    
    
}
