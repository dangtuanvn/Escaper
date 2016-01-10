/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gfx;

import entities.Entity;
import tiles.Tile;
import wearefine.Handler;

/**
 *
 * @author Anthony Nguyen
 */
public class GameCamera {
    private Handler handler;
    private float xOffset, yOffset;
    public GameCamera(Handler handler, float xOffset, float yOffset){
        this.handler = handler;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
    
    
    public void checkBlankSpace(){
        if(xOffset < 0){
            xOffset = 0;
        }
        else if(xOffset > handler.getWorld().getWidth() * Tile.tileWidth - handler.getWidth()){
            xOffset = handler.getWorld().getWidth() * Tile.tileWidth - handler.getWidth();
        }
        if(yOffset < 0){
            yOffset = 0;
        }
        else if(yOffset > handler.getWorld().getHeight() * Tile.tileHeight - handler.getHeight()){
            yOffset = handler.getWorld().getHeight() * Tile.tileHeight - handler.getHeight();
        }
    }
    
    public void centerOnEntity(Entity e){
        float tempXOffset = xOffset;
        xOffset = e.getX() - handler.getWidth() / 2 + e.getWidth() / 2;
        yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;
        checkBlankSpace();
        // handler.getGame().setBackgroundX(handler.getGame().getBackgroundX() - (xOffset - tempXOffset));
    }
    
    public void move(float xAmount, float yAmount){
        xOffset += xAmount;
        yOffset += yAmount;
        // checkBlankSpace(); This cause the screen to slighly lag/slutter
    }
    
    public float getXOffset(){
        return xOffset;
    }
    
    public void setXOffset(float xOffset){
        this.xOffset = xOffset;
    }
    
    public float getYOffset(){
        return yOffset;
    }    
    
    public void setYOffset(float yOffset){
        this.yOffset = yOffset;
    }
}
