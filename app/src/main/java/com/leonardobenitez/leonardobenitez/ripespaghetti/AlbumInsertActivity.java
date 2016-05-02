package com.leonardobenitez.leonardobenitez.ripespaghetti;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AlbumInsertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_insert);
        Intent intent = getIntent();
        userName = intent.getStringExtra("username");

    }

    Intent goToAlbum;
    String albumCoverUrl = null;
    String userName;
    private static final int SELECT_PHOTO = 100;
    static String encodedImage = "";

    public void AlbumCoverUploadButton(View v){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }
    public void InsertAlbumButton(View v){
        //receive what was input to the text fields
        final String artistName = ((EditText)findViewById(R.id.ArtistNameEditText)).getText().toString();
        final String albumTitle = ((EditText)findViewById(R.id.AlbumTitleEditText)).getText().toString();
        final String releaseDate = ((EditText)findViewById(R.id.ReleaseDateEditText)).getText().toString();
        //this will get sent to the server and the filename will be figured out
        final String albumCoverImage = encodedImage;
        //albumCoverUrl = figure out how to store the path to the picture that has been uploaded to the server.
        //need a
        //figure out how to add album cover image file to server
        if(artistName == null || albumTitle == null || releaseDate == null || encodedImage == null){
            AlertDialog.Builder builder = new AlertDialog.Builder(AlbumInsertActivity.this);
            builder.setMessage("Please Fill All Text Fields or Upload An Image")
                    .setNegativeButton("Ok", null)
                    .create()
                    .show();
        }else{
            //response from the server
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");
                        if (success) {
                            //Log.d("success", "true");
                            goToAlbum = new Intent(AlbumInsertActivity.this, AlbumActivity.class);
                            goToAlbum.putExtra("username", userName);
                            goToAlbum.putExtra("albumname", albumTitle);
                            AlbumInsertActivity.this.startActivity(goToAlbum);
                        } else {
                            //Log.d("success", "false");
                            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(AlbumInsertActivity.this);
                            builder.setMessage("Album Upload Failed!")
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            };
            AlbumInsertRequest albumInsertRequest = new AlbumInsertRequest(artistName, albumTitle, releaseDate, albumCoverImage, responseListener);
            RequestQueue queue = Volley.newRequestQueue(AlbumInsertActivity.this);
            queue.add(albumInsertRequest);
        }



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent){
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        Bitmap yourSelectedImage = null;
        switch(requestCode) {
            case SELECT_PHOTO:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();

                    try {
                        //gets selected image
                        yourSelectedImage = decodeUri(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(AlbumInsertActivity.this);
                        builder.setMessage("Album Cover Upload Failed! Try Again.")
                                .setNegativeButton("Ok", null)
                                .create()
                                .show();
                    }

                    //send image to server location
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    yourSelectedImage.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] imageBytes = baos.toByteArray();

                    //this string gets sent to php file. must be decoded.
                    encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                    android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(AlbumInsertActivity.this);
                    builder.setMessage("Album Cover Upload Successful!")
                            .setNegativeButton("Ok", null)
                            .create()
                            .show();
                }
        }
    }

    private Bitmap decodeUri(Uri selectedImage) throws FileNotFoundException {

        // Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o);

        // The new size we want to scale to
        final int REQUIRED_SIZE = 140;

        // Find the correct scale value. It should be the power of 2.
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE
                    || height_tmp / 2 < REQUIRED_SIZE) {
                break;
            }
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        // Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o2);

    }



}
