package alvarez.oscar.crimedatasf.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.TimeZone;

import alvarez.oscar.crimedatasf.R;
import alvarez.oscar.crimedatasf.models.District;

/**
 * Created by Oscar Álvarez on 14/01/2016.
 */
public class Util {

    public static final int DEFAULT_COLOR = 8;
    private static RequestQueue mRequestQueue;
    private static boolean lastRequestFromSF;
    private static int counterRequest;

    public static RequestQueue getRequestQueue(Context context) {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context);
        }

        return mRequestQueue;
    }

    public static String getFloatingTimestampFormat(Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = calendar.getTime();
        return sdf.format(date).replace(" ", "T");
    }

    public static Comparator<District> getDistrictComparator() {
        return new Comparator<District>() {
            @Override
            public int compare(District lhs, District rhs) {
                return Integer.valueOf(rhs.getCount()) - Integer.valueOf(lhs.getCount());
            }
        };
    }

    public static Bitmap getTintBitmap(Context context, @DrawableRes int src, @ColorRes int color) {

        Bitmap ob = BitmapFactory.decodeResource(context.getResources(), src);
        ob = Bitmap.createScaledBitmap(ob, ob.getWidth(), ob.getHeight(), false);
        Bitmap obm = Bitmap.createBitmap(ob.getWidth(), ob.getHeight(), ob.getConfig());
        Canvas canvas = new Canvas(obm);
        Paint paint = new Paint();
        paint.setColorFilter(new PorterDuffColorFilter(
            context.getResources().getColor(color), PorterDuff.Mode.SRC_ATOP));
        canvas.drawBitmap(ob, 0f, 0f, paint);
        return obm;
    }

    public static Drawable getTintDrawable(Context context, @DrawableRes int src, @ColorRes int color) {
        Drawable drawable = ContextCompat.getDrawable(context, src);//context.getResources().getDrawable(src, context.getTheme());
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN);
        DrawableCompat.setTint(drawable, Color.RED);
        return drawable;
    }

    public static void setLastRequestFromSF(boolean val) {
        lastRequestFromSF = val;
    }

    public static boolean getLastRequestFromSF() {
        return lastRequestFromSF;
    }

    public static int getCounterRequest() {
        return counterRequest;
    }

    public static void setCounterRequest(int counterRequest) {
        counterRequest = counterRequest;
    }

    public static int getColorByPriority(int priority) {
        switch (priority) {
        case 0:
            return R.color.p1;
        case 1:
            return R.color.p2;
        case 2:
            return R.color.p3;
        case 3:
            return R.color.p4;
        case 4:
            return R.color.p5;
        case 5:
            return R.color.p6;
        case 6:
            return R.color.p7;
        case 7:
            return R.color.p8;
        default:
            return R.color.p8;
        }
    }
}
