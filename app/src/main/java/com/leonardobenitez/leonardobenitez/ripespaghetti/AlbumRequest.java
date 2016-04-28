package com.leonardobenitez.leonardobenitez.ripespaghetti;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by leonardobenitez on 4/27/16.
 */
public class AlbumRequest extends StringRequest {
    private static final String SEARCH_ALBUM_URL = "http://leonardobenitez.com/ripeSpaghetti/volleyAlbum.php";
    private Map<String, String> params;
    public AlbumRequest(String album, Response.Listener<String> listener) {
        super(Method.POST, SEARCH_ALBUM_URL, listener, null);
        params = new HashMap<>();
        params.put("album", album);
    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}