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
public class Underground1 extends Tile {

    public Underground1(int id) {
        super(Assets.underground1, id);
    }
    
    @Override
    public boolean isSolid(){
        return true;
    }
}
