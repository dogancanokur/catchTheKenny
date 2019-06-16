package com.dogancanokur.catchthekenny;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startGame(View view) {
        Intent startGame = new Intent(MainActivity.this, GameActivity.class);
        startActivity(startGame);

    }

    public void options(View view) {
        Intent goToOptions = new Intent(MainActivity.this, OptionsActivity.class);
        startActivity(goToOptions);
    }

    @Override
    public void onBackPressed() {
        // doesnt work back button
    }

}
