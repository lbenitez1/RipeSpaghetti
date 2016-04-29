package com.leonardobenitez.leonardobenitez.ripespaghetti;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by leonardobenitez on 4/28/16.
 */
public class UploadReviewRequest extends StringRequest {
    private static final String UPLOAD_REVIEW_URL = "http://leonardobenitez.com/ripeSpaghetti/volleyAlbum.php";
    private Map<String, String> params;
    public UploadReviewRequest(String review, String userID, String albumID, Response.Listener<String> listener) {
        super(Method.POST, UPLOAD_REVIEW_URL, listener, null);
        params = new HashMap<>();
        params.put("userid", userID);
        params.put("albumid", albumID);
        params.put("review", review);
    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
