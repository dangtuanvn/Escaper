/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import wearefine.Handler;

/**
 *
 * @author Anthony Nguyen
 */
public class GameStateManager {
    private Handler handler;
    private static State currentState = null;
    
    public GameStateManager(Handler handler){
        this.handler = handler;
    }
    
    public static void setState(State state){
        currentState = state;
    }
    
    public static State getState(){
        return currentState;
    }
}
