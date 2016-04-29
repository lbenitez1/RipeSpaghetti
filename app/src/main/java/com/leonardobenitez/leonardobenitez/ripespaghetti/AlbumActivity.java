package com.leonardobenitez.leonardobenitez.ripespaghetti;

import android.content.Intent;
import android.support.v7.app.AlertDialog;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AlbumActivity extends AppCompatActivity {
    //variables to be sent to php files
    String albumName, userName;
    //variables to retrieved from php files
    String album, coverArt, artist, title;
    int count;
    Bitmap albumCover;
    //array for album reviews
    String[] reviews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        final RelativeLayout albumLayout = (RelativeLayout)findViewById(R.id.albumLayout);
        //get info from previous activity
        Intent intent = getIntent();
        albumName = intent.getStringExtra("album");
        userName = intent.getStringExtra("username");
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean albumsuccess = jsonResponse.getBoolean("albumsuccess");
                    if (albumsuccess) {
                        //Log.d("success", "true");
                        //display reviews
                        album = jsonResponse.getString("album");
                        coverArt = jsonResponse.getString("coverart");
                        artist = jsonResponse.getString("artist");
                        albumCover = StringToBitMap(coverArt);
                        title = album+" by "+artist;
                        final TextView albumTitle = (TextView) findViewById(R.id.tvAlbumName);
                        albumTitle.setText(title);

                        boolean reviewsuccess = jsonResponse.getBoolean("reviewsuccess");
                        if (reviewsuccess) {
                            count = jsonResponse.getInt("count");
                            reviews = new String[count];
                            TextView[] tvReviews = new TextView[count];
                            for(int i = 0; i<count;i++){
                                reviews[i] = jsonResponse.getString("review"+Integer.toString(i));
                                tvReviews[i].setText(reviews[i]);
                                albumLayout .addView(tvReviews[i]);
                            }
                        }
                        else{
                            //display message that album has no reviews
                            TextView noReview = new TextView(getApplicationContext());
                            noReview.setText("No Reviews");
                            albumLayout .addView(noReview);
                        }
                    } else {
                        //Log.d("success", "false");
                        //display message that album has error

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        AlbumRequest albumRequest = new AlbumRequest(albumName, responseListener);
        RequestQueue queue = Volley.newRequestQueue(AlbumActivity.this);
        queue.add(albumRequest);
    }

    //turns an encoded string into a bitmap that will be able to be displayed.
    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }

    //calcuates insamplesize to make sure size of image is appropriate/scaled down
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    //decode bitmap and make proper size to be displayed on the image view
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    //THIS CAN BE THE WAY WE CAN USE THE ABOVE FUNCTIONS TO DISPLAY BITMAP ON IMAGE VIEW
    //mImageView.setImageBitmap(
    //decodeSampledBitmapFromResource(getResources(), R.id.myimage, 100, 100));


}
