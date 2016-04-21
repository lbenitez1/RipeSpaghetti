package com.leonardobenitez.leonardobenitez.ripespaghetti;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class LoginActivity extends AppCompatActivity {
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
        final String username = ((EditText)findViewById(R.id.usernameField)).getText().toString();
        final String password = ((EditText)findViewById(R.id.passwordField)).getText().toString();
        Response.Listener<String> responseListner = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        String cell = jsonResponse.getString("cell");
                        Intent login = new Intent(getApplicationContext(), UserActivity.class);
                        login.putExtra("user", username);
                        login.putExtra("cell", cell);
                        Intent intent = new Intent(LoginActivity.this, UserActivity.class);
                        LoginActivity.this.startActivity(intent);                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setMessage("Login Failed")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        LoginRequest loginRequest = new LoginRequest(username, password, responseListner);
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        queue.add(loginRequest);
    }
    public void forgotPassWordButton(View v){
        //do something
        Intent forgot = new Intent(getApplicationContext(),RegisterActivity.class);
        startActivity(forgot);
    }
}
