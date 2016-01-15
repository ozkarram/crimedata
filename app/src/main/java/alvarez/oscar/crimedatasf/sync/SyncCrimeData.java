package alvarez.oscar.crimedatasf.sync;

import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;

import alvarez.oscar.crimedatasf.models.Incident;
import alvarez.oscar.crimedatasf.util.CrimeDataApp;

/**
 * Created by Oscar Álvarez on 14/01/2016.
 */
public class SyncCrimeData {
    private static final String BASE_URL = "https://data.sfgov.org/resource/cuks-n6tp.json";
    private static int ITEMS_LIMIT = 90;


    public static void getPoliceDepartments(final AppCompatActivity activity,
                                              Response.Listener<String> responseListener,
                                              Response.ErrorListener errorListener,
                                              int requestCounter) {
        RequestQueue queue = CrimeDataApp.getRequestQueue(activity);
        String url = BASE_URL + "?$select=pddistrict,COUNT(*)&$group=pddistrict"; //+ getPaginationParameters(requestCounter);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                responseListener, errorListener);
        queue.add(stringRequest);
    }

    public static void getIncidentsSF(final AppCompatActivity activity,
                                        Response.Listener<Incident[]> responseListener,
                                        Response.ErrorListener errorListener,
                                        int requestCounter) {
        RequestQueue queue = CrimeDataApp.getRequestQueue(activity);
        String url = BASE_URL + "?" + getPaginationParameters(requestCounter);
        /*StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                responseListener, errorListener);*/
        queue.add(new GsonRequest<Incident[]>(url, Incident[].class, null, responseListener, errorListener));
    }

    public static void getIncidentsByDistrict(final AppCompatActivity activity,
                                                Response.Listener<String> responseListener,
                                                Response.ErrorListener errorListener,
                                                String district,
                                                int requestCounter) {
        RequestQueue queue = CrimeDataApp.getRequestQueue(activity);
        String url = BASE_URL + "?pddistrict=" + district +
                "&" + getPaginationParameters(requestCounter);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                responseListener, errorListener);
        queue.add(stringRequest);
    }

    private static String getPaginationParameters(int requestCounter) {
        return "$limit="+ITEMS_LIMIT+"&$offset="+(ITEMS_LIMIT * requestCounter);
    }


}
