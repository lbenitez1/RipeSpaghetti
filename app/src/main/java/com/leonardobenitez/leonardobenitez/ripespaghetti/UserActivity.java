package com.leonardobenitez.leonardobenitez.ripespaghetti;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class UserActivity extends AppCompatActivity {
    String username, cell;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        // display hello to user
        final TextView welcomeMessage = (TextView) findViewById(R.id.tvWelcomeMessage);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        cell = intent.getStringExtra("cell");
        // construct welcome message to user
        String message = "Hello "+username;
        welcomeMessage.setText(message);

    }
    public void userInfoButtonOnClick(View v){
        // view user info and edit
        Intent userInfo = new Intent(getApplicationContext(),UserInfoActivity.class);
        userInfo.putExtra("username", username);
        userInfo.putExtra("cell", cell);
        UserActivity.this.startActivity(userInfo);
    }
    public void signOutButtonOnClick(View v){
        // sign out
        finish();
    }
    public void aboutButtonOnClick(View v){
        // sign out
        Intent about = new Intent(getApplicationContext(),AboutActivity.class);
        UserActivity.this.startActivity(about);
    }
    public void newsButtonOnClick(View v){
        // view news in music
        Intent news = new Intent(getApplicationContext(),NewsActivity.class);
        UserActivity.this.startActivity(news);
    }
    public void albumDirectoryButtonOnClick(View v){
        // view all albums in directory
        Intent directory = new Intent(getApplicationContext(),AlbumDirectoryActivity.class);
        UserActivity.this.startActivity(directory);
    }
    public void helpButtonOnClick(View v){
        // get help
        Intent help = new Intent(getApplicationContext(),HelpActivity.class);
        UserActivity.this.startActivity(help);
    }
    public void searchAlbumOnClick(View v){
        // search album
        final String search = null;
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) findItem(R.id.searchView).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        // Response received from the server
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                        //Log.d("success", "true");
                        String album = jsonResponse.getString("album");
                        Intent intent = new Intent(UserActivity.this, AlbumActivity.class);
                        intent.putExtra("username", username);
                        intent.putExtra("album", album);
                        UserActivity.this.startActivity(intent);
                    } else {
                        //Log.d("success", "false");
                        AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
                        builder.setMessage("Album does not exist\nWould you like to upload this album?")
                                .setNegativeButton("Upload", null)
                                .create()
                                .show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
/*
        AlbumRequest albumRequest = new AlbumRequest(search, responseListener);
        RequestQueue queue = Volley.newRequestQueue(UserActivity.this);
        queue.add(albumRequest);
*/
    }
}
