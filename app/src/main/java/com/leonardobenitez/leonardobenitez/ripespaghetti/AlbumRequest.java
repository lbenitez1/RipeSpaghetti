package com.leonardobenitez.leonardobenitez.ripespaghetti;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by leonardobenitez on 4/27/16.
 */
public class AlbumRequest extends StringRequest {
    private static final String SEARCH_ALBUM_URL = "http://leonardobenitez.com/ripeSpaghetti/volleySearchAlbum.php";
    private Map<String, String> params;
    public AlbumRequest(String search, Response.Listener<String> listener) {
        super(Method.POST, SEARCH_ALBUM_URL, listener, null);
        params = new HashMap<>();
        params.put("search", search);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}