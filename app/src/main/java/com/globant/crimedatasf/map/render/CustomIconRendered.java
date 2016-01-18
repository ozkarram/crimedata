package com.globant.crimedatasf.map.render;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

import com.globant.crimedatasf.map.MapItem;
import com.globant.crimedatasf.util.Util;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;

import alvarez.oscar.crimedatasf.R;

/**
 * Created by Oscar Álvarez on 14/01/2016.
 */
public class CustomIconRendered extends DefaultClusterRenderer<MapItem> {
    private Context context;
    private final IconGenerator mClusterIconGenerator;

    public CustomIconRendered(Context context,
                              GoogleMap map,
                              ClusterManager<MapItem> clusterManager) {
        super(context, map, clusterManager);
        this.context = context;
        mClusterIconGenerator = new IconGenerator(context);
    }

    @Override
    protected void onBeforeClusterItemRendered(MapItem item, MarkerOptions markerOptions) {
        Bitmap obm = Util.getTintBitmap(context, R.drawable.ic_room_black_24dp,
                                        Util.getColorByPriority(item.getPriority()));
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(obm));
        markerOptions.title(item.getTitle()).snippet(item.getDescription());
    }

    @Override
    protected void onBeforeClusterRendered(Cluster<MapItem> cluster, MarkerOptions markerOptions) {
        final Drawable clusterIcon = context.getResources()
            .getDrawable(R.drawable.ic_lens_black_24dp);
        clusterIcon.setColorFilter(context.getResources().getColor(R.color.p7),
                                   PorterDuff.Mode.SRC_ATOP);
        mClusterIconGenerator.setBackground(clusterIcon);

        //modify padding for one or two digit numbers
        if (cluster.getSize() < 10) {
            mClusterIconGenerator.setContentPadding(25, 20, 25, 20);
        } else {
            mClusterIconGenerator.setContentPadding(30, 30, 30, 30);
        }

        Bitmap icon = mClusterIconGenerator.makeIcon(String.valueOf(cluster.getSize()));
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon))
            .title(new StringBuilder().append(cluster.getSize())
                       .append(" incidents here").toString());
    }

    @Override
    protected void onClusterItemRendered(MapItem clusterItem, Marker marker) {
        super.onClusterItemRendered(clusterItem, marker);
    }

}
