package com.dogancanokur.catchthekenny;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OptionsActivity extends AppCompatActivity {
    SharedPreferences saveSharedPreferences;
    EditText saveTimerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        saveSharedPreferences = this.getSharedPreferences("com.dogancanokur.catchthekenny", MODE_PRIVATE);
        saveTimerText = findViewById(R.id.timerOption);
        int timer = saveSharedPreferences.getInt("saveTimer", 60);
        saveTimerText.setText(Integer.toString(timer));
    }


    public void backToMainMenu(View view) {
        Intent mainMenu = new Intent(OptionsActivity.this, MainActivity.class);
        startActivity(mainMenu);
    }

    public void clearStats(View view) {
        SharedPreferences clearPref = this.getSharedPreferences("com.dogancanokur.catchthekenny", Context.MODE_PRIVATE);

        clearPref.edit().remove("bestScore").apply();
        Toast.makeText(OptionsActivity.this, "Cleared.", Toast.LENGTH_SHORT).show();

    }

    public void saveTime(View view) {
        int timer = Integer.parseInt(saveTimerText.getText().toString());
        saveSharedPreferences.edit().putInt("saveTimer", timer).apply();
        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT);

    }

}
