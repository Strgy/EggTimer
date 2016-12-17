package com.klerzjeh.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seek;
    TextView tajmer;
    Boolean counterActive = false;
    Button gumb;
    CountDownTimer brojac;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tajmer = (TextView) findViewById(R.id.tekst);
        gumb = (Button) findViewById(R.id.play);

        seek = (SeekBar) findViewById(R.id.trazi);
        seek.setMax(600);
        seek.setProgress(30);


        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }


    public void play(View view) {

        if (counterActive == false) {

            counterActive = true;
            gumb.setText("Stop");
            seek.setEnabled(false);
            brojac = new CountDownTimer(seek.getProgress() * 1000 + 100, 1000) {


                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);


                }

                @Override
                public void onFinish() {
                    naKraju();
                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.horn);
                    mp.start();


                }
            }.start();

        } else {
            naKraju();

        }
    }

    public void naKraju() {
        tajmer.setText("0:30");
        seek.setProgress(30);
        brojac.cancel();
        gumb.setText("Go!");
        seek.setEnabled(true);
        counterActive = false;
    }

    public void updateTimer(int secondsLeft) {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;
        String secondNum = Integer.toString(seconds);
        if (secondNum.equals("0")) {
            secondNum = "00";
        } else if (seconds <= 9) {
            secondNum = "0" + secondNum;
        }
        tajmer.setText(Integer.toString(minutes) + ":" + Integer.toString(seconds));

    }
}
