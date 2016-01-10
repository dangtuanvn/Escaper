/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wearefine;

import Utilities.Utils;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author s3500286
 */
public class Profile 
{
    private final String path = "res/worlds/profile.txt";
    private int[] hs = new int[3];// highscore
    public Profile()
    {
        loadScore();
    }              
    
    public void loadScore(){
        String file = Utils.loadFileAsString(path);    
        String[] tokens = file.split("\\s+");
        hs[0] = Utils.parseInt(tokens[0]);
        hs[1] = Utils.parseInt(tokens[1]);
        hs[2] = Utils.parseInt(tokens[2]);
    }
    
    public void setScore(int lvl, int score) throws IOException{
        hs[lvl] = score;
        
        FileWriter fw = new FileWriter("res/worlds/profile.txt");
 
	for (int i = 0; i < hs.length; i++) {
		fw.write(Integer.toString(hs[i]) + "\n");
	}
	fw.close();
        
        loadScore();
    }
    
    public int getHighScore(int lvl)
    {
        return hs[lvl];
    }
}
