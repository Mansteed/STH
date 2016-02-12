package com.cave.sth.model;

import android.graphics.Bitmap;

/**
 * Created by Dalton on 2/9/2016.
 */
public class Enemy extends Character {
    private int speed;
    
    public Enemy(Bitmap bitmap, int x, int y, int health, int speed){
        super(bitmap, x, y, health);
        this.speed = speed;
    }
    
    public int getSpeed(){
        return speed;
    }
    public void setSpeed(int speed){
        this.speed = speed;
    }

    public void update(int playerX, int playerY, int screenWidth, int screenHeight){
        if (playerX < (getX() - (getBitmap().getWidth()/2))){
            setXv(-1 * speed);
        }
        else if (playerX > (getX() - (getBitmap().getWidth()/2))){
            setXv(speed);
        }
        else setXv(0);

        if (playerY < (getY() - (getBitmap().getHeight()/2))){
            setYv(-1 * speed);
        }
        else if (playerY > (getY() - (getBitmap().getHeight()/2))){
            setYv(speed);
        }
        else setYv(0);

        update(screenWidth, screenHeight);
    }
}
