package com.leonardobenitez.leonardobenitez.ripespaghetti;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

/**
 * Created by Sean on 5/3/2016.
 */
public class AlbumDirectoryRequest extends StringRequest {
    private static final String ALBUM_DIRECTORY_PULL = "http://leonardobenitez.com/ripeSpaghetti/volleyAlbumDirectoryPull.php";
    private Map<String, String> params;
    public AlbumDirectoryRequest(Response.Listener<String> listener){
        super(Method.POST, ALBUM_DIRECTORY_PULL, listener, null);
        //dont have to pass anything because simple query?
    }


}
