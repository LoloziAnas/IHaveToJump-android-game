package com.lzi.ihavetojump;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {

    // check the link for more information about 'volatile' keyword
    // https://www.javatpoint.com/volatile-keyword-in-java
    volatile boolean isPlaying;
    private Thread gameThread = null;

    public GameView(Context context) {
        super(context);
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
    }
    private void draw() {
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
