package com.example.buskothy;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splashscreen extends AppCompatActivity {
    private ImageView imageView;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        imageView = findViewById(R.id.logoImageID);
        final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move);
        imageView.startAnimation(animation);
       // mediaPlayer = MediaPlayer.create(this,R.raw.sound);
       // mediaPlayer.start();

        final Thread timer = new Thread(){

            @Override
            public void run() {

                try {
                    sleep(4000);
                    Intent intent = new Intent(getApplicationContext(),ChoseLogin.class);
                    startActivity(intent);
                    finish();
                    super.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        timer.start();
    }

    /*@Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.release();
        finish();
    }*/
}
