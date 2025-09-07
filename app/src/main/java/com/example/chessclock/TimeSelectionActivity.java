package com.example.chessclock;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class TimeSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_time_selection);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText whiteTimeInput = findViewById(R.id.whiteTimeInput);
        EditText whiteIncrementInput = findViewById(R.id.whiteIncrementInput);
        EditText blackTimeInput = findViewById(R.id.blackTimeInput);
        EditText blackIncrementInput = findViewById(R.id.blackIncrementInput);
        Button playButton = findViewById(R.id.playButton);

        playButton.setOnClickListener(v -> {
            String whiteTimeString = whiteTimeInput.getText().toString().trim();
            String whiteIncrementString = whiteIncrementInput.getText().toString().trim();
            String blackTimeString = blackTimeInput.getText().toString().trim();
            String blackIncrementString = blackIncrementInput.getText().toString().trim();

            if (whiteTimeString.isEmpty() || whiteIncrementString.isEmpty() || blackTimeString.isEmpty() || blackIncrementString.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(TimeSelectionActivity.this, GameActivity.class);
                intent.putExtra("white_timer", Double.parseDouble(whiteTimeString));
                intent.putExtra("white_increment", Integer.parseInt(whiteIncrementString));
                intent.putExtra("black_timer", Double.parseDouble(blackTimeString));
                intent.putExtra("black_increment", Integer.parseInt(blackIncrementString));
                startActivity(intent);
            }
        });

    }
}
