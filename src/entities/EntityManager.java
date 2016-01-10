    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import static Utilities.Utils.parseInt;
import entities.dynamics.Boulder;
import entities.dynamics.EnergyBall;
import entities.dynamics.Fireball;
import entities.dynamics.Flybie;
import entities.dynamics.Golem;
import entities.dynamics.Graybie;
import entities.dynamics.Plantie;
import entities.dynamics.Player;
import entities.statics.Bush1;
import entities.statics.Bush2;
import entities.statics.Cactus1;
import entities.statics.Cactus2;
import entities.statics.Cactus3;
import entities.statics.Coin;
import entities.statics.Crate;
import entities.statics.Door;
import entities.statics.Grass1;
import entities.statics.Grass2;
import entities.statics.Key;
import entities.statics.Mine;
import entities.statics.Powerup;
import entities.statics.Sign1;
import entities.statics.Sign2;
import entities.statics.Sign3;
import entities.statics.Skeleton1;
import entities.statics.Skeleton2;
import entities.statics.Spike1;
import entities.statics.Spike2;
import entities.statics.Spike3;
import entities.statics.Spike4;
import entities.statics.Stone;
import entities.statics.Stoneblock;
import entities.statics.Torch;
import entities.statics.Tree1;
import entities.statics.Tree2;
import entities.statics.dropPlatform;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import wearefine.Handler;

/**
 *
 * @author Anthony Nguyen
 */
public class EntityManager {
    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities;
    /*    private Comparator<Entity> renderSorter = new Comparator<Entity>(){
    @Override
    public int compare(Entity a, Entity b) {
    if(a.getY() + a.getHeight() < b.getY() + b.getHeight()){
    return -1;
    }
    else{
    return 1;
    }
    }
    };*/
    
    public EntityManager(Handler handler, Player player){
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<>();
        addEntity(player);
    }
    
    public void update(){
        for(int i = 0; i < entities.size(); i++){
            Entity e = entities.get(i);
            e.update();
        }              
        // Collections.sort(entities, renderSorter);
    }
    
    public void render(Graphics g){
        for(Entity e: entities){
            e.render(g);
        }
    }
    
    public void addEntity(Entity e){
        entities.add(e);
    }
    
    public Handler getHandler(){
        return handler;
    }
    
    public void setHandler(Handler handler){
        this.handler = handler;
    }
    
    public Player getPlayer(){
        return player;
    }
    
    public void setPlayer(Player player){
        this.player = player;
    }
    
    public ArrayList<Entity> getEntities(){
        return entities;
    }
    
    public void setEntities(ArrayList<Entity> entities){
        this.entities = entities;
    }    
    
