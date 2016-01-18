package com.globant.crimedatasf.sync;

import android.support.v7.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.globant.crimedatasf.models.District;
import com.globant.crimedatasf.models.Incident;
import com.globant.crimedatasf.util.Util;

import java.util.Calendar;

/**
 * Created by Oscar Álvarez on 14/01/2016.
 */
public class SyncCrimeData {
    private static final String BASE_URL = "https://data.sfgov.org/resource/cuks-n6tp.json";
    public static final String SEPARATOR = "?";
    public static final String LIMIT = "$limit=";
    public static final String OFFSET = "&$offset=";
    public static final String SPACE_CHAR = "%20";
    public static final String QUOTE_CHAR = "%27";
    public static final String BETWEEN = "between";
    public static final String AND = "and";
    public static final String WHERE_DATE = "&$where=date";
    public static final String PDDISTRICT = SEPARATOR + "pddistrict=";
    public static final String AMPERSAND = "&";
    public static final String QUERY_DISTRICTS = SEPARATOR +
                                                 "$select=pddistrict,COUNT(*)&$group=pddistrict";

    private static int ITEMS_LIMIT = 100;


    public static void getPoliceDepartments(final AppCompatActivity activity,
                                            Response.Listener<District[]> responseListener,
                                            Response.ErrorListener errorListener) {
        RequestQueue queue = Util.getRequestQueue(activity);
        String url = BASE_URL + QUERY_DISTRICTS;
        queue.add(new GsonRequest<>(url, District[].class, null, responseListener, errorListener));
    }

    public static void getIncidentsSF(final AppCompatActivity activity,
                                      Response.Listener<Incident[]> responseListener,
                                      Response.ErrorListener errorListener,
                                      int requestCounter) {
        RequestQueue queue = Util.getRequestQueue(activity);
        String url = BASE_URL + SEPARATOR + getPaginationParameters(requestCounter)
                     + getLastMonthInfo(System.currentTimeMillis() / 1000);
        queue.add(new GsonRequest<>(url, Incident[].class, null, responseListener, errorListener));
    }

    public static void getIncidentsByDistrict(final AppCompatActivity activity,
                                              Response.Listener<Incident[]> responseListener,
                                              Response.ErrorListener errorListener,
                                              String district,
                                              int requestCounter) {
        RequestQueue queue = Util.getRequestQueue(activity);
        String url = BASE_URL + PDDISTRICT + district +
                     AMPERSAND + getPaginationParameters(requestCounter)
                     + getLastMonthInfo(System.currentTimeMillis() / 1000);
        queue.add(new GsonRequest<>(url, Incident[].class, null, responseListener, errorListener));
    }

    private static String getPaginationParameters(int requestCounter) {
        return LIMIT + ITEMS_LIMIT + OFFSET + (ITEMS_LIMIT * requestCounter);
    }

    private static String getLastMonthInfo(long currentDate) {
        Calendar currentCalendar = Calendar.getInstance();
        Calendar monthLessCalendar = Calendar.getInstance();
        currentCalendar.setTimeInMillis(currentDate * 1000);
        monthLessCalendar.setTimeInMillis(currentCalendar.getTimeInMillis());
        monthLessCalendar.add(Calendar.MONTH, -3);
        return WHERE_DATE + SPACE_CHAR + BETWEEN
               + SPACE_CHAR + QUOTE_CHAR
               + Util.getFloatingTimestampFormat(monthLessCalendar)
               + QUOTE_CHAR + SPACE_CHAR
               + AND
               + SPACE_CHAR + QUOTE_CHAR
               + Util.getFloatingTimestampFormat(currentCalendar)
               + QUOTE_CHAR;
    }


}
