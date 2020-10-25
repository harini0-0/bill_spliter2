package com.example.billspliter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    public VideoView videoview;
    MediaPlayer mmediaplayer;
    int mcurrentposition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoview = (VideoView) findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.videoplayback);
        videoview.setVideoURI(uri);
        videoview.start();
        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mmediaplayer = mp;
                // We want our video to play over and over so we set looping to true.
                mmediaplayer.setLooping(true);
                // We then seek to the current posistion if it has been set and play the video.
                if (mcurrentposition != 0) {
                    mmediaplayer.seekTo(mcurrentposition);
                    mmediaplayer.start();
                }
            }
        });

        Button restbill = (Button)findViewById(R.id.restbill);
        restbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent restaurant = new Intent(getApplicationContext(), restaurant.class);
                startActivity(restaurant);

            }
        });
        Button exit = (Button) findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

    }
    @Override
    protected void onPause() {
        super.onPause();
        // to restart the video after coming from other activity like Sing up
        mcurrentposition = mmediaplayer.getCurrentPosition();
        videoview.pause();

    }
    @Override
    protected void onResume() {
        super.onResume();
        // to restart the video after coming from other activity like Sing up
        videoview.start();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // to restart the video after coming from other activity like Sing up
        mmediaplayer.release();
        mmediaplayer=null;

    }
}
