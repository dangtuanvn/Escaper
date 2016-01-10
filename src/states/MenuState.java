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
import wearefine.Handler;

/**
 *
 * @author Anthony Nguyen
 */
public class MenuState extends State {
    
    public Rectangle playBtn = new Rectangle(handler.getWidth() / 2 - 80, 250, 150, 40);
    public Rectangle settingBtn = new Rectangle(handler.getWidth() / 2 - 80, 310, 150, 40);
    public Rectangle quitBtn = new Rectangle(handler.getWidth() / 2 - 80, 370, 150, 40);
    
    private boolean hoverPlayBtn = false;
    private boolean hoverSettingBtn = false;
    private boolean hoverQuitBtn = false;
    
    
    public MenuState(Handler handler){
        super(handler);
        Bgm.MENU.loop();
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
        
        Font fnt1 = new Font("Comic Sans MS", Font.BOLD, 25); 
        g.setFont(fnt1);
        
        if(hoverPlayBtn)
        {
            g.setColor(Color.LIGHT_GRAY);
        }
        else
        {
            g.setColor(Color.GRAY);
        }
        
        g.drawString("Play", playBtn.x + 50, playBtn.y + 30);
        g2d.draw(playBtn);
        
        if(hoverSettingBtn)
        {
            g.setColor(Color.LIGHT_GRAY);
        }
        else
        {
            g.setColor(Color.GRAY);
        }
  
        g.drawString("Setting", settingBtn.x + 40, settingBtn.y + 30);
        g2d.draw(settingBtn);
        
        if(hoverQuitBtn)
        {
            g.setColor(Color.LIGHT_GRAY);
        }
        else
        {
            g.setColor(Color.GRAY);
        }
        g.drawString("Quit", quitBtn.x + 50, quitBtn.y + 30);
        g2d.draw(quitBtn);
    }
    
    public void hoverPlayBtn(boolean b)
    {
        hoverPlayBtn = b;
    }
    
    public void hoverSettingBtn(boolean b)
    {
        hoverSettingBtn = b;
    }
    
    public void hoverQuitBtn(boolean b)
    {
        hoverQuitBtn = b;
    }
}

