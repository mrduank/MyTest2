package com.example.dk.mytest.utils.net;

import com.android.volley.VolleyError;

/**
 * Created by dk on 2017/8/28.
 */

public interface VolleyResponseCallback {
    void onSuccess(String response);
    void onError(VolleyError error);
}
