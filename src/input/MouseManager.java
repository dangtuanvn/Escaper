/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package input;

import audio.Bgm;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import states.GameState;
import states.GameStateManager;
import states.MenuState;
import states.ScoreState;
import states.SelectLevelState;
import wearefine.Handler;
import worlds.World;

/**
 *
 * @author USER
 */
public class MouseManager implements MouseListener, MouseMotionListener {

    Handler handler;

    public MouseManager(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.mouseReleased(e);
        int mx = e.getX();
        int my = e.getY();
        try
        {
        //GameState
        if (GameStateManager.getState() == handler.getGame().getGameState()) {
            //Pause Screen
            if (handler.getWorld().isPaused()) {
                //SurrenderBtn
                if (mx >= handler.getWidth() / 2 - 170 && mx <= handler.getWidth() / 2 - 20
                        && (my >= 360 && my <= 400)) {
                    GameStateManager.setState(handler.getGame().getScoreState());
                } 
                //RetryBtn
                if (mx >= handler.getWidth() / 2 + 20 && mx <= handler.getWidth() / 2 + 170
                        && (my >= 360 && my <= 400)) {
                    try {
                        World world_temp = new World(handler, handler.getWorld().getLvlNumber());
                        handler.setWorld(world_temp);
                        ((GameState)(handler.getGame().getGameState())).changeWorld(world_temp);
                        handler.getWorld().getEntityManager().getPlayer().setAlive(true);
                    }
                    catch (FileNotFoundException ex) {
                        Logger.getLogger(MouseManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                 //Resume
                if (mx >= handler.getWidth() / 2 + 120 && mx <= handler.getWidth() / 2 + 198
                        && (my >= 425 && my <= 448)) {
                    handler.getWorld().resumeGame();
                }
            }
        }
        //MenuState
        if (GameStateManager.getState() == handler.getGame().getMenuState()) {           
            //PlayBtn
            if (mx >= handler.getWidth() / 2 - 80 && mx <= handler.getWidth() / 2 + 70 && (my >= 250 && my <= 290)) {
                GameStateManager.setState(handler.getGame().getSelectLevelState());
            }

            //SettingBtn
            if (mx >= handler.getWidth() / 2 - 80 && mx <= handler.getWidth() / 2 + 70 && (my >= 310 && my <= 350)) {

            }

            //QuitBtn
            if (mx >= handler.getWidth() / 2 - 80 && mx <= handler.getWidth() / 2 + 70 && (my >= 370 && my <= 410)) {
                System.exit(0);
            }
        } else if (GameStateManager.getState() == handler.getGame().getSelectLevelState()) {
           
            //Level0Btn
            if (mx >= handler.getWidth() / 2 - 80 && mx <= handler.getWidth() / 2 + 70 && (my >= 250 && my <= 290)) {
                try {
                    handler.getGame().setGameState(new GameState(handler, 0));
                    GameStateManager.setState(handler.getGame().getGameState());
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MouseManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            //Level1Btn
            if (mx >= handler.getWidth() / 2 - 80 && mx <= handler.getWidth() / 2 + 70 
                    && (my >= 310 && my <= 350 && handler.getProfile().getHighScore(0) != 0)) {
                try {
                    handler.getGame().setGameState(new GameState(handler, 1));
                    GameStateManager.setState(handler.getGame().getGameState());
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MouseManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            

            //Level2Btn
            if (mx >= handler.getWidth() / 2 - 80 && mx <= handler.getWidth() / 2 + 70 
                    && (my >= 370 && my <= 410 && handler.getProfile().getHighScore(1) != 0)) {
                try {
                    handler.getGame().setGameState(new GameState(handler, 2));
                    GameStateManager.setState(handler.getGame().getGameState());
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MouseManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            
            //BackBtn
            if (mx >= handler.getWidth() / 2 - 80 && mx <= handler.getWidth() / 2 + 70 && (my >= 430 && my <= 470)) {
                GameStateManager.setState(handler.getGame().getMenuState());
            }
        }
        
        //ScoreState
        else if(GameStateManager.getState() == handler.getGame().getScoreState())
        {            
            //SelectLevelBtn
            if (mx >= handler.getWidth() / 2 - 170 && mx <= handler.getWidth() / 2 - 20 && (my >= 400 && my <= 440)) 
            {
                GameStateManager.setState(handler.getGame().getSelectLevelState());
                Bgm.ICEY.stop();
                Bgm.MENU.loop();
            }    
            
            //NextLvlBtn
            if (mx >= handler.getWidth() / 2 + 20 && mx <= handler.getWidth() / 2 + 170
                    && (my >= 400 && my <= 440) && handler.getWorld().getFinish()) {
                try {                   
                    World world_temp = new World(handler, handler.getWorld().getLvlNumber()+1);
                    handler.setWorld(world_temp);
                    ((GameState)(handler.getGame().getGameState())).changeWorld(world_temp);
                    GameStateManager.setState(handler.getGame().getGameState());
                    
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MouseManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            //RetryBtn
            else if (mx >= handler.getWidth() / 2 + 20 && mx <= handler.getWidth() / 2 + 170
                    && (my >= 400 && my <= 440) && !handler.getWorld().getFinish()) {               
                try {                  
                    World world_temp = new World(handler, handler.getWorld().getLvlNumber());
                    handler.setWorld(world_temp);
                    ((GameState)(handler.getGame().getGameState())).changeWorld(world_temp);  
                    GameStateManager.setState(handler.getGame().getGameState());
                } catch (FileNotFoundException ex) {
                        Logger.getLogger(MouseManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        catch(NullPointerException ex)
        {           
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        try {
            int mx = e.getX();
            int my = e.getY();

            //MenuState
            if (GameStateManager.getState() == handler.getGame().getMenuState()) {

                //PlayBtn
                if (mx >= handler.getWidth() / 2 - 80 && mx <= handler.getWidth() / 2 + 70 && (my >= 250 && my <= 290)) {
                    ((MenuState) (handler.getGame().getMenuState())).hoverPlayBtn(true);
                } //SettingBtn
                else if (mx >= handler.getWidth() / 2 - 80 && mx <= handler.getWidth() / 2 + 70 && (my >= 310 && my <= 350)) {
                    ((MenuState) (handler.getGame().getMenuState())).hoverSettingBtn(true);
                } //QuitBtn
                else if (mx >= handler.getWidth() / 2 - 80 && mx <= handler.getWidth() / 2 + 70 && (my >= 370 && my <= 410)) {
                    ((MenuState) (handler.getGame().getMenuState())).hoverQuitBtn(true);
                } else {
                    ((MenuState) (handler.getGame().getMenuState())).hoverPlayBtn(false);
                    ((MenuState) (handler.getGame().getMenuState())).hoverSettingBtn(false);
                    ((MenuState) (handler.getGame().getMenuState())).hoverQuitBtn(false);
                }

            }

            //SelectLevelState
            if (GameStateManager.getState() == handler.getGame().getSelectLevelState()) {

                //Level0Btn
                if (mx >= handler.getWidth() / 2 - 80 && mx <= handler.getWidth() / 2 + 70 && (my >= 250 && my <= 290)) {
                    ((SelectLevelState) (handler.getGame().getSelectLevelState())).hoverLV0Btn(true);
                }
                //Level1Btn
                else if (mx >= handler.getWidth() / 2 - 80 && mx <= handler.getWidth() / 2 + 70 && (my >= 310 && my <= 350)) {
                    ((SelectLevelState) (handler.getGame().getSelectLevelState())).hoverLV1Btn(true);
                } //Level2Btn
                else if (mx >= handler.getWidth() / 2 - 80 && mx <= handler.getWidth() / 2 + 70 && (my >= 370 && my <= 410)) {
                    ((SelectLevelState) (handler.getGame().getSelectLevelState())).hoverLV2Btn(true);
                } //BackBtn
                else if (mx >= handler.getWidth() / 2 - 80 && mx <= handler.getWidth() / 2 + 70 && (my >= 430 && my <= 470)) {
                    ((SelectLevelState) (handler.getGame().getSelectLevelState())).hoverBackBtn(true);
                } 
                else {
                    ((SelectLevelState) (handler.getGame().getSelectLevelState())).hoverLV0Btn(false);
                    ((SelectLevelState) (handler.getGame().getSelectLevelState())).hoverLV1Btn(false);
                    ((SelectLevelState) (handler.getGame().getSelectLevelState())).hoverLV2Btn(false);
                    ((SelectLevelState) (handler.getGame().getSelectLevelState())).hoverBackBtn(false);
                }
            }
            
            //ScoreState           
            if (GameStateManager.getState() == handler.getGame().getScoreState()) {               
                //SelectLevelBtn
                if (mx >= handler.getWidth() / 2 - 170 && mx <= handler.getWidth() / 2 - 20 && (my >= 400 && my <= 440)) {
                    ((ScoreState) (handler.getGame().getScoreState())).hoverSelectlvlBtn(true);
                }
                //NextLevelBtn
                else if (mx >= handler.getWidth() / 2 + 20 && mx <= handler.getWidth() / 2 + 170
                        && (my >= 400 && my <= 440) && handler.getWorld().getFinish()) {
                    ((ScoreState) (handler.getGame().getScoreState())).hoverNextLvlBtn(true);
                }   
                //RetryBtn     
                else if (mx >= handler.getWidth() / 2 + 20 && mx <= handler.getWidth() / 2 + 170
                        && (my >= 400 && my <= 440) && !handler.getWorld().getFinish()) {
                    ((ScoreState) (handler.getGame().getScoreState())).hoverRetryBtn(true);
                }  
                else {
                    ((ScoreState) (handler.getGame().getScoreState())).hoverSelectlvlBtn(false);
                    ((ScoreState) (handler.getGame().getScoreState())).hoverRetryBtn(false);
                    ((ScoreState) (handler.getGame().getScoreState())).hoverNextLvlBtn(false);
                }
            }
            
            //GameState 
            //Pause Screen
            if (GameStateManager.getState() == handler.getGame().getGameState()
                    && handler.getWorld().isPaused()) {                        
   
                //SurrenderBtn
                if (mx >= handler.getWidth() / 2 - 170 && mx <= handler.getWidth() / 2 - 20 
                        && (my >= 360 && my <= 400)) {
                    handler.getWorld().hoverSurBtn(true);
                }
                //RetryBtn
                else if (mx >= handler.getWidth() / 2 + 20 && mx <= handler.getWidth() / 2 + 170
                        && (my >= 360 && my <= 400)) {
                    handler.getWorld().hoverRetryBtn(true);
                }   
                //Resume    
                else if (mx >= handler.getWidth() / 2 + 120 && mx <= handler.getWidth() / 2 + 198
                        && (my >= 425 && my <= 448)) {
                    handler.getWorld().hoverResumeBtn(true);
                }  
                else {
                    handler.getWorld().hoverSurBtn(false);
                    handler.getWorld().hoverRetryBtn(false);
                    handler.getWorld().hoverResumeBtn(false);
                    
                }
            }
        } catch (NullPointerException ex) {

        }
    }
}
