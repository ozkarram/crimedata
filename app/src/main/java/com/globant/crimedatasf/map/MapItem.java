package com.globant.crimedatasf.map;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by Oscar Álvarez on 14/01/16.
 */
public class MapItem implements ClusterItem {
    private final LatLng position;
    private final int priority;
    private String title;
    private String description;

    public MapItem(LatLng position, int priority, String title, String description) {
        this.position = position;
        this.priority = priority;
        this.title = title;
        this.description = description;
    }

    @Override
    public LatLng getPosition() {
        return position;
    }

    public int getPriority() {
        return priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
