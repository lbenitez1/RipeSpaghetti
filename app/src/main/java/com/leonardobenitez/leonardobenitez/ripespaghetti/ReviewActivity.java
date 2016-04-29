package com.leonardobenitez.leonardobenitez.ripespaghetti;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ReviewActivity extends AppCompatActivity {

    String username, albumName, review;
    //intent??? dont think we need since we're not passing anything to another page

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        //display "album name" reviewed by "username"
        final TextView albumReviewedBy = (TextView) findViewById(R.id.albumReviewByTextView);
        Intent intent = new Intent();
        username = intent.getStringExtra("username");
        albumName = intent.getStringExtra("albumname");
        //populate top of the page text view
        String message = albumName + " review by: " + username;
        albumReviewedBy.setText(message);

        //display the actual review
        final TextView userReviewTextField = (TextView) findViewById(R.id.userReviewTextField);
        review = intent.getStringExtra("review");
        //populate the review for the user to read
        message = review;
        userReviewTextField.setText(message);

        //i think this is all that we should need to do to display that information
    }



}
