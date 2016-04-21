package com.leonardobenitez.leonardobenitez.ripespaghetti;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        final TextView welcomeMessage = (TextView) findViewById(R.id.tvWelcomeMessage);

        Intent intent = getIntent();
        String name = intent.getStringExtra("username");
        String cell = intent.getStringExtra("cell");

        String message = name + " welcome to your user area";
        welcomeMessage.setText(message);

    }
}
