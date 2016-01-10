/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiles;

import gfx.Assets;

/**
 *
 * @author Anthony Nguyen
 */
public class Underground2 extends Tile {

    public Underground2(int id) {
        super(Assets.underground2, id);
    }
    
    @Override
    public boolean isSolid(){
        return true;
    }
}
