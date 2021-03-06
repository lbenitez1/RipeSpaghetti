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
    private static final String UPLOAD_REVIEW_URL = "http://leonardobenitez.com/ripeSpaghetti/volleyReview.php";
    private Map<String, String> params;
    public UploadReviewRequest(String review, String userName, String albumName, Response.Listener<String> listener) {
        super(Method.POST, UPLOAD_REVIEW_URL, listener, null);
        params = new HashMap<>();
        params.put("username", userName);
        params.put("albumname", albumName);
        params.put("review", review);
    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