    public void loadWorldEntities(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner sc = new Scanner(file);
        
        while(sc.hasNextLine()){               
            String line = sc.nextLine();
            String[] values = line.split("\\s+");
                  
            /* This is a list of entity's IDs, please add the new IDs to this list
            Static IDs 0 - 100, Dynamic IDs 100 - 200
            0 = Coin
            1 = Tree1
            2 = Tree2
            3 = Bush1
            4 = Bush2
            5 = Grass1
            6 = Grass2
            7 = Cactus1
            8 = Cactus2
            9 = Cactus3
            10 = Key
            11 = Crate
            12 = Stone
            13 = Stoneblock
            14 = Sign1
            15 = Sign2
            16 = Sign3
            17 = Skeleton1
            18 = Skeleton2
            39 = Torch (checkpoint)
            77 = Powerup
            90 = dropPlatform
            95 = Spike4
            96 = Spike3
            97 = Spike2
            98 = Spike1
            99 = Mine
            100 = Golem
            101 = Fireball
            102 = Boulder
            103 = Energyball (goal)
            104 = Graybie
            105 = Plantie
            106 = Flybie
            */
            
            if(values.length == 3){
                int id = parseInt(values[0]);
                int spawnX = parseInt(values[1]);
                int spawnY = parseInt(values[2]);            

                if(id == 0){
                    addEntity(new Coin(handler, spawnX, spawnY));
                }
                else if(id == 1){
                    addEntity(new Tree1(handler, spawnX, spawnY));
                }
                else if(id == 2){
                    addEntity(new Tree2(handler, spawnX, spawnY));
                }
                else if(id == 3){
                    addEntity(new Bush1(handler, spawnX, spawnY));
                }
                else if(id == 4){
                    addEntity(new Bush2(handler, spawnX, spawnY));
                }
                else if(id == 5){
                    addEntity(new Grass1(handler, spawnX, spawnY));
                }
                else if(id == 6){
                    addEntity(new Grass2(handler, spawnX, spawnY));
                }
                else if(id == 7){
                    addEntity(new Cactus1(handler, spawnX, spawnY));
                }
                else if(id == 8){
                    addEntity(new Cactus2(handler, spawnX, spawnY));
                }
                else if(id == 9){
                    addEntity(new Cactus3(handler, spawnX, spawnY));
                }
                else if(id == 10){
                    addEntity(new Key(handler, spawnX, spawnY));
                }
                else if(id == 11){
                    addEntity(new Crate(handler, spawnX, spawnY));
                }
                else if(id == 12){
                    addEntity(new Stone(handler, spawnX, spawnY));
                }
                else if(id == 13){
                    addEntity(new Stoneblock(handler, spawnX, spawnY));
                }
                else if(id == 14){
                    addEntity(new Sign1(handler, spawnX, spawnY));
                }
                else if(id == 15){
                    addEntity(new Sign2(handler, spawnX, spawnY));
                }
                else if(id == 16){
                    addEntity(new Sign3(handler, spawnX, spawnY));
                }
                else if(id == 17){
                    addEntity(new Skeleton1(handler, spawnX, spawnY));
                }
                else if(id == 18){
                    addEntity(new Skeleton2(handler, spawnX, spawnY));
                }                                                
                else if(id == 39){
                    addEntity(new Torch(handler, spawnX, spawnY));
                }
                else if (id == 77){
                    addEntity(new Powerup(handler, spawnX, spawnY));
                }
                else if (id == 90){
                    addEntity(new dropPlatform(handler, spawnX, spawnY));
                }
                else if(id == 98){
                    addEntity(new Spike1(handler, spawnX, spawnY));
                }
                else if(id == 97){
                    addEntity(new Spike2(handler, spawnX, spawnY));
                }
                else if(id == 96){
                    addEntity(new Spike3(handler, spawnX, spawnY));
                }
                else if(id == 95){
                    addEntity(new Spike4(handler, spawnX, spawnY));
                }
                else if(id == 105){
                    addEntity(new Plantie(handler, spawnX, spawnY));
                }
                else{                    
                }
            }
            
            else if(values.length == 4){
                int id = parseInt(values[0]);
                int spawnX = parseInt(values[1]);
                int spawnY = parseInt(values[2]);            
                int patrolDistance = parseInt(values[3]);
                
                if(id == 100){
                    addEntity(new Golem(handler, spawnX, spawnY, patrolDistance));
                }                
                if(id == 101){
                    addEntity(new Fireball(handler, spawnX, spawnY, patrolDistance));
                }
                if(id == 102){
                    addEntity(new Boulder(handler, spawnX, spawnY, patrolDistance));
                }
                if(id == 103){
                    addEntity(new EnergyBall(handler, spawnX, spawnY, patrolDistance));
                }
                if(id == 104){
                    addEntity(new Graybie(handler, spawnX, spawnY, patrolDistance));
                }
                if(id == 106){
                    addEntity(new Flybie(handler, spawnX, spawnY, patrolDistance));
                }
                else{                   
                }
            }
            else if(values.length == 5){
                int id = parseInt(values[0]);
                int spawnX = parseInt(values[1]);
                int spawnY = parseInt(values[2]);            
                int activateTimer = parseInt(values[3]);
                int deactivateTimer = parseInt(values[4]);
                if(id == 99){
                    addEntity(new Mine(handler, spawnX, spawnY, activateTimer, deactivateTimer));
                }
            }
            else{
                
            }
        }        
        sc.close();
    }
    
    public void removeTraps(){
        for(int i = 0; i<entities.size(); i++){        
            if(entities.get(i) instanceof  Mine || entities.get(i) instanceof Boulder
                    || entities.get(i) instanceof Powerup || entities.get(i) instanceof dropPlatform){
                entities.get(i).remove();
                i--;        
            }
        }                                    
    }
    
    public void respawnTrap() throws FileNotFoundException{    
        removeTraps();
        File file = new File(handler.getWorld().getPathEntities());
        Scanner sc = new Scanner(file);        
        while(sc.hasNextLine()){               
            String line = sc.nextLine();
            String[] values = line.split("\\s+");
            if(values.length == 3){
                int id = parseInt(values[0]);
                int spawnX = parseInt(values[1]);
                int spawnY = parseInt(values[2]);    
                if(id == 77){
                    addEntity(new Powerup(handler, spawnX, spawnY));
                }
                if(id == 90){
                    addEntity(new dropPlatform(handler, spawnX, spawnY));
                }
            }
            if(values.length == 4){
                int id = parseInt(values[0]);
                int spawnX = parseInt(values[1]);
                int spawnY = parseInt(values[2]);            
                int patrolDistance = parseInt(values[3]);                                
                if(id == 102){
                    addEntity(new Boulder(handler, spawnX, spawnY, patrolDistance));
                }                         
                else{                   
                }
            }
            else if(values.length == 5){
                int id = parseInt(values[0]);
                int spawnX = parseInt(values[1]);
                int spawnY = parseInt(values[2]);            
                int activateTimer = parseInt(values[3]);
                int deactivateTimer = parseInt(values[4]);
                if(id == 99){
                    addEntity(new Mine(handler, spawnX, spawnY, activateTimer, deactivateTimer));
                }
            }
            else{                
            }
        }        
        sc.close();                        
        
        // Get the player's index and move it to last position in array for rendering purpose
        int playerIndex = entities.indexOf(player);       
            Collections.swap(entities, playerIndex, entities.size() - 1);                    
    }
}
