package alvarez.oscar.crimedatasf.map;

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
import android.support.v4.graphics.drawable.DrawableCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;

import alvarez.oscar.crimedatasf.R;
import alvarez.oscar.crimedatasf.map.render.CustomIconRendered;

/**
 * Created by Oscar Álvarez on 14/01/16.
 */
public class MapUtil {
    private ClusterManager<MapItem> mClusterManager;
    private Context context;
    //TODO: Set correct San Francisco Position
    private LatLng SAN_FRANCISCO_POSITION = new LatLng(37.757815, -122.50764);
    private GoogleMap map;


    public MapUtil(GoogleMap map, Context context) {
        this.context = context;
        this.map = map;
        this.map.moveCamera(CameraUpdateFactory.newLatLngZoom(SAN_FRANCISCO_POSITION, 12));
        mClusterManager = new ClusterManager<MapItem>(context, this.map);

        this.map.setOnCameraChangeListener(mClusterManager);
        this.map.setOnMarkerClickListener(mClusterManager);

        mClusterManager.setRenderer(new CustomIconRendered(context, map, mClusterManager));
        map.setInfoWindowAdapter(mClusterManager.getMarkerManager());

        map.setOnInfoWindowClickListener(mClusterManager);

        mClusterManager.setOnClusterItemInfoWindowClickListener(
            new ClusterManager.OnClusterItemInfoWindowClickListener<MapItem>() {
                @Override
                public void onClusterItemInfoWindowClick(MapItem mapItem) {

                }
            });
        mClusterManager.setOnClusterItemClickListener(
            new ClusterManager.OnClusterItemClickListener<MapItem>() {
                @Override
                public boolean onClusterItemClick(MapItem mapItem) {
                    return false;
                }
            });

        /*
        addItemWithPriority(SAN_FRANCISCO_POSITION, 5, "SF");
        for (int i = 0; i < 10; i++) {
            double offset = i / 60d;
            double lat = SAN_FRANCISCO_POSITION.latitude + offset;
            double lng = SAN_FRANCISCO_POSITION.longitude + offset;
            addItemWithPriority(new LatLng(lat, lng), i, "SF "+i);
        }*/
    }

    public void addItemWithPriority(LatLng position, int priority, String title, String description) {
        MapItem offsetItem = new MapItem(position, priority);
        mClusterManager.addItem(offsetItem);

        /*
        Bitmap ob = BitmapFactory.decodeResource(context.getResources(),R.drawable.marker);
        ob = Bitmap.createScaledBitmap(ob, ob.getWidth()/3, ob.getHeight()/3, false);
        Bitmap obm = Bitmap.createBitmap(ob.getWidth(), ob.getHeight(), ob.getConfig());
        Canvas canvas = new Canvas(obm);
        Paint paint = new Paint();
        paint.setColorFilter(new PorterDuffColorFilter(
            context.getResources().getColor(getColorByPriority(priority)),PorterDuff.Mode.SRC_ATOP));
        canvas.drawBitmap(ob, 0f, 0f, paint);*/

        /*map.addMarker(new MarkerOptions()
                          .position(position)
                          .title(description).snippet("CITY")
                          .icon(BitmapDescriptorFactory.fromBitmap(obm)));*/
    }

}
