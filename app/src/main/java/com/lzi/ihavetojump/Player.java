package com.lzi.ihavetojump;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Player {
    private Bitmap bitmap;
    //coordinates
    private int x,y;
    //motion speed of the character
    private int speed;

    public Player(Context context){
        this.x = 50;
        this.y = 75;
        speed = 1;
        //getting bitmap from drawable resource
        bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.player);
    }
    //function for updating the position of player
    public void update(){
        this.x ++;
    }

    //getters

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }
}
