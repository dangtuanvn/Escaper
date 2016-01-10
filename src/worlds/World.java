/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worlds;

import Utilities.Utils;
import audio.Bgm;
import entities.EntityManager;
import entities.dynamics.Player;
import gfx.Assets;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;
import audio.Sfx;
import states.GameStateManager;
import tiles.Tile;
import wearefine.Handler;

/**
 *
 * @author Anthony Nguyen
 */
public class World {
    private Handler handler;    
    private int lvlNumber, width, height, playerSpawnX, playerSpawnY, coins, 
            deathCount, keys, maxKeys;
    private int[][] tileMap;
    private EntityManager entityManager;
    private Timer timer;
    private static int seconds;    
    private boolean pause = false, idPause = false, finish;
    private String path_entities;
    private final Font fnt0 = new Font("Comic Sans MS", Font.BOLD, 15);
    private final Font fnt1 = new Font("Comic Sans MS", Font.BOLD, 35);
    private final Font fnt2 = new Font("Comic Sans MS", Font.ITALIC, 17);
    private final Font fnt3 = new Font("Comic Sans MS", Font.BOLD, 23);
    private final Font fnt4 = new Font("Comic Sans MS", Font.BOLD, 35);
    private final Font fnt5 = new Font("Comic Sans MS", Font.BOLD, 20);
   
    boolean hoverRetryBtn = false;
    boolean hoverResumeBtn = false;
    boolean hoverSurBtn = false;
    
    private final String path_tiles_lv0 = "res/worlds/world0-tiles.txt",
            path_entities_lv0 = "res/worlds/world0-entities.txt",
            path_tiles_lv1 = "res/worlds/world1-tiles.txt",
            path_entities_lv1 = "res/worlds/world1-entities.txt",
            path_tiles_lv2 = "res/worlds/world2-tiles.txt",
            path_entities_lv2 = "res/worlds/world2-entities.txt";
    
    public World(Handler handler, int lvlNum) throws FileNotFoundException{
        this.handler = handler;
        entityManager = new EntityManager(handler, Player.getInstance(handler, 0, 0));
        
        coins = 0;
        deathCount = 0;
        finish = false;
        this.lvlNumber = lvlNum;
        Bgm.MENU.stop();
        Bgm.ICEY.stop();
        if(lvlNum == 0)
        {
            this.path_entities = path_entities_lv0;
            loadWorldTiles(path_tiles_lv0);
            entityManager.loadWorldEntities(path_entities);
            startTimer(999);
            Bgm.ICEY.loop();
        }
        else if(lvlNum == 1)
        {
            this.path_entities = path_entities_lv1;
            loadWorldTiles(path_tiles_lv1);
            entityManager.loadWorldEntities(path_entities);
            startTimer(180);
            Bgm.ICEY.loop();
        }
        else if(lvlNum == 2)
        {
            this.path_entities = path_entities_lv2;
            loadWorldTiles(path_tiles_lv2);
            entityManager.loadWorldEntities(path_entities);
            startTimer(360);
            Bgm.ICEY.loop();
        }
        else{
            this.handler.getGameStateManager().setState(this.handler.getGame().getSelectLevelState());
        }
        
        entityManager.getPlayer().setX(playerSpawnX);
        entityManager.getPlayer().setY(playerSpawnY);
        entityManager.getPlayer().setRespawnX(playerSpawnX);
        entityManager.getPlayer().setRespawnY(playerSpawnY);
        Collections.rotate(entityManager.getEntities(), -1); // Move the player to the last position of list for rendering purpose
    }
    
