/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wearefine;

/**
 *
 * @author Anthony Nguyen
 */
public class Launcher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game game = new Game("We Are Fine", 1000, 800);
        game.start(); // call the run() method to start the thread
    }
    
}
