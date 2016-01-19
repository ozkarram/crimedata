package com.globant.crimedatasf.map;

import android.content.Context;

import com.globant.crimedatasf.map.render.CustomIconRendered;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterManager;

/**
 * Created by Oscar Álvarez on 14/01/16.
 */
public class MapUtil {
    private ClusterManager<MapItem> mClusterManager;
    private LatLng SAN_FRANCISCO_POSITION = new LatLng(37.7658217, -122.4468723);
    private GoogleMap map;


    public MapUtil(GoogleMap map, Context context) {
        this.map = map;
        this.map.moveCamera(CameraUpdateFactory.newLatLngZoom(SAN_FRANCISCO_POSITION, 12.0f));
        mClusterManager = new ClusterManager<>(context, this.map);

        this.map.setOnCameraChangeListener(mClusterManager);
        this.map.setOnMarkerClickListener(mClusterManager);

        mClusterManager.setRenderer(new CustomIconRendered(context, map, mClusterManager));
        map.setInfoWindowAdapter(mClusterManager.getMarkerManager());
        map.setOnInfoWindowClickListener(mClusterManager);

        map.getUiSettings().setMapToolbarEnabled(false);

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
    }

    public void addItemWithPriority(LatLng position, int priority, String title,
                                    String description) {
        MapItem offsetItem = new MapItem(position, priority, title, description);
        mClusterManager.addItem(offsetItem);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(SAN_FRANCISCO_POSITION, 11.5f));
        mClusterManager.cluster();
    }

    public void clearMap() {
        map.clear();
        mClusterManager.clearItems();
    }

}
