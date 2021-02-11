package com.lzi.ihavetojump;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Jump {
    public boolean isGoingUp = false;
    int x, y, width, height, wingCounter = 0;
    Bitmap j1, j2;

    Jump(int screenY, Resources resources) {
        j1 = BitmapFactory.decodeResource(resources, R.drawable.jump1);
        j2 = BitmapFactory.decodeResource(resources, R.drawable.jump2);
        width = j1.getWidth();
        height = j1.getHeight();
        j1 = Bitmap.createScaledBitmap(j1, width, height, false);
        j2 = Bitmap.createScaledBitmap(j2, width, height, false);

        y = screenY - j1.getHeight();
        x = 50;
    }

    public Bitmap getJump() {
        if (wingCounter == 0) {
            wingCounter++;
            return j1;
        }
        wingCounter--;
        return j2;
    }
}
