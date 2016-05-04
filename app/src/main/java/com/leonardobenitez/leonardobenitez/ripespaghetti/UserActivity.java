package com.leonardobenitez.leonardobenitez.ripespaghetti;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class UserActivity extends AppCompatActivity {
    String username, cell, albumName;
    Intent goToAlbum, userInfo, about, news, directory, help, uploadAlbum;
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
        // Edit text variable for grabbing info for search album
        EditText searchAlbum = ((EditText)findViewById(R.id.searchAlbum));
        searchAlbum.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent){
                boolean handled = false;
                if(i == EditorInfo.IME_ACTION_DONE){
                    String search = textView.getText().toString();
                    // Response received from the server
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");

                                if (success) {
                                    //Log.d("success", "true");
                                    albumName = jsonResponse.getString("albumname");
                                    goToAlbum = new Intent(UserActivity.this, AlbumActivity.class);
                                    goToAlbum.putExtra("username", username);
                                    goToAlbum.putExtra("albumname", albumName);
                                    UserActivity.this.startActivity(goToAlbum);
                                } else {
                                    //Log.d("success", "false");
                                    AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
                                    builder.setMessage("Album does not exist\nWould you like to upload this album?")
                                        .setPositiveButton("Upload", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                // upload by moving to insert album activity
                                                uploadAlbum = new Intent(UserActivity.this, AlbumInsertActivity.class);
                                                uploadAlbum.putExtra("username", username);
                                                UserActivity.this.startActivity(uploadAlbum);
                                            }
                                        })
                                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    // Cancel
                                                    dialog.dismiss();
                                                }
                                            })
                                            .show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    AlbumSearchRequest albumRequest = new AlbumSearchRequest(search, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(UserActivity.this);
                    queue.add(albumRequest);
                }
                return handled;
            }
        });
        }
    public void userInfoButtonOnClick(View v){
        // view user info and edit
        userInfo = new Intent(getApplicationContext(),UserInfoActivity.class);
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
        about = new Intent(getApplicationContext(),AboutActivity.class);
        UserActivity.this.startActivity(about);
    }
    public void newsButtonOnClick(View v){
        // view news in music
        news = new Intent(getApplicationContext(),NewsActivity.class);
        UserActivity.this.startActivity(news);
    }
    public void helpButtonOnClick(View v){
        // get help
        help = new Intent(getApplicationContext(),HelpActivity.class);
        UserActivity.this.startActivity(help);
    }
}
