package com.leonardobenitez.leonardobenitez.ripespaghetti;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by leonardobenitez on 4/21/16.
 */
public class LoginRequest extends StringRequest {
    private static final String LOGIN_URL = "http://leonardobenitez.com/ripeSpaghetti/volleyLogin.php";
    private Map<String, String> params;
    public LoginRequest(String username, String password, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
