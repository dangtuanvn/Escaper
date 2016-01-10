/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import audio.Bgm;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import wearefine.Handler;

/**
 *
 * @author Anthony Nguyen
 */
public class ScoreState extends State {
    private int score;
    public Rectangle selectLvlBtn = new Rectangle(handler.getWidth() / 2 - 170, 400, 150, 40);
    public Rectangle nextLvlBtn = new Rectangle(handler.getWidth() / 2 + 20, 400, 150, 40);
    public Rectangle retryBtn = new Rectangle(handler.getWidth() / 2 + 20, 400, 150, 40);
    
    private boolean hoverSelectlvlBtn = false;
    private boolean hoverNextLvlBtn = false; 
    private boolean hoverRetryBtn = false; 
    
    
    public ScoreState(Handler handler){
        super(handler);        
    }

    @Override
    public void update() {        
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Font fnt0 = new Font("Comic Sans MS", Font.BOLD, 40);
        Font fnt1 = new Font("Comic Sans MS", Font.BOLD, 30); 
        Font fnt2 = new Font("Comic Sans MS", Font.BOLD, 23); 
        
        if(handler.getWorld().getFinish()){
            g.setFont(fnt0);
            g.setColor(Color.DARK_GRAY);
            g.drawString("YOUR SCORE", handler.getWidth() / 2 - 320, handler.getHeight() / 2 - 150);
            g.drawString("HIGH SCORE", handler.getWidth() / 2 + 60, handler.getHeight() / 2 - 150);
            
            g.setFont(fnt1);
            g.drawString(String.format("%04d", score), handler.getWidth() / 2 - 230, handler.getHeight() / 2 - 100);
            g.drawString(String.format("%04d", handler.getProfile().getHighScore(handler.getWorld().getLvlNumber())), 
                    handler.getWidth() / 2 + 160, handler.getHeight() / 2 - 100); 
            
            g.setFont(fnt2);
            if (hoverSelectlvlBtn) {
                g.setColor(Color.LIGHT_GRAY);
            } else {
                g.setColor(Color.GRAY);
            }
            g.drawString("Select Level", selectLvlBtn.x + 8, selectLvlBtn.y + 30);
            g2d.draw(selectLvlBtn);
            
            if (hoverNextLvlBtn) {
                g.setColor(Color.LIGHT_GRAY);
            } else {
                g.setColor(Color.GRAY);
            }
            g.drawString("Next Level", nextLvlBtn.x + 17, nextLvlBtn.y + 30);
            g2d.draw(nextLvlBtn);
        }
        else{     
            g.setFont(fnt0);
            g.setColor(Color.DARK_GRAY);
            g.drawString("GAME OVER", handler.getWidth() / 2 - 120, handler.getHeight() / 2 - 100);
            
            g.setFont(fnt2);
            if (hoverSelectlvlBtn) {
                g.setColor(Color.LIGHT_GRAY);
            } else {
                g.setColor(Color.GRAY);
            }
            g.drawString("Select Level", selectLvlBtn.x + 8, selectLvlBtn.y + 30);
            g2d.draw(selectLvlBtn);
            
            if (hoverRetryBtn) {
                g.setColor(Color.LIGHT_GRAY);
            } else {
                g.setColor(Color.GRAY);
            }
            g.drawString("Retry", nextLvlBtn.x + 45, nextLvlBtn.y + 30);
            g2d.draw(retryBtn);
        }            
    }
    
    public void setScore() throws IOException{        
        int bonusScore;
        if(handler.getWorld().getDeathCount() == 0){
            bonusScore = 1000;
        }
        else if(handler.getWorld().getDeathCount() < 5){
            bonusScore = 700;
        }
        else if(handler.getWorld().getDeathCount() < 10){
            bonusScore = 300;
        }
        else {
            bonusScore = 0;
        }
        score = handler.getWorld().getCoins() * 100 + handler.getWorld().getSeconds() + bonusScore;        
        
        if(score > handler.getProfile().getHighScore(handler.getWorld().getLvlNumber()))
        {
            handler.getProfile().setScore(handler.getWorld().getLvlNumber(), score);
        }
    }
    
    public void hoverSelectlvlBtn(boolean b)
    {
        hoverSelectlvlBtn = b;
    }
    
    public void hoverNextLvlBtn(boolean b)
    {
        hoverNextLvlBtn = b;
    }
    
    public void hoverRetryBtn(boolean b)
    {
        hoverRetryBtn = b;
    }
}
