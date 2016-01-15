package alvarez.oscar.crimedatasf.map.render;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import alvarez.oscar.crimedatasf.R;
import alvarez.oscar.crimedatasf.map.MapItem;

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
        Bitmap ob = BitmapFactory.decodeResource(context.getResources(), R.drawable.marker);
        ob = Bitmap.createScaledBitmap(ob, ob.getWidth()/3, ob.getHeight()/3, false);
        Bitmap obm = Bitmap.createBitmap(ob.getWidth(), ob.getHeight(), ob.getConfig());
        Canvas canvas = new Canvas(obm);
        Paint paint = new Paint();
        paint.setColorFilter(new PorterDuffColorFilter(
            context.getResources().getColor(getColorByPriority(item.getPriority())), PorterDuff.Mode.SRC_ATOP));
        canvas.drawBitmap(ob, 0f, 0f, paint);
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(obm));
    }

    @Override
    protected void onBeforeClusterRendered(Cluster<MapItem> cluster, MarkerOptions markerOptions) {
        final Drawable clusterIcon = context.getResources().getDrawable(R.drawable.ic_lens_black_24dp);
        clusterIcon.setColorFilter(context.getResources().getColor(R.color.p7), PorterDuff.Mode.SRC_ATOP);
        mClusterIconGenerator.setBackground(clusterIcon);

        //modify padding for one or two digit numbers
        if (cluster.getSize() < 10) {
            mClusterIconGenerator.setContentPadding(20, 20, 20, 20);
        }
        else {
            mClusterIconGenerator.setContentPadding(30, 30, 30, 30);
        }

        Bitmap icon = mClusterIconGenerator.makeIcon(String.valueOf(cluster.getSize()));
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));
    }

    @Override
    protected void onClusterItemRendered(MapItem clusterItem, Marker marker) {
        super.onClusterItemRendered(clusterItem, marker);
    }

    private int getColorByPriority(int priority) {
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

    /*private int getColorBySize(Collection<MapItem> cluster) {
        ArrayList<Integer> counter = new ArrayList<>();
        for (int i=0; i<8; i++) {
            counter.add(0);
        }

        Iterator iterator = cluster.iterator();
        while (iterator.hasNext()) {
            MapItem item = ((MapItem) iterator.next());
            counter.add(item.getPriority(), counter.get(item.getPriority()) + 1);
        }
    }*/
}
