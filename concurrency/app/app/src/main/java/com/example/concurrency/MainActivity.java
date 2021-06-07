package com.example.concurrency;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private TextView countdown;
    private Countdown sharedCountdown;

    // 3. This is where we can utilize multiple threads.
    //    We can start a new thread, which we sleep for 1 second before calling the tick function.
    Thread backgroundThread = new Thread(){
        @Override
        public void run() {
            try{
                while (true) {
                    TimeUnit.SECONDS.sleep(1);
                    tick(sharedCountdown);
                }
            } catch (java.lang.InterruptedException e) { }
        }
    };

    // 4. We can't directly call the tick function on the shared object, as it modifies the UI.
    //    This must happen on the thread which created the view (the main thread), for this we can use .post().
    private void tick(final Countdown sharedCountdown) {
        countdown.post(new Runnable() {
            @Override
            public void run() {
                sharedCountdown.tick();
            }
        });
    }

    // 5. Another example of concurrency is event listeners.
    //    We use event listeners when we don't want code to run straight away, but only when a certain event happens.
    //    This is often used for click events.
    //    This code is considered a seperate stream.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countdown = findViewById(R.id.countdown);
        sharedCountdown = new Countdown(countdown);
        backgroundThread.start();
    }

    public void onClickStart(View v) {
        sharedCountdown.setRunning(true);
    }

    public void onClickStop(View v) {
        sharedCountdown.setRunning(false);
    }


    // Example of not using concurrency, resulting in a crashed app.
    // We occupy the main thread, which we are also using for updating the UI.
    public void startClockBad() {
        try{
            while (true) {
                TimeUnit.SECONDS.sleep(1);
                sharedCountdown.tick();
            }
        } catch (java.lang.InterruptedException e) { }
    }

    public void onClickBad(View v) {
        startClockBad();
        sharedCountdown.setRunning(true);
    }
}