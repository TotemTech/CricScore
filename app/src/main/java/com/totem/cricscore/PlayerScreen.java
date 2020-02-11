package com.totem.cricscore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PlayerScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Players");
        setContentView(R.layout.activity_player_screen);
    }
}
