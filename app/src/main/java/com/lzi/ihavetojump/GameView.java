package com.lzi.ihavetojump;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {

    // check the link for more information about 'volatile' keyword
    // https://www.javatpoint.com/volatile-keyword-in-java
    volatile boolean isPlaying;
    private Thread gameThread = null;

    private Player player;
    private Paint paint;

    private SurfaceHolder surfaceHolder;

    public GameView(Context context) {
        super(context);
        //Initializing player object
        player = new Player(context);
        //Initializing surfaceHolder object
        surfaceHolder = getHolder();
        paint = new Paint();
    }


    @Override
    public void run() {
        while (isPlaying){
            // function for updating the frame
            update();
            // function for drawing the frame
            draw();
            // function for controlling the frame
            control();
            
        }
    }

    private void update() {
        player.update();
    }
    private void draw() {
        Canvas canvas;
        //Checking if the surface is valid
        if (surfaceHolder.getSurface().isValid()){
            //locking the canvas
            canvas = surfaceHolder.lockCanvas();
            //Drawing a black color for canvas
            canvas.drawColor(Color.BLACK);
            //Drawing the player
            canvas.drawBitmap(player.getBitmap(),player.getX(),player.getY(),paint);
            //Unlocking the canvas
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }
    private  void control() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //function activated when the game stopped
    public void pause(){

        isPlaying = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    //function when the game resumed
    public void resume(){
        //set isPlaying variable to true
        isPlaying = true;
        //create a new thread and start it
        gameThread = new Thread();
        gameThread.start();
    }
}
