/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.dynamics;

import entities.Entity;
import gfx.Animation;
import gfx.Assets;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import audio.Sfx;
import tiles.Tile;
import wearefine.Handler;

/**
 *
 * @author Anthony Nguyen
 */

public class Player extends DynamicEntity {

    private static Player instance = null;    
    
    // Animations
    private static Animation animation_run_left, animation_run_right,
            animation_idle_left, animation_idle_right,
            animation_die_left, animation_die_right;
    private static boolean onGround = false, faceDirection = true, alive, godMode;    
    private static int player_width = 128, player_height = 128, godModeSeconds;
    private float respawnX, respawnY;           
    private Entity touchEntity = null;
    private Timer godTimer;    
    
    // Gravity variables (will be moved)    
    private long gravityLastTime, gravityTimer;
    private int gravity = 5, idUp = 1;
    
    // KeyInput states (will be moved)
    private boolean respawn = false, conversation = false, move = true;    
    
    // Used for teleport(), developer's key
    private float developerX = 3200, developerY = 50;       
    
    public Player(Handler handler, float x, float y) {
        super(handler, x, y, player_width, player_height);
        hitbox.x = 50;
        hitbox.y = 54;
        hitbox.width = 27;
        hitbox.height = 66;
        alive = true;
        godMode = false;
        // Animations        
        animation_run_left = new Animation(120, Assets.player_run_left);
        animation_run_right = new Animation(120, Assets.player_run_right);
        animation_idle_left = new Animation(120, Assets.player_idle_left);
        animation_idle_right = new Animation(120, Assets.player_idle_right);
        animation_die_left = new Animation(720, Assets.player_die_left);
        animation_die_right = new Animation(720, Assets.player_die_right);
    }

    @Override
    public void update() {
        onGround = checkOnGround();
        gravityCheck();
        faceDirection();
        
        // Animations                    
        /*if(!alive){
        animation_die_left.update();
        animation_die_right.update();
        }*/
        animation_run_left.update();
        animation_run_right.update();
        animation_idle_left.update();
        animation_idle_right.update();       

        // System.out.println(onGround);
        //System.out.println(x + "," + y);
        
        // Movement        
        getInput();

        move();
        handler.getGameCamera().centerOnEntity(this);
    }
    
