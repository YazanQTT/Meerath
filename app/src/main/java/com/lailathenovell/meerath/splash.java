package com.lailathenovell.meerath;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class splash extends AppCompatActivity {
    Button btnEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.splash);


        btnEnter = findViewById(R.id.btnEnter);

        btnEnter.setOnClickListener(v -> {
            Intent mainIntent = new Intent(splash.this, home.class);
            startActivity(mainIntent);
            finish();
        });

    }


}