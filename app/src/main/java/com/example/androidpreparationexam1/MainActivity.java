package com.example.androidpreparationexam1;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    Music music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeVariable();
        setListener();
    }

    private void initializeVariable(){
        music = new Music(MediaPlayer.create(this, R.raw.daichi_miura_blizzard));
    }

    private void setListener(){
        setButtonListener();
        setSeekBarListener();
    }

    private void setButtonListener(){
        final Button buttonMusic = findViewById(R.id.button_music);
        buttonMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music.toggle();
                if(music.isPlaying()){
                    buttonMusic.setText(R.string.pause);
                }else{
                    buttonMusic.setText(R.string.start);
                }
            }
        });

        final Button buttonStart = findViewById(R.id.button_start);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music.start();
            }
        });

        final Button buttonStop = findViewById(R.id.button_stop);
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music.stop();
            }
        });
    }

    private void setSeekBarListener(){
        final SeekBar seekBarMusic = findViewById(R.id.seekBar_music);
        seekBarMusic.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    music.seekToPercent(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final Handler seekBarMusicHandler = new Handler();
        seekBarMusicHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                seekBarMusic.setProgress(music.getPercent());
                seekBarMusicHandler.postDelayed(this, 1000);
            }
        }, 1000);
    }

}
