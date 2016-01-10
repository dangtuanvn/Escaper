/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import wearefine.Handler;

/**
 *
 * @author Anthony Nguyen
 */
public class SelectLevelState extends State {

    public Rectangle lv0Btn = new Rectangle(handler.getWidth() / 2 - 80, 250, 150, 40);
    public Rectangle lv1Btn = new Rectangle(handler.getWidth() / 2 - 80, 310, 150, 40);
    public Rectangle lv2Btn = new Rectangle(handler.getWidth() / 2 - 80, 370, 150, 40);
    public Rectangle backBtn = new Rectangle(handler.getWidth() / 2 - 80, 430, 150, 40);
    
    private boolean hoverLV0Btn = false;
    private boolean hoverLV1Btn = false;
    private boolean hoverLV2Btn = false;
    private boolean hoverBackBtn = false;
    
    public SelectLevelState(Handler handler){
        super(handler);
    }
    
    @Override
    public void update() {
       
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        Font fnt0 = new Font("Comic Sans MS", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.DARK_GRAY);
        g.drawString("Escaper", handler.getWidth() / 2 - 100, 150);
        
        Font fnt1 = new Font("Comic Sans MS", Font.BOLD, 30);
        g.setFont(fnt1);
        g.setColor(Color.DARK_GRAY);
        g.drawString("Select Level", handler.getWidth() / 2 - 90, lv0Btn.y - 50);
        
        Font fnt2 = new Font("Comic Sans MS", Font.BOLD, 25); 
        Font fnt3 = new Font("Comic Sans MS", Font.BOLD, 20); 
        g.setFont(fnt3);
        
        if(handler.getProfile().getHighScore(0) == 0)
        {
            g.drawString("Finish level 0 to unlock", lv1Btn.x + 185, lv1Btn.y + 28);
        }
        
        if(handler.getProfile().getHighScore(1) == 0)
        {
            g.drawString("Finish level 1 to unlock", lv2Btn.x + 185, lv2Btn.y + 28);        
        }
        
        if(hoverLV0Btn)
        {
            g.setColor(Color.LIGHT_GRAY);
        }
        else
        {
            g.setColor(Color.GRAY);
        }
        g.setFont(fnt2);
        g.drawString("Level 0", lv0Btn.x + 35, lv0Btn.y + 30);
        g2d.draw(lv0Btn);
        
        if(hoverLV1Btn || handler.getProfile().getHighScore(0) == 0)
        {
            g.setColor(Color.LIGHT_GRAY);
        }
        else
        {
            g.setColor(Color.GRAY);
        }
        
        g.drawString("Level 1", lv1Btn.x + 35, lv1Btn.y + 30);
        g2d.draw(lv1Btn); 
        
        if(hoverLV2Btn || handler.getProfile().getHighScore(1) == 0)
        {
            g.setColor(Color.LIGHT_GRAY);
        }
        else
        {
            g.setColor(Color.GRAY);
        }
        g.drawString("Level 2", lv2Btn.x + 35, lv2Btn.y + 30);
        g2d.draw(lv2Btn); 
        
        if(hoverBackBtn)
        {
            g.setColor(Color.LIGHT_GRAY);
        }
        else
        {
            g.setColor(Color.GRAY);
        }
        g.drawString("Back", backBtn.x + 45, backBtn.y + 30);
        g2d.draw(backBtn);
    }
    
    public void hoverLV1Btn(boolean b)
    {
        hoverLV1Btn = b;
    }
    
    public void hoverLV0Btn(boolean b)
    {
        hoverLV0Btn = b;
    }
    
    public void hoverBackBtn(boolean b)
    {
        hoverBackBtn = b;
    }
    
    public void hoverLV2Btn(boolean b)
    {
        hoverLV2Btn = b;
    }
    
}
