/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wearefine;

import states.GameState;
import states.MenuState;
import states.State;
import gfx.Assets;
import display.Display;
import gfx.GameCamera;
import gfx.ImageLoader;
import input.KeyManager;
import input.MouseManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import states.GameStateManager;
import states.ScoreState;
import states.SelectLevelState;

/**
 *
 * @author Anthony Nguyen
 */
public class Game implements Runnable{
    private Thread thread;
    private Display display;
    private String title;
    private int width, height;
    private boolean running = false;
    private BufferStrategy bs;
    private Graphics g;
    
    // States
    private State gameState, menuState, selectLevelState, scoreState;    
    
    // Input
    private KeyManager keyManager;
    private MouseManager mouseManager;
    
    // Camera
    private GameCamera gameCamera;
    
    // Handler
    private Handler handler;
    
    public Game(String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();      
        handler = new Handler(this);
        mouseManager = new MouseManager(handler);
        gameCamera = new GameCamera(handler, 0, 0);        
    }
    
    private void init() throws FileNotFoundException{
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();
        
        menuState = new MenuState(handler);
        scoreState = new ScoreState(handler);
        selectLevelState = new SelectLevelState(handler);
        GameStateManager.setState(menuState);
    }   
  
    
    private void update(){
              
        if(handler.getGameStateManager().getState() == gameState){
           keyManager.update();
           handler.getGameStateManager().getState().update();
        }
    }
           
    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();    
        // Clear screen
        g.clearRect(0, 0, width, height);
        
        // Start draw
        
        // Draw background

        // g.drawImage(Assets.background, (int) backgroundX - 1000, -150, null);
        
        // g.drawImage(Assets.background, (int) backgroundX + 1000, -150, null);
        // backgroundX-=5;
        // System.out.println(backgroundX);

        
        if(handler.getGameStateManager().getState() == gameState)
        {
           g.drawImage(Assets.background, 0, -150, null);
           handler.getGameStateManager().getState().render(g);
        }
        else if(handler.getGameStateManager().getState() == menuState)
        {   
            g.drawImage(Assets.background, 0, -150, null);
            handler.getGameStateManager().getState().render(g);
        }
        else if(handler.getGameStateManager().getState() == selectLevelState)
        {
            g.drawImage(Assets.background, 0, -150, null);
            handler.getGameStateManager().getState().render(g);
        }
        else if(handler.getGameStateManager().getState() == scoreState){
            g.drawImage(Assets.background, 0, -150, null);
            handler.getGameStateManager().getState().render(g); 
        }
        
       

        
        
        // Finish draw        
        bs.show();
        g.dispose();
    }
    
    public void run(){
        try {
            init();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        // in order to have the game running at same speed on any computer
        // the following code would limit the frame per second to 60
        int fps = 60; // how many times the update() will invoke every second
        double timePerTick = 1000000000 / fps; // 1 billion nanoseconds per second
        double delta = 0;
        long now;
        long lastTime = System.nanoTime(); // return the nanoseconds the computer is running
        long timer = 0;
        int ticks = 0;
        
        while(running){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;
            
            if(delta >= 1){
                update();
                render();
                delta--;
                ticks++;
            }
            
            // Check the fps and print it on the console        
            if(timer >= 1000000000){
                // System.out.println("FPS: " + ticks);
                ticks = 0;
                timer = 0;
            }
        } 
        stop();
    }
    
    public KeyManager getKeyManager(){
        return keyManager;
    }
    
    public GameCamera getGameCamera(){
        return gameCamera;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }

    public State getGameState() {
        return gameState;
    }
    
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public State getMenuState() {
        return menuState;
    }
    
    public State getScoreState() {
        return scoreState;
    }

    public State getSelectLevelState() {
        return selectLevelState;
    }   
    
    public synchronized void start(){
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    
    public synchronized void stop(){
        if(!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
