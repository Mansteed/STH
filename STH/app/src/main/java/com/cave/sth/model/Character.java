package com.cave.sth.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

/**
 * Created by Dalton on 2/8/2016.
 */
public class Character {
    private Bitmap bitmap;
    private int health;
    private int x, y;
    private double xv, yv;
    private MeleeWeapon weapon;
    private static final String TAG = Character.class.getSimpleName();

    public Character(Bitmap bitmap, int x, int y, int health){
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
        this.health = health;
        xv = 0;
        yv = 0;
        weapon = null;
    }

    public Bitmap getBitmap(){
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap){
        this.bitmap = bitmap;
    }

    public int getHealth(){
        return health;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public int getX(){
        return x;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return y;
    }

    public void setY(int y){
        this.y = y;
    }

    public double getXv(){
        return xv;
    }

    public void setXv(double xv){
        this.xv = xv;
    }

    public double getYv(){
        return yv;
    }

    public void setYv(double yv){
        this.yv = yv;
    }

    public MeleeWeapon getWeapon(){
        return weapon;
    }

    public void setWeapon(MeleeWeapon weapon){
        this.weapon = weapon;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmap, x-(bitmap.getWidth()/2), y-(bitmap.getWidth()/2), null);
        if (weapon != null){
            canvas.drawBitmap(weapon.getBitmap(), x + (bitmap.getWidth()/2), y, null);
        }
    }

    public void update(int screenWidth, int screenHeight) {
        if ((x < x + xv) && screenWidth >= (x + (bitmap.getWidth()/2) + xv)){
            x += xv;
        }
        if ((x > x + xv) && 0 <= (x - (bitmap.getWidth()/2) + xv)){
            x += xv;
        }
        if ((y < y + yv) && screenHeight >= (y + (bitmap.getHeight()/2) + yv)){
            y += yv;
        }
        if ((y > y + yv) && 0 <= (y - (bitmap.getHeight()/2) + yv)){
            y += yv;
        }
    }

    public void handleActionDown(Character player, Enemy enemy){
        Log.d(TAG, "Checking to see if enemy is hit");
        int px, py, ex, ey;
        double distance;
        if(player.getX() < enemy.getX()){
            px = player.getX() + (player.getBitmap().getWidth()/2);
            ex = enemy.getX() - (enemy.getBitmap().getWidth()/2);
            if (player.getY() < enemy.getY()){
                py = player.getY() + (player.getBitmap().getHeight()/2);
                ey = enemy.getY() - (enemy.getBitmap().getHeight()/2);
                distance = Math.sqrt((double)((ex - px)^2 + (ey - py)^2));
            }
            else if (player.getY() > enemy.getY()){
                py = player.getY() - (player.getBitmap().getHeight()/2);
                ey = enemy.getY() + (enemy.getBitmap().getHeight()/2);
                distance = Math.sqrt((double)((px - ex)^2 + (py - ey)^2));
            }
            else{
                distance = (double) ex - px;
            }
        }
        else if (player.getX() > enemy.getX()){
            px = player.getX() - (player.getBitmap().getWidth()/2);
            ex = enemy.getX() + (enemy.getBitmap().getWidth()/2);
            if (player.getY() < enemy.getY()){
                py = player.getY() + (player.getBitmap().getHeight()/2);
                ey = enemy.getY() - (enemy.getBitmap().getHeight()/2);
                distance = Math.sqrt((double)((ex - px)^2 + (ey - py)^2));
            }
            else if (player.getY() > enemy.getY()){
                py = player.getY() - (player.getBitmap().getHeight()/2);
                ey = enemy.getY() + (enemy.getBitmap().getHeight()/2);
                distance = Math.sqrt((double)((px - ex)^2 + (py - ey)^2));
            }
            else{
                distance = (double) px - ex;
            }
        }
        else{
            if (player.getY() > enemy.getY()){
                py = player.getY() - (player.getBitmap().getHeight()/2);
                ey = enemy.getY() + (enemy.getBitmap().getHeight()/2);
                distance = (double) py - ey;
            }
            else if (player.getY() < enemy.getY()){
                py = player.getY() + (player.getBitmap().getHeight()/2);
                ey = enemy.getY() - (enemy.getBitmap().getHeight()/2);
                distance = (double) ey - py;
            }
            else{
                distance = 0;
            }
        }

        if (distance < player.getWeapon().getRange()){
            //hit enemy
            Log.d(TAG, "Enemy is hit");
        }
    }


}
