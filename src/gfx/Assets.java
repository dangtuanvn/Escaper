/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gfx;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 *
 * @author Anthony Nguyen
 */
public class Assets {            
    public static BufferedImage[] player_idle_left, player_idle_right,  
            player_run_right, player_run_left, player_die_left, player_die_right,
            coin, powerup, door, torch, fireballDown, fireballUp,
            golem_walk_right, golem_walk_left, mine_explode,
            graybie_left, graybie_right, plantie_left, plantie_right,
            flybie_left, flybie_right;
    
    public static BufferedImage player_jump_left, player_jump_right,             
            player_fall_left, player_fall_right,
            background, menuBG, blank, invi,
            sand1, sand2, sand3, side1, side2,
            underground1, underground2, underground3,underground4, underground5,
            float1, float2, float3, float4, float5, float6,            
            bush1, bush2, cactus1, cactus2, cactus3, tree1, tree2, crate,
            grass1, grass2, sign1, sign2, sign3, torch_unlit,
            skeleton1, skeleton2, stone, stoneblock,
            mine0, mine1, boulder, energyball, dropPlatform, key,
            spike1, spike2, spike3, spike4, 
            clockIcon, deathIcon, coinIcon, godIcon, keyIcon;
                
        
    // Load everything only once
    public static void init(){
        // Sheets
        // SpriteSheet platformSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Tiles/platform.png"));        
        // SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/player.png"));
        SpriteSheet coinSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Object/coin.jpg"));        
        SpriteSheet torchSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Object/animated_torch.png"));
        SpriteSheet flybieSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Enemies/creature_sprites.png"));
        
        // Player animations               
        player_jump_right = ImageLoader.loadImage("/textures/Characters/Dummy/obj_JumpHigh000.png");
        player_jump_left = ImageLoader.loadImage("/textures/Characters/Dummy/obj_JumpHigh100.png");
        player_fall_right = ImageLoader.loadImage("/textures/Characters/Dummy/obj_Jump000.png");
        player_fall_left = ImageLoader.loadImage("/textures/Characters/Dummy/obj_Jump100.png");
        
        player_run_right = new BufferedImage[6];
        player_run_right[0] = ImageLoader.loadImage("/textures/Characters/Dummy/obj_Run000.png");
        player_run_right[1] = ImageLoader.loadImage("/textures/Characters/Dummy/obj_Run001.png");
        player_run_right[2] = ImageLoader.loadImage("/textures/Characters/Dummy/obj_Run002.png");
        player_run_right[3] = ImageLoader.loadImage("/textures/Characters/Dummy/obj_Run003.png");
        player_run_right[4] = ImageLoader.loadImage("/textures/Characters/Dummy/obj_Run004.png");
        player_run_right[5] = ImageLoader.loadImage("/textures/Characters/Dummy/obj_Run005.png");
        
        player_run_left = new BufferedImage[6];
        player_run_left[0] = ImageLoader.loadImage("/textures/Characters/Dummy/obj_Run100.png");
        player_run_left[1] = ImageLoader.loadImage("/textures/Characters/Dummy/obj_Run101.png");
        player_run_left[2] = ImageLoader.loadImage("/textures/Characters/Dummy/obj_Run102.png");
        player_run_left[3] = ImageLoader.loadImage("/textures/Characters/Dummy/obj_Run103.png");
        player_run_left[4] = ImageLoader.loadImage("/textures/Characters/Dummy/obj_Run104.png");
        player_run_left[5] = ImageLoader.loadImage("/textures/Characters/Dummy/obj_Run105.png");
        
        player_idle_right = new BufferedImage[6];
        player_idle_right[0] = ImageLoader.loadImage("/textures/Characters/Dummy/obj_Idle000.png");
        player_idle_right[1] = ImageLoader.loadImage("/textures/Characters/Dummy/obj_Idle001.png");
        player_idle_right[2] = ImageLoader.loadImage("/textures/Characters/Dummy/obj_Idle002.png");
        player_idle_right[3] = ImageLoader.loadImage("/textures/Characters/Dummy/obj_Idle003.png");
        player_idle_right[4] = ImageLoader.loadImage("/textures/Characters/Dummy/obj_Idle002.png");
        player_idle_right[5] = ImageLoader.loadImage("/textures/Characters/Dummy/obj_Idle001.png");
        
        player_idle_left = new BufferedImage[6];
        player_idle_left[0] = ImageLoader.loadImage("/textures/Characters/Dummy/obj_Idle100.png");
        player_idle_left[1] = ImageLoader.loadImage("/textures/Characters/Dummy/obj_Idle101.png");
        player_idle_left[2] = ImageLoader.loadImage("/textures/Characters/Dummy/obj_Idle102.png");
        player_idle_left[3] = ImageLoader.loadImage("/textures/Characters/Dummy/obj_Idle103.png");
        player_idle_left[4] = ImageLoader.loadImage("/textures/Characters/Dummy/obj_Idle102.png");
        player_idle_left[5] = ImageLoader.loadImage("/textures/Characters/Dummy/obj_Idle101.png");
        
        player_die_right = new BufferedImage[2];
        player_die_right[0] = ImageLoader.loadImage("/textures/Characters/Dummy/obj_Flying001.png");
        player_die_right[1] = ImageLoader.loadImage("/textures/Characters/Dummy/obj_Flying000.png");
       
        player_die_left = new BufferedImage[2];
        player_die_left[0] = ImageLoader.loadImage("/textures/Characters/Dummy/obj_Flying101.png");
        player_die_left[1] = ImageLoader.loadImage("/textures/Characters/Dummy/obj_Flying100.png");
        
        // Tiles
        background = ImageLoader.loadImage("/textures/BG.png");
        menuBG = ImageLoader.loadImage("/textures/BG2.png");
        sand1 = ImageLoader.loadImage("/textures/Tiles/1.png");
        sand2 = ImageLoader.loadImage("/textures/Tiles/2.png");
        sand3 = ImageLoader.loadImage("/textures/Tiles/3.png");
        float1 = ImageLoader.loadImage("/textures/Tiles/4.png");
        float2 = ImageLoader.loadImage("/textures/Tiles/5.png");
        float3 = ImageLoader.loadImage("/textures/Tiles/6.png");
        float4 = ImageLoader.loadImage("/textures/Tiles/7.png");
        float5 = ImageLoader.loadImage("/textures/Tiles/8.png");
        float6 = ImageLoader.loadImage("/textures/Tiles/9.png");
        underground1 = ImageLoader.loadImage("/textures/Tiles/10.png");  
        underground2 = ImageLoader.loadImage("/textures/Tiles/11.png");  
        underground3 = ImageLoader.loadImage("/textures/Tiles/12.png");  
        underground4 = ImageLoader.loadImage("/textures/Tiles/13.png");  
        underground5 = ImageLoader.loadImage("/textures/Tiles/14.png");  
        side1 = ImageLoader.loadImage("/textures/Tiles/15.png");  
        side2 = ImageLoader.loadImage("/textures/Tiles/16.png");  
        
        // Objects
        coin = new BufferedImage[10];
        coin[0] = coinSheet.crop(0, 0, 100, 100);
        coin[1] = coinSheet.crop(100, 0, 100, 100);
        coin[2] = coinSheet.crop(100 * 2, 0, 100, 100);
        coin[3] = coinSheet.crop(100 * 3, 0, 100, 100);
        coin[4] = coinSheet.crop(100 * 4, 0, 100, 100);
        coin[5] = coinSheet.crop(100 * 5, 0, 100, 100);
        coin[6] = coinSheet.crop(100 * 6, 0, 100, 100);
        coin[7] = coinSheet.crop(100 * 7, 0, 100, 100);
        coin[8] = coinSheet.crop(100 * 8, 0, 100, 100);
        coin[9] = coinSheet.crop(100 * 9, 0, 100, 100);
        
        powerup = new BufferedImage[10];
        powerup[0] = ImageLoader.loadImage("/textures/Object/Power Ups/frame-1.png");
        powerup[1] = ImageLoader.loadImage("/textures/Object/Power Ups/frame-2.png");
        powerup[2] = ImageLoader.loadImage("/textures/Object/Power Ups/frame-3.png");
        powerup[3] = ImageLoader.loadImage("/textures/Object/Power Ups/frame-4.png");
        powerup[4] = ImageLoader.loadImage("/textures/Object/Power Ups/frame-5.png");
        powerup[5] = ImageLoader.loadImage("/textures/Object/Power Ups/frame-6.png");
        powerup[6] = ImageLoader.loadImage("/textures/Object/Power Ups/frame-7.png");
        powerup[7] = ImageLoader.loadImage("/textures/Object/Power Ups/frame-8.png");
        powerup[8] = ImageLoader.loadImage("/textures/Object/Power Ups/frame-9.png");
        powerup[9] = ImageLoader.loadImage("/textures/Object/Power Ups/frame-10.png");        
          
       
        torch = new BufferedImage[9];
        torch[0] = torchSheet.crop(0, 0, 32, 64);
        torch[1] = torchSheet.crop(32, 0, 32, 64);
        torch[2] = torchSheet.crop(32 * 2, 0, 32, 64);
        torch[3] = torchSheet.crop(32 * 3, 0, 32, 64);
        torch[4] = torchSheet.crop(32 * 4, 0, 32, 64);
        torch[5] = torchSheet.crop(32 * 5, 0, 32, 64);
        torch[6] = torchSheet.crop(32 * 6, 0, 32, 64);
        torch[7] = torchSheet.crop(32 * 7, 0, 32, 64);
        torch[8] = torchSheet.crop(32 * 8, 0, 32, 64);
        torch_unlit = torchSheet.crop(32 * 9, 0, 32, 64);
        
        dropPlatform = ImageLoader.loadImage("/textures/Object/bridge.png");
        tree1 = ImageLoader.loadImage("/textures/Object/Tree.png");
        tree2 = horFlip(tree1);
        bush1 = ImageLoader.loadImage("/textures/Object/Bush (1).png");
        bush2 = ImageLoader.loadImage("/textures/Object/Bush (2).png");
        cactus1 = ImageLoader.loadImage("/textures/Object/Cactus (1).png");
        cactus2 = ImageLoader.loadImage("/textures/Object/Cactus (2).png");
        cactus3 = ImageLoader.loadImage("/textures/Object/Cactus (3).png");
        crate = ImageLoader.loadImage("/textures/Object/Crate.png");
        grass1 = ImageLoader.loadImage("/textures/Object/Grass (1).png");
        grass2 = ImageLoader.loadImage("/textures/Object/Grass (2).png");
        sign1 = ImageLoader.loadImage("/textures/Object/Sign.png");
        sign2 = ImageLoader.loadImage("/textures/Object/SignArrow.png");
        sign3 = horFlip(sign2);        
        skeleton1 = ImageLoader.loadImage("/textures/Object/Skeleton.png");
        skeleton2 = horFlip(skeleton1);
        stone = ImageLoader.loadImage("/textures/Object/Stone.png");
        stoneblock = ImageLoader.loadImage("/textures/Object/StoneBlock.png"); 
        mine0 = ImageLoader.loadImage("/textures/Object/mine/mine_2.png");
        mine1 = ImageLoader.loadImage("/textures/Object/mine/mine_1.png");
        energyball = ImageLoader.loadImage("/textures/Object/energyball.png");
        boulder = ImageLoader.loadImage("/textures/Object/boulder.png");
        spike1 = ImageLoader.loadImage("/textures/Object/Spikes/Spike A.png");
        spike2 = ImageLoader.loadImage("/textures/Object/Spikes/Spike B.png");
        spike3 = ImageLoader.loadImage("/textures/Object/Spikes/Spike C.png");
        spike4 = ImageLoader.loadImage("/textures/Object/Spikes/Spike D.png");
        clockIcon = ImageLoader.loadImage("/textures/clockIcon.png");
        deathIcon = ImageLoader.loadImage("/textures/deathIcon.png");
        coinIcon = ImageLoader.loadImage("/textures/coinIcon.png");
        godIcon = ImageLoader.loadImage("/textures/godIcon.png");
        keyIcon = ImageLoader.loadImage("/textures/keyIcon.png");
        key = ImageLoader.loadImage("/textures/Object/key.png");
        
        // Creatures
        golem_walk_left = new BufferedImage[6];
        golem_walk_left[0] = ImageLoader.loadImage("/textures/Enemies/Golem/idle-walk/idle_1.png");
        golem_walk_left[1] = ImageLoader.loadImage("/textures/Enemies/Golem/idle-walk/idle_2.png");
        golem_walk_left[2] = ImageLoader.loadImage("/textures/Enemies/Golem/idle-walk/idle_3.png");
        golem_walk_left[3] = ImageLoader.loadImage("/textures/Enemies/Golem/idle-walk/idle_4.png");
        golem_walk_left[4] = ImageLoader.loadImage("/textures/Enemies/Golem/idle-walk/idle_5.png");
        golem_walk_left[5] = ImageLoader.loadImage("/textures/Enemies/Golem/idle-walk/idle_6.png");
        
        golem_walk_right = new BufferedImage[6];
        golem_walk_right[0] = horFlip(golem_walk_left[0]);
        golem_walk_right[1] = horFlip(golem_walk_left[1]);
        golem_walk_right[2] = horFlip(golem_walk_left[2]);
        golem_walk_right[3] = horFlip(golem_walk_left[3]);
        golem_walk_right[4] = horFlip(golem_walk_left[4]);
        golem_walk_right[5] = horFlip(golem_walk_left[5]);
        
        mine_explode = new BufferedImage[7];
        mine_explode[0] = ImageLoader.loadImage("/textures/Object/mine/explosion_1.png");
        mine_explode[1] = ImageLoader.loadImage("/textures/Object/mine/explosion_2.png");
        mine_explode[2] = ImageLoader.loadImage("/textures/Object/mine/explosion_3.png");
        mine_explode[3] = ImageLoader.loadImage("/textures/Object/mine/explosion_4.png");
        mine_explode[4] = ImageLoader.loadImage("/textures/Object/mine/explosion_5.png");
        mine_explode[5] = ImageLoader.loadImage("/textures/Object/mine/explosion_6.png");
        mine_explode[6] = ImageLoader.loadImage("/textures/Object/mine/explosion_7.png");     
        
        graybie_left = new BufferedImage[2];
        graybie_left[0] = ImageLoader.loadImage("/textures/Enemies/Graybie/frame-1.png");
        graybie_left[1] = ImageLoader.loadImage("/textures/Enemies/Graybie/frame-2.png");
        
        graybie_right = new BufferedImage[2];
        graybie_right[0] = horFlip(graybie_left[0]);
        graybie_right[1] = horFlip(graybie_left[1]);
        
        plantie_left = new BufferedImage[2];
        plantie_left[0] = ImageLoader.loadImage("/textures/Enemies/Plantie/frame-1.png");
        plantie_left[1] = ImageLoader.loadImage("/textures/Enemies/Plantie/frame-2.png");
        
        plantie_right = new BufferedImage[2];
        plantie_right[0] = horFlip(plantie_left[0]);
        plantie_right[1] = horFlip(plantie_left[1]);
        
        flybie_left = new BufferedImage[4];
        flybie_left[0] = flybieSheet.crop(0, 64, 64, 64);
        flybie_left[1] = flybieSheet.crop(64, 64, 64, 64);
        flybie_left[2] = flybieSheet.crop(64 * 2, 64, 64, 64);
        flybie_left[3] = flybieSheet.crop(64 * 3, 64, 64, 64);
        
        flybie_right = new BufferedImage[4];
        flybie_right[0] = horFlip(flybie_left[0]);
        flybie_right[1] = horFlip(flybie_left[1]);
        flybie_right[2] = horFlip(flybie_left[2]);
        flybie_right[3] = horFlip(flybie_left[3]);
    }
    
    // Flip image horizontally
    private static BufferedImage horFlip(BufferedImage image){
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-image.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return op.filter(image, null);
    }
}
