/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.statics;

import gfx.Assets;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import wearefine.Handler;

/**
 *
 * @author Anthony Nguyen
 */
public class dropPlatform extends StaticEntity {
    private static int objectWidth = 50;
    private static int objectHeight = 60;
    private boolean timerStart;
    private Timer dropTimer;
    private int dropSeconds;
    public dropPlatform(Handler handler, float x, float y) {
        super(handler, x, y, objectWidth, objectHeight);        
        hitbox.x = 0;
        hitbox.y = 42;
        hitbox.width = width;
        hitbox.height = height - 42;
        timerStart = false;
    }

    @Override
    public void update() {
        checkEntityCollisions(0f, -1f);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.dropPlatform, (int)(x - handler.getGameCamera().getXOffset()), (int)(y - handler.getGameCamera().getYOffset()), objectWidth, objectHeight, null);
        // Draw hitbox
        // g.setColor(Color.red);
        // g.fillRect((int) (x + hitbox.x - handler.getGameCamera().getXOffset()), (int) (y + hitbox.y - handler.getGameCamera().getYOffset()), hitbox.width, hitbox.height);
    }
    
    @Override
    public boolean checkEntityCollisions(float xOffset, float yOffset) {
        boolean check = super.checkEntityCollisions(xOffset, yOffset);
        if (check) {    
            // System.out.println(dropSeconds);
            if(!timerStart){
                activateTimer();
            }
        }
        return check;
    }
    
    @Override
    public boolean isSolid(){
        return true;
    }
    
    public void activateTimer(){
        timerStart = true;
        dropTimer = new Timer();
        dropSeconds = 3;
        TimerTask task = new TimerTask()
        {
            public void run()
            {
                dropSeconds--;
                if(dropSeconds == 0)
                {
                    remove();             
                }
            }
        };        
        dropTimer.scheduleAtFixedRate(task, 1000, 1000);
    }
}