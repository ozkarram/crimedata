package alvarez.oscar.crimedatasf.map;

import android.content.Context;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterManager;

/**
 * Created by Oscar Álvarez on 14/01/16.
 */
public class MapUtil {
    private ClusterManager<MapItem> mClusterManager;
    private ClusterManager clusterManager;
    //TODO: Set correct San Francisco Position
    private LatLng SAN_FRANCISCO_POSITION = new LatLng(51.503186, -0.126446);


    public MapUtil(GoogleMap map, Context context) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(SAN_FRANCISCO_POSITION, 10));
        mClusterManager = new ClusterManager<MapItem>(context, map);
        map.setOnCameraChangeListener(mClusterManager);
        map.setOnMarkerClickListener(mClusterManager);
    }

    public void addItem(LatLng position) {
        MapItem offsetItem = new MapItem(position);
        mClusterManager.addItem(offsetItem);
    }
}
