package com.example.a1kayat34.multimediaapp;

import android.app.AlertDialog;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SurfaceHolder.Callback {

    //Create an object representing the File
    File mediaFile;



    //Then create the media player
    MediaPlayer player;

    boolean prepared;

    //video
    SurfaceView sv;



 // Download/3D_Printer_Slow_Edit_for_Android.mp3


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_play = (Button)findViewById(R.id.play);
        Button btn_pause = (Button)findViewById(R.id.pause);
        Button btn_rewind = (Button)findViewById(R.id.rewind);


        btn_pause.setOnClickListener(this);
        btn_rewind.setOnClickListener(this);

        mediaFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Download/3D_Printer_Slow_Edit_for_Android.mp3");
        player  = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            player.setDataSource(getApplicationContext(), Uri.fromFile(mediaFile));
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
                                             public void onPrepared(MediaPlayer mp){
                                                 //mp.start();
                                                 prepared = true;
                                                 Toast.makeText(MainActivity.this, "Playing multimedia...",Toast.LENGTH_SHORT).show();
                                             }
            }

            );
            player.prepareAsync();

        } catch (Exception e) {
           // e.printStackTrace();
            new AlertDialog.Builder(this).setPositiveButton("OK", null).setMessage(e.toString()).show();
        }

        btn_play.setOnClickListener(this);

        sv = (SurfaceView)findViewById(R.id.video);
        sv.getHolder().addCallback(this);


    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.play){
            if(prepared){
                player.start();

            }
        }
        else if(view.getId() == R.id.pause){
            if(prepared){
                player.pause();

            }

        }
        else if(view.getId() == R.id.rewind){
            if(prepared){
                player.seekTo(0);

            }

        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
