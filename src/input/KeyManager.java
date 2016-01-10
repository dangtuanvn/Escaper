/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Anthony Nguyen
 */

// http://pastie.org/5579314#22,38
public class KeyManager implements KeyListener {
    private boolean[] keys;
    public boolean up, down, left, right, reset, developer,
            release, pause;   
    
    public KeyManager(){
        keys = new boolean[600];
    }
    
    public void update(){
        up = isKeyPressed(KeyEvent.VK_W) || isKeyPressed(KeyEvent.VK_UP) || isKeyPressed(KeyEvent.VK_X);
        down = isKeyPressed(KeyEvent.VK_S) || isKeyPressed(KeyEvent.VK_DOWN);
        left = isKeyPressed(KeyEvent.VK_A) || isKeyPressed(KeyEvent.VK_LEFT);
        right = isKeyPressed(KeyEvent.VK_D) || isKeyPressed(KeyEvent.VK_RIGHT);            
        reset = isKeyPressed(KeyEvent.VK_R);
        developer = isKeyPressed(KeyEvent.VK_T);
        pause = isKeyPressed(KeyEvent.VK_ESCAPE);
        // System.out.println(isKeyPressed(KeyEvent.VK_W));
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        keys[ke.getKeyCode()] = true;
        // System.out.println("Pressed!");
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        keys[ke.getKeyCode()] = false;
        // System.out.println("Released!");
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {        
    }
    
    public boolean isKeyPressed(int k){
        return keys[k];
    }
}
