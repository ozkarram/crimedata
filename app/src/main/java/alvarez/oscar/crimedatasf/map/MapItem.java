package alvarez.oscar.crimedatasf.map;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by Oscar Álvarez on 14/01/16.
 */
public class MapItem implements ClusterItem {
    private final LatLng position;

    public MapItem(LatLng position) {
        this.position = position;
    }

    @Override
    public LatLng getPosition() {
        return position;
    }
}