    private void getInput() {
        // Developer key
        if (handler.getKeyManager().developer) {
            teleport();
        }

        // Respawn
        if (!alive) {
            if (handler.getKeyManager().reset && respawn == false) {
                try {
                    handler.getWorld().getEntityManager().respawnTrap();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                }
                respawn();
                respawn = true;
                handler.getWorld().resumeTimer();
            }
        }

        if (alive) {
            if (!handler.getKeyManager().reset) {
                respawn = false;
            }

            // Jump
            if ((handler.getKeyManager().up && onGround && idUp < 2)) {
                Sfx.JUMP.play();
                yMove -= 16;
                idUp++;                
            }
            if (!handler.getKeyManager().up) {
                idUp = 1;
            }
            
            /* if (handler.getKeyManager().down) {
             yMove = speed;
             }*/

            // Move left and right
            if (!handler.getKeyManager().left && xMove < 0) {
                xMove += 2;
            }
            if (!handler.getKeyManager().right && xMove > 0) {
                xMove -= 2;
            }
            if (!handler.getKeyManager().left && !handler.getKeyManager().right) {
                xMove = 0;
            }
            if (handler.getKeyManager().left) {
                xMove -= 1;
                if (xMove < -speed) {
                    xMove = -speed;
                }
            }
            if (handler.getKeyManager().right) {
                xMove += 1;
                if (xMove > speed) {
                    xMove = speed;
                }
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
        // Die animation
        if(!alive){
            if(faceDirection){
                return Assets.player_die_right[1];
            }
            else{
                return Assets.player_die_left[1];
            }
        }
        
        // Jump animation
        if (xMove < 0 && onGround) {
            return animation_run_left.getCurrentFrame();
        } else if (xMove > 0 && onGround) {
            return animation_run_right.getCurrentFrame();
        } 
        
        // Run and Idle animation
        else if (onGround){
            if (faceDirection) {
                return animation_idle_right.getCurrentFrame();
            } else {
                return animation_idle_left.getCurrentFrame();
            }
        }
        else{
            if (faceDirection && yMove < 0) {
                return Assets.player_jump_right;
            }
            else if (!faceDirection && yMove < 0){
                return Assets.player_jump_left;
            }
            else if (faceDirection && yMove > 0){
                return Assets.player_fall_right;
            }
            else{
                return Assets.player_fall_left;
            }
        }
    }

    @Override
    public void move() {
        // Check collision with entities;
        if (!checkEntityCollisions(xMove, 0f)) {
            moveX();                           
        } else if (checkEntityCollisions(xMove, 0f)) {
            if (!collideWithEntity(touchEntity)) {
                moveX();
            }
        }
        
        // Check collision with entities
        if (!checkEntityCollisions(0f, yMove)) {
            moveY();
        } else if (checkEntityCollisions(0f, yMove)) {
            if (!collideWithEntity(touchEntity)
                    && !collideWithEntity(touchEntity)) {
                moveY();
            }
        }
    }

    @Override
    public void moveX() {
        if (xMove > 0) { // Moving right
            int tx = (int) (x + xMove + hitbox.x + hitbox.width) / Tile.tileWidth;
            // Check collision with tiles
            if (!collideWithTile(tx, (int) (y + hitbox.y) / Tile.tileHeight)
                    && !collideWithTile(tx, (int) (y + hitbox.y + hitbox.height) / Tile.tileHeight)
                    && !collideWithTile(tx, (int) (y + hitbox.y + hitbox.height / 2) / Tile.tileHeight)) 
            // because the player's hitbox is bigger than the tile's, we need another check in the middle
            {
                x += xMove;
            } else {
                //x = tx * Tile.tileWidth; // cool phase through code, save for future use
                x = tx * Tile.tileWidth - hitbox.x - hitbox.width - 1;
            }
        } else if (xMove < 0) { // Moving left
            int tx = (int) (x + xMove + hitbox.x) / Tile.tileWidth;
            // Check collision with tiles
            if (!collideWithTile(tx, (int) (y + hitbox.y) / Tile.tileHeight)
                    && !collideWithTile(tx, (int) (y + hitbox.y + hitbox.height) / Tile.tileHeight)
                    && !collideWithTile(tx, (int) (y + hitbox.y + hitbox.height / 2) / Tile.tileHeight)) {
                x += xMove;
            } else {
                x = tx * Tile.tileWidth + Tile.tileWidth - hitbox.x;
            }
        }
    }

    @Override
    public void moveY() {
        if (yMove < 0) { // Moving up
            int ty = (int) (y + yMove + hitbox.y) / Tile.tileHeight;
            // Check collision with tiles
            if (!collideWithTile((int) (x + hitbox.x) / Tile.tileWidth, ty)
                    && !collideWithTile((int) (x + hitbox.x + hitbox.width) / Tile.tileWidth, ty)) {
                y += yMove;
            } else {
                y = ty * Tile.tileHeight + Tile.tileHeight - hitbox.y;
            }
        }
    }
    
    @Override
    public boolean checkEntityCollisions(float xOffset, float yOffset) {
        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this)) {
                continue;
            }
            if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
                touchEntity = e;
                return true;
            }
        }

        touchEntity = null;
        return false;
    }

    // Gravity force
    private void gravityPull() {
        // TODO: There is a minor bug with this timer, expect a patch soon
        if(!onGround){
        gravityTimer += System.currentTimeMillis() - gravityLastTime;
        gravityLastTime = System.currentTimeMillis();
        }
        
        if (gravityTimer > 150) {
            yMove += gravity;
            gravityTimer = 0;
        }
        // Limiting the pull force to avoid problems
        if (yMove > 30){
            yMove = 30;
        }   

        if (yMove > 0) { // Moving down
            int ty = (int) (y + yMove + hitbox.y + hitbox.height) / Tile.tileHeight;
            // Check collision with tiles
            if (!collideWithTile((int) (x + hitbox.x) / Tile.tileWidth, ty)
                    && !collideWithTile((int) (x + hitbox.x + hitbox.width) / Tile.tileWidth, ty)) {
                y += yMove;
            } else {
                y = ty * Tile.tileHeight - hitbox.y - hitbox.height - 1;
            }           
            // Check collision with entities
            if (checkEntityCollisions(0f, yMove - 1)) {
                if (collideWithEntity(touchEntity)) {
                    y = touchEntity.getY() + touchEntity.getHitboxY() - hitbox.y - hitbox.height;  
                }
            }
        }
    }
    
    // Check if gravity should be applied
      private void gravityCheck(){
        if (onGround) {
            if (yMove > 2 || yMove < 2) {
                yMove = 2;
                gravityTimer = 0;
                gravityLastTime = 0;
            }            
        }                
        else if(!onGround){
        gravityPull();}
    } 
    
    private boolean checkOnGround() {        
        int ty = (int) (y + yMove + hitbox.y + hitbox.height) / Tile.tileHeight;
        // Check collision with tiles
        if (collideWithTile((int) (x + hitbox.x) / Tile.tileWidth, ty)
                || collideWithTile((int) (x + hitbox.x + hitbox.width) / Tile.tileWidth, ty)) 
        {                   
            return true;
        }
        // Check collision with entities
        if (checkEntityCollisions(0f, yMove - 1)) {
            if (collideWithEntity(touchEntity)) {
                return true;
            }
        }
        return false;     
    }
    
    private void faceDirection() {
        if (xMove > 0) {
            faceDirection = true; // Character face right
        }
        if (xMove < 0) {
            faceDirection = false; // Character face left
        }
    }
    
    public void die(){
        if (!godMode) {
            if (alive) {
                Sfx.DEATH.play();
                alive = false;
                xMove = 0;
                handler.getWorld().stopTimer();
                handler.getWorld().deathCountIncrement();
            }
        }
    }
    
    public boolean getAlive(){
        return alive;
    }      
        
    // Create or get player method (singleton)
    public static Player getInstance(Handler handler, float x, float y){
        if(instance == null){
            instance =  new Player(handler, x, y);
        }
        return instance;
    }
    
    private void respawn(){
        x = respawnX;
        y = respawnY;
        alive = true;
    }   
    
    // Developer's cheat, will be removed in the final game
    private void teleport(){
        x = developerX;
        y = developerY;
    }        
    
    public void setRespawnX(float x){
        respawnX = x;
    }
    
    public void setRespawnY(float y){
        respawnY = y;
    }
    
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }    

    public Entity getTouchEntity() {
        return this.touchEntity;
    }
    
    public void setTouchEntity(Entity e) {
        this.touchEntity = e;
    }        
    
    public void activateGodMode(){
        godMode = true;
        godTimer = new Timer();
        godModeSeconds = 5;
        TimerTask task = new TimerTask()
        {
            public void run()
            {
                godModeSeconds--;
                if(godModeSeconds == 0)
                {
                    deactivateGodMode();                    
                }
            }
        };        
        godTimer.scheduleAtFixedRate(task, 1000, 1000);
    }
    
    public void deactivateGodMode(){                     
         godMode = false;
         godTimer.cancel();    
    } 
    
    public int getGodModeSeconds()
    {
        return godModeSeconds;
    }
    
    public void setAlive(boolean a){
        alive = a;
    }
}