    public void update(){
        if(!pause){
            getInput();
            entityManager.update();            
        }
        else{
            getInput();
        }        
    }

    
    public void render(Graphics g){
        // How many screens the game render
        int xStart = (int) Math.max(0, handler.getGameCamera().getXOffset() / Tile.tileWidth);
        int xEnd = (int) Math.min(width, ((handler.getGameCamera().getXOffset() + handler.getWidth()) / Tile.tileWidth) * 1.5); // put + 1 for just 1 screen, now it is 1.5 screen
        int yStart = (int) Math.max(0, handler.getGameCamera().getYOffset() / Tile.tileHeight);
        int yEnd = (int) Math.min(height, ((handler.getGameCamera().getYOffset() + handler.getHeight()) / Tile.tileHeight) * 1.5); // same as above
        for(int y = yStart; y < yEnd; y++){
            for(int x = xStart; x < xEnd; x++){
                getTile(x, y).render(g,(int)(x * Tile.tileWidth - handler.getGameCamera().getXOffset()), (int)(y * Tile.tileHeight - handler.getGameCamera().getYOffset()));
            }
        }
        
        // Entities
        entityManager.render(g);       
        
        // Timer       
        g.setFont(fnt5);
        g.setColor(new Color(186,85,211));
        g.drawImage(Assets.deathIcon, 10, 10, null);
        g.drawImage(Assets.coinIcon, handler.getWidth() - 220, 10, null);
        g.drawImage(Assets.clockIcon, 120, 10, null);
        g.drawImage(Assets.keyIcon, handler.getWidth() - 110, 12, null);
        
        g.drawString(": " + deathCount, 35, 29);   
        g.drawString(": " + coins, handler.getWidth() - 195, 29);
        g.drawString(": " + String.format("%03d", seconds), 145, 29);
        g.drawString(": " + keys, handler.getWidth() - 85, 29);
        
        int godtime = handler.getWorld().getEntityManager().getPlayer().getGodModeSeconds();
                
        if(godtime > 0)
        {
            g.drawImage(Assets.godIcon, handler.getWidth() - 110, 50, null);
            g.drawString(": " + godtime, handler.getWidth() - 85, 69); 
        }
      
        g.setFont(fnt1);  
        g.setColor(new Color(107,142,35));
        g.drawString("LEVEL " + lvlNumber, handler.getWidth() / 2 - 55, 40); 
        
        g.setColor(Color.BLACK);

        // Tutorial
        if(lvlNumber == 0){
            g.setFont(fnt0);
            if(entityManager.getPlayer().getX() < 300){                
                g.drawString("Press WASD or arrow keys to move", 30, 500);       
            }
            if(entityManager.getPlayer().getX() > 440 && entityManager.getPlayer().getX() < 500){
                g.drawString("Press W or X or Up arrow key to jump", handler.getWidth() / 2 - 150, 500);       
            }
            
            if(entityManager.getPlayer().getX() > 740 && entityManager.getPlayer().getX() < 800){
                g.drawString("Avoid monsters and traps, collect yellow coins", handler.getWidth() / 2 - 150, 290);       
                g.drawString("Lighted mine will explode if stepped on", handler.getWidth() / 2 - 150, 320);       
                g.drawString("Cross the mine field when their lights are off", handler.getWidth() / 2 - 150, 350);       
            }
            
            if(entityManager.getPlayer().getX() > 1570 && entityManager.getPlayer().getX() < 1600){
                g.drawString("Green coins make you invulnerable to damage", handler.getWidth() / 2 - 150, 350);       
                g.drawString("Run straight to the goal and DO NOT look back", handler.getWidth() / 2 - 150, 380);       
            }                        
        }    
        
        // Respawn screen
        if(!entityManager.getPlayer().getAlive()){
            g.setFont(fnt1);
            g.drawString("Press 'R' to respawn", handler.getWidth() / 2 - 165, handler.getHeight() / 2 - 100);   
        }
        
        // Pause screen
        if(pause){
            Rectangle pauseMenu = new Rectangle(handler.getWidth() / 2 - 200, 250, 400, 200);
            Rectangle pauseMenuShadow = new Rectangle(handler.getWidth() / 2 - 185, 265, 400, 200);
            Rectangle surrenderBtn = new Rectangle(handler.getWidth() / 2 - 170, 360, 150, 40);
            Rectangle retryBtn = new Rectangle(handler.getWidth() / 2 + 20, 360, 150, 40);
            Rectangle resumeBtn = new Rectangle(handler.getWidth() / 2 + 120, 425, 78, 23);
                       
            
            Graphics2D g2d = (Graphics2D) g;
            
            g.setColor(new Color(185, 177, 156));
            g2d.fill(pauseMenuShadow);
                       
            g.setColor(new Color(255, 216, 109));
            g2d.fill(pauseMenu);
            g.setColor(new Color(102, 51, 0));
            g2d.setStroke(new BasicStroke(3));
            g2d.draw(pauseMenu);                        
            
            g.setFont(fnt4);
            g.drawString("GAME PAUSED", handler.getWidth() / 2 - 125, handler.getHeight() / 2 - 100);    
            
            g2d.setStroke(new BasicStroke(2));
            g.setFont(fnt3);
            
            if(hoverSurBtn)
            {
                g.setColor(new Color(198, 158, 65));
            }
            else
            {
                g.setColor(new Color(102, 51, 0));
            }
            
            g.drawString("Surrender", surrenderBtn.x + 15, surrenderBtn.y + 30);
            g2d.draw(surrenderBtn);
            
            if(hoverRetryBtn)
            {
                g.setColor(new Color(198, 158, 65));
            }
            else
            {
                g.setColor(new Color(102, 51, 0));
            }
            
            g.drawString("Retry", retryBtn.x + 45, retryBtn.y + 30);
            g2d.draw(retryBtn);
            
            if(hoverResumeBtn)
            {
                g.setColor(new Color(198, 158, 65));
            }
            else
            {
                g.setColor(new Color(102, 51, 0));
            }
            
            g.setFont(fnt2);
            g.drawString("Resume", resumeBtn.x + 10, resumeBtn.y + 18);
            g2d.draw(resumeBtn);
                          
        }
        
        
    }
    
