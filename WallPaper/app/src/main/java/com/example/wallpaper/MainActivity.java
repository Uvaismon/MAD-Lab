package com.example.wallpaper;

import androidx.appcompat.app.AppCompatActivity;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Button changeWallpaper;
    Timer myTimer;
    Drawable drawable;
    WallpaperManager wpm;
    int prev = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTimer = new Timer();
        wpm = WallpaperManager.getInstance(this);
        changeWallpaper = findViewById(R.id.button);
        changeWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View view) {
                setWallpaper();
            }
        });
    }

    private void setWallpaper() {
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                switch (prev) {
                    case 1:
                        drawable = getResources().getDrawable(R.drawable.img_1);
                        prev = 2;
                        break;
                    case 2:
                        drawable = getResources().getDrawable(R.drawable.img_2);
                        prev = 3;
                        break;
                    case 3:
                        drawable = getResources().getDrawable(R.drawable.img_3);
                        prev = 4;
                        break;
                    case 4:
                        drawable = getResources().getDrawable(R.drawable.img_4);
                        prev = 5;
                        break;
                    case 5:
                        drawable = getResources().getDrawable(R.drawable.img_5);
                        prev = 1;
                        break;
                }
                Bitmap wallpaper = ((BitmapDrawable) drawable).getBitmap();
                try {
                    wpm.setBitmap(wallpaper);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 30000);
    }
}