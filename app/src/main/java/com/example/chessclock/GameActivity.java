package com.example.chessclock;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView whiteTimer = findViewById(R.id.whiteRemainingTime);
        TextView blackTimer = findViewById(R.id.blackRemainingTime);

        Intent intent = getIntent();
        Clock whiteClock = new Clock(intent.getDoubleExtra("white_timer", 600), intent.getIntExtra("white_increment", 0), whiteTimer);
        Clock blackClock = new Clock(intent.getDoubleExtra("black_timer", 600), intent.getIntExtra("black_increment", 0), blackTimer);

        findViewById(R.id.lowerHalf).setOnClickListener(v -> {
                    if (!blackClock.isRunning() && !blackClock.isOutOfTime() && !whiteClock.isOutOfTime()) {
                        whiteClock.played(blackClock);
                    }
                }
        );
        findViewById(R.id.upperHalf).setOnClickListener(v -> {
                    if (blackClock.isRunning()) {
                        blackClock.played(whiteClock);
                    }
                }
        );
    }

}