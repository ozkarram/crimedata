package alvarez.oscar.crimedatasf.sync;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by Oscar Álvarez on 14/01/2016.
 */
public class SyncCrimeData {
    public static String getPoliceDepartments(final Context context,
                                              Response.Listener<String> responseListener,
                                              Response.ErrorListener errorListener) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://data.sfgov.org/resource/cuks-n6tp.json?$select=pddistrict,COUNT(*)&$group=pddistrict";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "pff", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "pff", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
        return "";
    }
}
