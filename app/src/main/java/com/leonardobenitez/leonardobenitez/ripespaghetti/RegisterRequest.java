package com.leonardobenitez.leonardobenitez.ripespaghetti;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by leonardobenitez on 4/20/16.
 */
public class RegisterRequest extends StringRequest {
    private static final String REGISTER_URL = "http://leonardobenitez.com/ripeSpaghetti/volleyRegister.php";
    private Map<String, String> params;
    public RegisterRequest(String username, String password, String cell, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        params.put("cell", cell);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
