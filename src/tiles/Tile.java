/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Anthony Nguyen
 */
public class Tile {
    // Static variables
    public static Tile[] tiles = new Tile[256];
    public static Tile blank = new BlankTile(0);
    public static Tile die = new DieTile(98);
    public static Tile invi = new InviTile(99);
    public static Tile sand1 = new Sand1(1);
    public static Tile sand2 = new Sand2(2);
    public static Tile sand3 = new Sand3(3);
    public static Tile float1 = new Float1(4);
    public static Tile float2 = new Float2(5);
    public static Tile float3 = new Float3(6);
    public static Tile float4 = new Float4(7);
    public static Tile float5 = new Float5(8);
    public static Tile float6 = new Float6(9);
    public static Tile underground1 = new Underground1(10);
    public static Tile underground2 = new Underground2(11);
    public static Tile underground3 = new Underground3(12);
    public static Tile underground4 = new Underground4(13);
    public static Tile underground5 = new Underground5(14);
    public static Tile side1 = new Side1(15);
    public static Tile side2 = new Side2(16);
       
// Tile variables
    public static final int tileWidth = 40, tileHeight = 40;
    protected BufferedImage texture;
    protected final int id;
    
    public Tile(BufferedImage texture, int id){
        this.texture = texture;
        this.id = id;        
        tiles[id] = this;
    }
    
    public void update(){
        
    }
    
    public boolean isSolid(){
        return false;
    }
    
    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, tileWidth, tileHeight, null);
    }
    
    public int getId(){
        return id;
    }        
}
