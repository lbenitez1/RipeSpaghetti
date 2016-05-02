package com.leonardobenitez.leonardobenitez.ripespaghetti;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by leonardobenitez on 4/27/16.
 */
public class AlbumInsertRequest extends StringRequest {
    private static final String ALBUM_INSERT_URL = "http://leonardobenitez.com/ripeSpaghetti/volleyUpload.php";
    private Map<String, String> params;
    public AlbumInsertRequest(String artist, String album, String releaseDate, String coverArt, Response.Listener<String> listener) {
        super(Method.POST, ALBUM_INSERT_URL, listener, null);
        params = new HashMap<>();
        params.put("artistalbum", album);
        params.put("artistname", artist);
        params.put("releasedate", releaseDate);
        params.put("coverart", coverArt);

    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}