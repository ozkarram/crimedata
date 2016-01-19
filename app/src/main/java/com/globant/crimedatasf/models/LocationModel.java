package com.globant.crimedatasf.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Álvarez on 15/01/16.
 */
public class LocationModel implements Parcelable{

    private int data;

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("coordinates")
    @Expose
    private List<Double> coordinates = new ArrayList<Double>();

    /**
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return The coordinates
     */
    public List<Double> getCoordinates() {
        return coordinates;
    }

    /**
     * @param coordinates
     *     The coordinates
     */
    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(data);
    }

    public static final Parcelable.Creator<LocationModel> CREATOR
        = new Parcelable.Creator<LocationModel>() {
        public LocationModel createFromParcel(Parcel in) {
            return new LocationModel(in);
        }

        public LocationModel[] newArray(int size) {
            return new LocationModel[size];
        }
    };

    private LocationModel(Parcel in) {
        data = in.readInt();
    }
}
