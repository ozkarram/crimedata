package alvarez.oscar.crimedatasf.map;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by Oscar Álvarez on 14/01/16.
 */
public class MapItem implements ClusterItem {
    private final LatLng position;
    private final int priority;

    public MapItem(LatLng position, int priority) {
        this.position = position;
        this.priority = priority;
    }

    @Override
    public LatLng getPosition() {
        return position;
    }

    public int getPriority() {
        return priority;
    }
}
