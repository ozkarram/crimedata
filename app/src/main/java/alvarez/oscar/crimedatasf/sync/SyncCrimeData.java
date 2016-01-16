package alvarez.oscar.crimedatasf.sync;

import android.support.v7.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;

import alvarez.oscar.crimedatasf.models.District;
import alvarez.oscar.crimedatasf.models.Incident;
import alvarez.oscar.crimedatasf.util.Util;

/**
 * Created by Oscar Álvarez on 14/01/2016.
 */
public class SyncCrimeData {
    private static final String BASE_URL = "https://data.sfgov.org/resource/cuks-n6tp.json";
    private static int ITEMS_LIMIT = 100;


    public static void getPoliceDepartments(final AppCompatActivity activity,
                                              Response.Listener<District[]> responseListener,
                                              Response.ErrorListener errorListener) {
        RequestQueue queue = Util.getRequestQueue(activity);
        String url = BASE_URL + "?$select=pddistrict,COUNT(*)&$group=pddistrict";
        queue.add(new GsonRequest<>(url, District[].class, null, responseListener, errorListener));
    }

    public static void getIncidentsSF(final AppCompatActivity activity,
                                        Response.Listener<Incident[]> responseListener,
                                        Response.ErrorListener errorListener,
                                        int requestCounter) {
        RequestQueue queue = Util.getRequestQueue(activity);
        String url = BASE_URL + "?" + getPaginationParameters(requestCounter);
        queue.add(new GsonRequest<>(url, Incident[].class, null, responseListener, errorListener));
    }

    public static void getIncidentsByDistrict(final AppCompatActivity activity,
                                                Response.Listener<Incident[]> responseListener,
                                                Response.ErrorListener errorListener,
                                                String district,
                                                int requestCounter) {
        RequestQueue queue = Util.getRequestQueue(activity);
        String url = BASE_URL + "?pddistrict=" + district +
                "&" + getPaginationParameters(requestCounter);
        queue.add(new GsonRequest<>(url, Incident[].class, null, responseListener, errorListener));
    }

    private static String getPaginationParameters(int requestCounter) {
        return "$limit="+ITEMS_LIMIT+"&$offset="+(ITEMS_LIMIT * requestCounter);
    }


}
