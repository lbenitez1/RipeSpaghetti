package com.leonardobenitez.leonardobenitez.ripespaghetti;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AlbumInsertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_insert);
    }

    String albumCoverUrl = null;
    private static final int SELECT_PHOTO = 100;

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
        //albumCoverUrl = figure out how to store the path to the picture that has been uploaded to the server.
        //need a
        //figure out how to add album cover image file to server
        if(artistName == null || albumTitle == null || releaseDate == null){
            AlertDialog.Builder builder = new AlertDialog.Builder(AlbumInsertActivity.this);
            builder.setMessage("Please Fill All Text Fields")
                    .setNegativeButton("Ok", null)
                    .create()
                    .show();
        }else{
            //response from the server

        }



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent){
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch(requestCode){
            case SELECT_PHOTO:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    //InputStream imageStream = getContentResolver().openInputStream(selectedImage);
                    //Bitmap yourSelectedImage = decodeUri(selectedImage);
                }
        }
    }
     //figure out this shit tomorrow, look at  tutorial in bookmarks

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
