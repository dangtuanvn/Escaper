/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.statics;

import gfx.Animation;
import gfx.Assets;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import audio.Sfx;
import wearefine.Handler;

/**
 *
 * @author Anthony Nguyen
 */
public class Mine extends StaticEntity {
    private static int objectWidth = 70; //55
    private static int objectHeight = 50; //50
    private int renderWidth;
    private int renderHeight;
    private Animation mine_animation;
    private boolean activate = false, blowup = false;
    private int deactivateTime, activateTime;
    private long timer, lastTime;
    
    public Mine(Handler handler, float x, float y, int activateTime, int deactivateTime) {
        super(handler, x, y, objectWidth, objectHeight);        
        renderWidth = objectWidth;
        renderHeight = objectHeight;
        hitbox.x = -2;
        hitbox.y = 10;
        hitbox.width = 74;
        hitbox.height = 40;
        this.activateTime = activateTime;
        this.deactivateTime = deactivateTime;
        mine_animation = new Animation(120, Assets.mine_explode);        
    }

    @Override
    public void update() {      
        if(activateTime != 0){
            changeState();
        }        
        if(blowup){
            mine_animation.update();
        }        
        if(blowup && mine_animation.getIndex() == 6){
            handler.getWorld().getEntityManager().getEntities().remove(this);
        }
        checkEntityCollisions(0f , 0f );
    }

    @Override
    public void render(Graphics g) {
        if(!blowup){
            if(!activate){
                g.drawImage(Assets.mine0, (int)(x - handler.getGameCamera().getXOffset()), (int)(y - handler.getGameCamera().getYOffset()), renderWidth, renderHeight, null);
            }
            if(activate){
                g.drawImage(Assets.mine1, (int)(x - handler.getGameCamera().getXOffset()), (int)(y - handler.getGameCamera().getYOffset()), renderWidth, renderHeight, null);
            }
        }
        else{
            g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getXOffset()), (int)(y - handler.getGameCamera().getYOffset()), renderWidth, renderHeight, null);
        }
        // Draw hitbox
        // g.setColor(Color.red);
        // g.fillRect((int) (x + hitbox.x - handler.getGameCamera().getXOffset()), (int) (y + hitbox.y - handler.getGameCamera().getYOffset()), hitbox.width, hitbox.height);
    }
    
    @Override
    public boolean checkEntityCollisions(float xOffset, float yOffset)
    {
        boolean check = super.checkEntityCollisions(xOffset, yOffset);
        if (check) {
            if (activate) {
                if (!blowup) {
                    Sfx.EXPLODE.play();
                    blowup = true;
                    renderWidth = 125;
                    renderHeight = 100;
                    x -= 30;
                    y -= 50;
                    handler.getWorld().getEntityManager().getPlayer().die();
                }
            }
        }
        return check;
    }
        
    private BufferedImage getCurrentAnimationFrame() {            
            return mine_animation.getCurrentFrame();                
    }    
    
    private void changeState() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        if (activate && timer > activateTime) {
            activate = false;
            timer = 0;
        }
        if (!activate && timer > deactivateTime) {
            activate = true;
            timer = 0;
        }
    }
}
