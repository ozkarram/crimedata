package alvarez.oscar.crimedatasf.util;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Oscar Álvarez on 15/01/16.
 */
public class CrimeDataApp extends Application {

    private static RequestQueue mRequestQueue;

    public static RequestQueue getRequestQueue(Context context) {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context);
        }

        return mRequestQueue;
    }



}
