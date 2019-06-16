package com.dogancanokur.catchthekenny;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    TextView timerText;
    TextView scoreText;
    ImageView kenny0x0;
    ImageView kenny0x1;
    ImageView kenny0x2;
    ImageView kenny1x0;
    ImageView kenny1x1;
    ImageView kenny1x2;
    ImageView kenny2x0;
    ImageView kenny2x1;
    ImageView kenny2x2;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;
    Random random = new Random();
    int kennysSpeed;

    int score = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // initialize

        timerText = findViewById(R.id.timer);
        scoreText = findViewById(R.id.score);
        kenny0x0 = findViewById(R.id.kenny0x0);
        kenny0x1 = findViewById(R.id.kenny0x1);
        kenny0x2 = findViewById(R.id.kenny0x2);
        kenny1x0 = findViewById(R.id.kenny1x0);
        kenny1x1 = findViewById(R.id.kenny1x1);
        kenny1x2 = findViewById(R.id.kenny1x2);
        kenny2x0 = findViewById(R.id.kenny2x0);
        kenny2x1 = findViewById(R.id.kenny2x1);
        kenny2x2 = findViewById(R.id.kenny2x2);
        imageArray = new ImageView[]{
                kenny0x0, kenny0x1, kenny0x2,
                kenny1x0, kenny1x1, kenny1x2,
                kenny2x0, kenny2x1, kenny2x2
        };

        hideImage();

        timerText.setText("Time Left: " + 60);
        scoreText.setText("Score: " + 0);
        int timer;
        SharedPreferences timeSharedPreferences = this.getSharedPreferences("com.dogancanokur.catchthekenny", MODE_PRIVATE);
        timer = timeSharedPreferences.getInt("saveTimer", 60);
        int timeLeft = timer * 1000;

        new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) { // her bir saniyede
                timerText.setText("Time Left: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() { // bitince ne olacak
                Intent gameIntent = new Intent(GameActivity.this, FinalActivity.class);
                gameIntent.putExtra("userScore", score);
                handler.removeCallbacks(runnable);
                finish();
                startActivity(gameIntent);

            }
        }.start();

    }

    public void increaseScore(View view) {
        score++;
        scoreText.setText("Score: " + score);
        handler.removeCallbacks(runnable);

        hideImage();

    }

    public void hideImage() {
        kennysSpeed = 750; // default kenny speed

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);

                }
                int i = random.nextInt(9); // [0,8]
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this, kennysSpeed);
                switch (score) {
                    case 15:
                        kennysSpeed = 750;
                        break;

                    case 30:
                        kennysSpeed = 550;
                        break;

                    case 45:
                        kennysSpeed = 350;
                        break;
                }
            }
        };
        handler.post(runnable);
    }

    @Override
    public void onBackPressed() {
        finish();
        handler.removeCallbacks(runnable);
        Intent goToMain = new Intent(GameActivity.this,MainActivity.class);
        startActivity(goToMain);
    }
}
