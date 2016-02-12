package com.cave.sth.controls;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.cave.sth.model.Character;

/**
 * Created by Dalton on 2/8/2016.
 */
public class Movement {
    private Bitmap bitmap;
    private int x, y;

    public Movement(Bitmap bitmap, int x, int y){
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
    }

    public Bitmap getBitmap(){
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap){
        this.bitmap = bitmap;
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

    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmap, x-(bitmap.getWidth()/2), y-(bitmap.getHeight()/2), null);
    }

    public void handleActionDown(int eventX, int eventY, Character player){
        if (eventY < (y + bitmap.getHeight()/2) && eventY > (y - bitmap.getHeight()/2)) {
            if (eventX >= (x - (bitmap.getWidth() / 2)) && eventX <= (x - (bitmap.getWidth() / 4))) {
                player.setXv(-10);
                player.setYv(0);
            } else if (eventX <= (x + (bitmap.getWidth() / 2)) && eventX >= (x + (bitmap.getWidth() / 4))) {
                player.setXv(10);
                player.setYv(0);
            }
        }

        if (eventX < (x + bitmap.getWidth()/2) && eventX > (x - bitmap.getWidth()/2)) {
            if (eventY >= (y - (bitmap.getHeight() / 2)) && eventY <= (y - (bitmap.getHeight() / 4))) {
                player.setYv(-10);
                player.setXv(0);
            } else if (eventY <= (y + (bitmap.getHeight() / 2)) && eventY >= (y + (bitmap.getHeight() / 4))) {
                player.setYv(10);
                player.setXv(0);
            }
        }
    }

    public void handleActionUp(Character player){
        player.setXv(0);
        player.setYv(0);
    }
}
