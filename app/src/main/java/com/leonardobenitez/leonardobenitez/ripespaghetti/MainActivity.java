package com.leonardobenitez.leonardobenitez.ripespaghetti;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public String username;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MyApp","I am here");
        }
    public void signUpButton(View v){
        //do something
        Intent register = new Intent(getApplicationContext(),RegisterActivity.class);
        startActivity(register);
    }
    public void loginButton(View v){
        //do something
        /*if(password == username) {
            Intent login = new Intent(getApplicationContext(), UserActivity.class);
            startActivity(login);
        }*/
        username = ((EditText)findViewById(R.id.usernameField)).getText().toString();
        password = ((EditText)findViewById(R.id.passwordField)).getText().toString();
        if(password.equals(username)) {
            Intent login = new Intent(getApplicationContext(), UserActivity.class);
            startActivity(login);
        }
    }
    public void forgotPassWordButton(View v){
        //do something
        Intent forgot = new Intent(getApplicationContext(),RegisterActivity.class);
        startActivity(forgot);
    }
}
