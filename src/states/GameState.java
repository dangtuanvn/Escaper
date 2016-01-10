/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.io.FileNotFoundException;
import wearefine.Handler;
import worlds.World;

/**
 *
 * @author Anthony Nguyen
 */
public class GameState extends State {
    private World world;
    
    public GameState(Handler handler, int worldNum) throws FileNotFoundException{
        super(handler); 
            world = new World(handler, worldNum);  
            handler.setWorld(world);                        
    }
    
    @Override
    public void update(){
        world.update();
    }
    
    @Override
    public void render(Graphics g){
        world.render(g);                
    }   
    
    public void changeWorld(World a)
    {
        this.world = a;
        handler.setWorld(a);
    }
}
