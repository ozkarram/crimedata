package com.globant.crimedatasf.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Oscar Álvarez on 15/01/2016.
 */
public class District implements Parcelable {

    @SerializedName("COUNT")
    @Expose
    private String COUNT;
    @SerializedName("pddistrict")
    @Expose
    private String pddistrict;

    /**
     * @return The COUNT
     */
    public String getCount() {
        return COUNT;
    }

    /**
     * @param COUNT
     *     The COUNT
     */
    public void setCount(String COUNT) {
        this.COUNT = COUNT;
    }

    /**
     * @return The pddistrict
     */
    public String getPddistrict() {
        return pddistrict;
    }

    /**
     * @param pddistrict
     *     The pddistrict
     */
    public void setPddistrict(String pddistrict) {
        this.pddistrict = pddistrict;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(COUNT);
        dest.writeString(pddistrict);
    }

    public static final Parcelable.Creator<District> CREATOR
        = new Parcelable.Creator<District>() {
        public District createFromParcel(Parcel in) {
            return new District(in);
        }

        public District[] newArray(int size) {
            return new District[size];
        }
    };

    private District(Parcel in) {
        COUNT = in.readString();
        pddistrict = in.readString();
    }

}