    public Tile getTile(int x, int y){
        if(x < 0 || y < 0 || x >= width || y >= height){
            return Tile.underground2;
        }
        Tile t = Tile.tiles[tileMap[x][y]];
        if(t == null){
            return Tile.underground2;
        }
        return t;                     
    }
    
    private void loadWorldTiles(String path){
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        playerSpawnX = Utils.parseInt(tokens[2]);
        playerSpawnY = Utils.parseInt(tokens[3]);
        maxKeys = Utils.parseInt(tokens[4]);
        
        tileMap = new int[width][height];
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                tileMap[x][y] = Utils.parseInt(tokens[(x + y * width) + 5]);
            }
        }
    } 
    
    // This method can be improved, as of now, It is necessary to throw Exception whenever method is called
    
    
    public EntityManager getEntityManager(){
        return entityManager;
    }
    
    public int getPlayerSpawnX(){        
        return playerSpawnX;       
    }
    
    public int getPlayerSpawnY(){        
        return playerSpawnY;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }    
    
    public int getCoins()
    {
        return coins;
    }
    
    public void coinCollect()
    {
        coins++;
    }
    
    public int getSeconds()
    {
        return seconds;
    }
    
    public void stopTimer()
    {
        timer.cancel();
    }
    
    public void resumeTimer(){
        startTimer(seconds - 1);       
    }
          
    public void startTimer(int secs)
    {
        timer = new Timer();
        seconds = secs;
        TimerTask task = new TimerTask()
        {
            public void run()
            {
                seconds--;
                if(seconds == 0)
                {
                    gameOver();
                }
            }
        };
        
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }        
    
    public void deathCountIncrement(){
        deathCount++;
    }
    
    public int getDeathCount(){
        return deathCount;
    }
    
    public String getPathEntities(){
        return path_entities;
    }    
    
    private void getInput(){    
            // Pause key
        if (handler.getKeyManager().pause){
            if (!idPause) {
                if (pause) {
                    resumeGame();
                } else if (!pause) {           
                    pauseGame();
                }
            }
            idPause = true;
        }  
        if (!handler.getKeyManager().pause){
            idPause = false;
        }
    }
    
    public void finishWorld(){
        Bgm.SANDY.stop();
        Bgm.ICEY.stop();
        Sfx.WIN.play();
        finish = true;
        stopTimer();
    }
    
    public void gameOver(){
        Bgm.SANDY.stop();
        Bgm.ICEY.stop();
        Sfx.LOSE.play();
        stopTimer();
        GameStateManager.setState(handler.getGame().getScoreState());
    }
    
    public boolean getFinish(){
        return finish;
    }
    
    public int getLvlNumber()
    {
        return this.lvlNumber;
    }
    
    public void hoverSurBtn(boolean a)
    {
        hoverSurBtn = a;
    }
    
    public void hoverRetryBtn(boolean a)
    {
        hoverRetryBtn = a;
    }
    
    public void hoverResumeBtn(boolean a)
    {
        hoverResumeBtn = a;
    }
    
    public boolean isPaused()
    {
        return pause;
    }
    
    public void pauseGame()
    {
        this.pause = true;
        stopTimer();
    }
    
    public void resumeGame()
    {
        this.pause = false;
        resumeTimer();
    }
    
    public int getKeys()
    {
        return keys;
    }
    
    public int getMaxKeys()
    {
        return maxKeys;
    }
    
    public void collectKey()
    {
        keys++;
    }
}
