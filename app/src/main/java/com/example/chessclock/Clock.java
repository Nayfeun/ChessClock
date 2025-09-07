package com.example.chessclock;

import android.os.Handler;
import android.widget.TextView;
public class Clock {

    private double time;
    private int increment;
    private TextView timerTextView;
    private Handler handler;
    private Runnable runnable;
    private boolean isRunning = false;

    private boolean outOfTime = false;

    private boolean started = false;

    public Clock(double time, int increment, TextView timerTextView) {
        this.time = time;
        this.increment = increment;
        this.timerTextView = timerTextView;
        this.handler = new Handler();

        this.runnable = new Runnable() {
            @Override
            public void run() {
                Clock.this.time -= 0.01;
                timerTextView.setText(setTimerLabel());

                if (Clock.this.time <= 0) {
                    outOfTime = true;
                    stop();
                    timerTextView.setText("Time is over");
                } else {
                    handler.postDelayed(this, 10);
                }
            }
        };

        timerTextView.setText(setTimerLabel());
    }

    public void start() {
        started = true;
        if (!isRunning) {
            handler.postDelayed(runnable, 10);
            isRunning = true;
        }
    }

    public void stop() {
        if (isRunning) {
            handler.removeCallbacks(runnable);
            isRunning = false;
        }
    }

    public void played(Clock opponentClock) {
        this.time += increment;
        this.timerTextView.setText(setTimerLabel());

        this.stop();
        opponentClock.start();
    }


    public String setTimerLabel() {
        int minutes = (int) (this.time / 60);
        int seconds = (int) (this.time % 60);
        int centiseconds = (int) ((this.time - (int) this.time) * 100);

        return String.format("%02d:%02d.%02d", minutes, seconds, centiseconds);
    }

    public boolean isRunning() {
        return isRunning;
    }

    public boolean isStarted() {
        return started;
    }

    public boolean isOutOfTime() {
        return outOfTime;
    }
}
