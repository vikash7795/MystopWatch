package com.example.vikash.mystopwatch;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;
public class MainActivity extends Activity {
    //Number of seconds displayed on the stopwatch.
    private int seconds = 0;
    //Is the stopwatch running?
    private boolean running;
    //storing the value for onstart
    private boolean wasRunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }
    @Override
    protected void onStop() {
        super.onStop();
        wasRunning = running;
        running = false;
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (wasRunning) {
            running = true;
        }
    }
    //Start the stopwatch running when the Start button is clicked.
    public void onClickStart(View view) {
        running = true;
    }
    //Stop the stopwatch running when the Stop button is clicked.
    public void onClickStop(View view) {
        running = false;

    }
    //saving screen orientation changes
    @Override
    public void onSaveInstanceState(Bundle b) {
        b.putInt("seconds", seconds);
        b.putBoolean("running", running);
      b.putBoolean("wasRunning", wasRunning);
    }
    //destroy the activity

    //Reset the stopwatch when the Reset button is clicked.
    public void onClickReset(View view) {
        running = false;
        seconds = 0;
    }
    //Sets the number of seconds on the timer.
    private void runTimer() {
        final TextView timeView = (TextView)findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format("%d:%02d:%02d",
                        hours, minutes, secs);
                timeView.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}