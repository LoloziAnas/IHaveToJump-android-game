package com.lzi.ihavetojump;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceView;

import androidx.constraintlayout.solver.widgets.Rectangle;

import java.util.ArrayList;

public class GameView extends SurfaceView implements Runnable {

    private Thread thread;
    private Boolean isPlaying;
    private int screenX, screenY;
    private Background background1, background2;
    private Jump jump;
    Paint paint;
    //private int peekCounter;

    public GameView(Context context, int screenX, int screenY) {
        super(context);
        this.screenX = screenX;
        this.screenY = screenY;
        // Create two backgrounds in order to start one when the other ends.
        background1 = new Background(screenX, screenY, getResources());
        background2 = new Background(screenX, screenY, getResources());
        background2.x = screenX;

        // Create a jump to let the character change his y axis.
        jump = new Jump(screenY, getResources());

        // Paint used to draw a rectangle over the objects
        paint = new Paint();
    }

    @Override
    public void run() {
        while (isPlaying) {
            update();
            draw();
            sleep();

        }
    }

    private void update() {
        background1.x -= 10;
        background2.x -= 10;
        if (background1.x + background1.background.getWidth() <= 0) {
            background1.x = screenX;
        }
        if (background2.x + background2.background.getWidth() <= 0) {
            background2.x = screenX;
        }

        if (jump.isGoingUp) {
            if (jump.y > screenY / 2 - 55)
                jump.y -= 55;
            //peekCounter++;
        } else {
            //peekCounter = 0;
            if (jump.y < screenY - jump.height) {
                jump.y += 55;
            } else {
                jump.y = screenY - jump.height;
            }
        }
    }

    public void draw() {
        if (getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);
            canvas.drawBitmap(background2.background, background2.x, background2.y, paint);

            canvas.drawBitmap(jump.getJump(), jump.x, jump.y, paint);


            Rect rect11 = new Rect(
                    background1.x + (232 * screenX / 600),
                    215 * screenY / 303,
                    background1.x + (292 * screenX / 600),
                    303 * screenY / 303
            );

            Rect rect12 = new Rect(
                    background2.x + (232 * screenX / 600),
                    215 * screenY / 303,
                    background2.x + (292 * screenX / 600),
                    303 * screenY / 303
            );

            Rect rect21 = new Rect(
                    background1.x + (468 * screenX / 600),
                    236 * screenY / 303,
                    background1.x + (538 * screenX / 600),
                    303 * screenY / 303
            );

            Rect rect22 = new Rect(
                    background2.x + (468 * screenX / 600),
                    236 * screenY / 303,
                    background2.x + (538 * screenX / 600),
                    303 * screenY / 303
            );

            Rect rectSprite = new Rect(
                    jump.x + 0,
                    jump.y + 0,
                    jump.x + jump.width,
                    jump.y + jump.height
            );

            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(10);
            paint.setColor(Color.BLACK);
            canvas.drawRect(rect11, paint);
            canvas.drawRect(rect21, paint);
            canvas.drawRect(rect12, paint);
            canvas.drawRect(rect22, paint);
            canvas.drawRect(rectSprite, paint);

            ArrayList<Rect> rects = new ArrayList<>();
            rects.add(rect11);
            rects.add(rect21);
            rects.add(rect12);
            rects.add(rect22);
            /*
            for (Rect r : rects) {
                if (rectSprite.right >= r.left && rectSprite.bottom >= r.top) {
                    paint.setStyle(Paint.Style.FILL);
                    paint.setTextSize(150);
                    paint.setColor(Color.BLACK);
                    paint.setStrokeWidth(20);
                    canvas.drawText("Colusion", screenX / 2 - 150, 200, paint);
                }
                if(rectSprite.left < r.right){
                    paint.setStyle(Paint.Style.FILL);
                    paint.setTextSize(150);
                    paint.setColor(Color.BLACK);
                    paint.setStrokeWidth(20);
                    canvas.drawText("", screenX / 2 - 150, 200, paint);
                }
            }
            */
            for(int i = 0 ; i < rects.size() ; i++){
                if (rectSprite.right >= rects.get(i).left && rectSprite.bottom >= rects.get(i).top) {
                    paint.setStyle(Paint.Style.FILL);
                    paint.setTextSize(150);
                    paint.setColor(Color.BLACK);
                    paint.setStrokeWidth(20);
                    canvas.drawText("Colusion", screenX / 2 - 150, 200, paint);
                    //i++;
                }
            }

            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    private void sleep() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pause() {
        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (jump.y == screenY - jump.height) {
                    jump.isGoingUp = true;

                }
                break;
            case MotionEvent.ACTION_UP:
                jump.isGoingUp = false;
                break;
        }
        return true;
    }

}
