package wearefine;

import gfx.GameCamera;
import input.KeyManager;
import states.GameStateManager;
import worlds.World;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Anthony Nguyen
 */
public class Handler {
    private Game game;
    private World world;
    private GameStateManager gameStateManager;
    private Profile profile;
    
    public Handler(Game game){
        this.game = game;
        gameStateManager = new GameStateManager(this);
        profile = new Profile();
    }
    
    public Profile getProfile()
    {
        return profile;
    }
    
    public GameCamera getGameCamera(){
        return game.getGameCamera();
    }
    
    public KeyManager getKeyManager(){
        return game.getKeyManager();
    }
    
    public int getWidth(){
        return game.getWidth();
    }
    
    public int getHeight(){
        return game.getHeight();
    }
    
    public Game getGame(){
        return game;
    }
    
    public void setGame(Game game){
        this.game = game;
    }
    
    public World getWorld(){
        return world;
    }
    
    public void setWorld(World world){
        this.world = world;
    }
    
    public GameStateManager getGameStateManager(){
        return gameStateManager;
    }
}
