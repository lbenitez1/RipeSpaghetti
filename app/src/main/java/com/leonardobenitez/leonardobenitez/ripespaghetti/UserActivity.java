package com.leonardobenitez.leonardobenitez.ripespaghetti;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        // display hello to user
        final TextView welcomeMessage = (TextView) findViewById(R.id.tvWelcomeMessage);
        Intent intent = getIntent();
        String name = intent.getStringExtra("username");
        String cell = intent.getStringExtra("cell");
        // construct welcome message to user
        String message = "Hello "+name;
        welcomeMessage.setText(message);

    }
    public void userInfoButtonOnClick(View v){
        // view user info and edit
        Intent userInfo = new Intent(getApplicationContext(),UserActivity.class);
        UserActivity.this.startActivity(userInfo);
    }
    public void signOutButtonOnClick(View v){
        // sign out
        Intent signOut = new Intent(getApplicationContext(),LoginActivity.class);
        UserActivity.this.startActivity(signOut);
    }
    public void aboutButtonOnClick(View v){
        // sign out
        Intent about = new Intent(getApplicationContext(),LoginActivity.class);
        UserActivity.this.startActivity(about);
    }
    public void newsButtonOnClick(View v){
        // view news in music
        Intent news = new Intent(getApplicationContext(),UserActivity.class);
        UserActivity.this.startActivity(news);
    }
    public void albumDirectoryButtonOnClick(View v){
        // view all albums in directory
        Intent directory = new Intent(getApplicationContext(),UserActivity.class);
        UserActivity.this.startActivity(directory);
    }
    public void helpButtonOnClick(View v){
        // get help
        Intent help = new Intent(getApplicationContext(),UserActivity.class);
        UserActivity.this.startActivity(help);
    }
    public void searchAlbumOnClick(View v){
        // search album

    }
}
