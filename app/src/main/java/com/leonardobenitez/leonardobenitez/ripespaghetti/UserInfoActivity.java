package com.leonardobenitez.leonardobenitez.ripespaghetti;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UserInfoActivity extends AppCompatActivity {
    String username, cell;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        final TextView userNameMessage = (TextView) findViewById(R.id.userNameField);
        final TextView cellPhoneMessage = (TextView) findViewById(R.id.cellPhoneField);
        final TextView createAtMessage = (TextView) findViewById(R.id.creationDateField);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        cell = intent.getStringExtra("cell");

        userNameMessage.setText(username);
        cellPhoneMessage.setText(cell);

    }
}
