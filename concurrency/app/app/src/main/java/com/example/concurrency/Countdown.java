package com.example.concurrency;

import android.widget.TextView;

import java.util.Locale;

public class Countdown {
    private int seconds = 0;
    private int deadline = 60*30;
    private boolean running = false;
    private TextView countdown;

    public Countdown(TextView countdown){
        this.countdown = countdown;
    }

    // 2. We write the result to the screen.
    private void write(TextView countdown){
        int hours = (deadline - seconds)/3600;
        int minutes = (deadline - seconds)/60;
        int secs = (deadline - seconds)%60;
        String timeUntilDeadline = String.format(Locale.getDefault(),"%d:%02d:%02d", hours, minutes, secs);
        countdown.setText(timeUntilDeadline);
    }

    // 1. A simple countdown, with a tick function to keep track of seconds passed.
    //    We use the synchronized keyword, to make sure that no two methods access the method at the same time.
    public synchronized void tick(){
        if (running) seconds = seconds + 1;
        write(countdown);
    }

    public synchronized void setRunning(boolean newState){
        running = newState;
    }

    public synchronized void reset(){
        running = false;
        seconds = 0;
        write(countdown);
    }
}

