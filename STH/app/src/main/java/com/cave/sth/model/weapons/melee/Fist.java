package com.cave.sth.model.weapons.melee;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.SurfaceView;

import com.cave.sth.R;
import com.cave.sth.model.MeleeWeapon;

/**
 * Created by Dalton on 2/9/2016.
 */
public class Fist extends MeleeWeapon {

    public Fist(SurfaceView view){
        setDamage(1);
        setRange(10);
        setRefresh(2); //Set time when used and then check if this value is longer than that time.
        setBitmap(BitmapFactory.decodeResource(view.getResources(), R.drawable.fist));
    }
}
