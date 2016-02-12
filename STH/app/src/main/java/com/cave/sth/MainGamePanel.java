package com.cave.sth;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.cave.sth.UI.UI;
import com.cave.sth.controls.Movement;
import com.cave.sth.model.Character;
import com.cave.sth.model.Enemy;
import com.cave.sth.model.weapons.melee.Fist;

/**
 * Created by Dalton on 2/8/2016.
 */
public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback{

    private static final String TAG = MainGamePanel.class.getSimpleName();

    private MainThread mainThread;

    private Character player;
    private Fist fist;
    private Enemy enemy;

    private UI ui;

    private Movement movement;

    private int screenWidth, screenHeight;

    public MainGamePanel(Context context){
        super(context);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        getHolder().addCallback(this);//adding callback(this) to the surface holder to intercept events
        mainThread = new MainThread(getHolder(), this); //create the main game loop
        player = new Character(BitmapFactory.decodeResource(getResources(), R.drawable.player), 200, 200, 100);
        enemy = new Enemy(BitmapFactory.decodeResource(getResources(), R.drawable.enemy), screenWidth - 200, 200, 10, 3);
        fist = new Fist(this);
        player.setWeapon(fist);
        ui = new UI(this);
        if (fist.getType().equals("melee")){
            ui.setMaxAmmo(-1);
        }
        movement = new Movement(BitmapFactory.decodeResource(getResources(), R.drawable.movement_circle), 200, screenHeight-200);
        setFocusable(true); //make the GamePanel focusable so it can handle events
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        //The surface is created so we can start the game loop
        mainThread.setRunning(true);
        mainThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        Log.d(TAG, "Surface is being destroyed");

        //tell thread to shut down and wait for it to finish
        boolean retry = true;
        while (retry){
            try{
                mainThread.join();
                retry = false;
            }
            catch(InterruptedException e){
                //try again shutting down the thread
            }
        }
        Log.d(TAG, "Thread was shut down cleanly");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            Log.d(TAG, "Screen was touched at X:" + event.getX() + ", Y:" + event.getY());
            movement.handleActionDown((int)event.getX(), (int)event.getY(), player);
            player.handleActionDown(player, enemy);
        }
        if (event.getAction() == MotionEvent.ACTION_UP){
            movement.handleActionUp(player);
        }
        return true;
    }


    public void render(Canvas canvas){
        canvas.drawColor(Color.BLACK);
        player.draw(canvas);
        enemy.draw(canvas);
        movement.draw(canvas);
        ui.draw(canvas);
    }

    public void update(){
        player.update(screenWidth, screenHeight);
        enemy.update(player.getX(), player.getY(), screenWidth, screenHeight);
        if (!player.getWeapon().getType().equals("melee")){
            //Update Ammo Amount
        }
    }
}
