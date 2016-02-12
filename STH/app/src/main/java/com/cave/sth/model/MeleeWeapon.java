package com.cave.sth.model;

import android.graphics.Bitmap;

/**
 * Created by Dalton on 2/9/2016.
 */
public class MeleeWeapon {
    private Bitmap bitmap;
    private int damage; //number of health weapon will diminish
    private int range; //number of pixels
    private int refresh; // time between each use
    private final String type = "melee";

    public Bitmap getBitmap(){
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap){
        this.bitmap = bitmap;
    }

    public int getDamage(){
        return damage;
    }

    public void setDamage(int damage){
        this.damage = damage;
    }

    public int getRange(){
        return range;
    }

    public void setRange(int range){
        this.range = range;
    }

    public int getRefresh(){
        return refresh;
    }

    public void setRefresh(int refresh){
        this.refresh = refresh;
    }

    public String getType(){
        return type;
    }
}
