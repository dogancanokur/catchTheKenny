package com.dogancanokur.catchthekenny;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FinalActivity extends AppCompatActivity {
    TextView userScoreText;
    TextView highScoreText;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        Intent intent = getIntent();

        userScoreText = findViewById(R.id.userScore);
        highScoreText = findViewById(R.id.highestScore);
        sharedPreferences = this.getSharedPreferences("com.dogancanokur.catchthekenny", Context.MODE_PRIVATE);

        int highScore = sharedPreferences.getInt("bestScore", 0);
        int gameScore;

        gameScore = intent.getIntExtra("userScore", 0);
        userScoreText.setText("Your Score: " + gameScore);
        highScoreText.setText("Highest Score: " + highScore);

        if (highScore <= gameScore) {
            sharedPreferences.edit().putInt("bestScore", gameScore).apply();
            highScore = sharedPreferences.getInt("bestScore",0);
        }
        highScoreText.setText("Highest Score: " + highScore);
    }

    public void toMainMenu(View view) {
        toMain();
    }


    @Override
    public void onBackPressed() {
        toMain();
    }

    protected void toMain(){
        Intent finalIntent = new Intent(FinalActivity.this, MainActivity.class);
        startActivity(finalIntent);
    }
}
