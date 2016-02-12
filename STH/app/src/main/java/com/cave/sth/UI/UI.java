package com.cave.sth.UI;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.cave.sth.R;

/**
 * Created by Dalton on 2/9/2016.
 */
public class UI {
    private int health;
    private int maxAmmo;
    private int currentAmmo;
    private SurfaceView sv;
    private static final String TAG = UI.class.getSimpleName();

    public UI (SurfaceView sv){
        this.sv = sv;
    }

    public int getHealth(){
        return health;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public int getMaxAmmo(){
        return maxAmmo;
    }

    public void setMaxAmmo(int maxAmmo){
        this.maxAmmo = maxAmmo;
    }

    public int getCurrentAmmo(){
        return currentAmmo;
    }

    public void setCurrentAmmo(int currentAmmo){
        this.currentAmmo = currentAmmo;
    }

    public void draw(Canvas canvas) {
        drawAmmo(canvas);
    }

    public void drawAmmo(Canvas canvas){
        if (maxAmmo != -1) {
            Log.d(TAG, "Not A melee weapon, requires ammo");
            canvas.drawText(currentAmmo + " / " + maxAmmo, 500, 500, null);
        }
        else{
            Log.d(TAG, "Melee Weapon, unlimited Ammo");
            canvas.drawBitmap(BitmapFactory.decodeResource(sv.getResources(), R.mipmap.infinity), 500, 500, null) ; //infinity symbol
        }
    }


}
