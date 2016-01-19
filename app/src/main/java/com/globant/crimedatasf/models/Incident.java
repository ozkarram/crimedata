package com.globant.crimedatasf.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Oscar Álvarez on 15/01/16.
 */
public class Incident implements Parcelable{

    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("dayofweek")
    @Expose
    private String dayofweek;
    @SerializedName("descript")
    @Expose
    private String descript;
    @SerializedName("incidntnum")
    @Expose
    private String incidntnum;
    @SerializedName("location")
    @Expose
    private LocationModel location;
    @SerializedName("pddistrict")
    @Expose
    private String pddistrict;
    @SerializedName("pdid")
    @Expose
    private String pdid;
    @SerializedName("resolution")
    @Expose
    private String resolution;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("x")
    @Expose
    private String x;
    @SerializedName("y")
    @Expose
    private String y;

    /**
     * @return The address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     *     The address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return The category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category
     *     The category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return The date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date
     *     The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return The dayofweek
     */
    public String getDayofweek() {
        return dayofweek;
    }

    /**
     * @param dayofweek
     *     The dayofweek
     */
    public void setDayofweek(String dayofweek) {
        this.dayofweek = dayofweek;
    }

    /**
     * @return The descript
     */
    public String getDescript() {
        return descript;
    }

    /**
     * @param descript
     *     The descript
     */
    public void setDescript(String descript) {
        this.descript = descript;
    }

    /**
     * @return The incidntnum
     */
    public String getIncidntnum() {
        return incidntnum;
    }

    /**
     * @param incidntnum
     *     The incidntnum
     */
    public void setIncidntnum(String incidntnum) {
        this.incidntnum = incidntnum;
    }

    /**
     * @return The location
     */
    public LocationModel getLocation() {
        return location;
    }

    /**
     * @param location
     *     The location
     */
    public void setLocation(LocationModel location) {
        this.location = location;
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

    /**
     * @return The pdid
     */
    public String getPdid() {
        return pdid;
    }

    /**
     * @param pdid
     *     The pdid
     */
    public void setPdid(String pdid) {
        this.pdid = pdid;
    }

    /**
     * @return The resolution
     */
    public String getResolution() {
        return resolution;
    }

    /**
     * @param resolution
     *     The resolution
     */
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    /**
     * @return The time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time
     *     The time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return The x
     */
    public String getX() {
        return x;
    }

    /**
     * @param x
     *     The x
     */
    public void setX(String x) {
        this.x = x;
    }

    /**
     * @return The y
     */
    public String getY() {
        return y;
    }

    /**
     * @param y
     *     The y
     */
    public void setY(String y) {
        this.y = y;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(address);
        dest.writeString(category);
        dest.writeString(date);
        dest.writeString(dayofweek);
        dest.writeString(descript);
        dest.writeString(incidntnum);
        dest.writeParcelable(location, 0);
        dest.writeString(pddistrict);
        dest.writeString(pdid);
        dest.writeString(resolution);
        dest.writeString(time);
        dest.writeString(x);
        dest.writeString(y);
    }

    public static final Parcelable.Creator<Incident> CREATOR
        = new Parcelable.Creator<Incident>() {
        public Incident createFromParcel(Parcel in) {
            return new Incident(in);
        }

        public Incident[] newArray(int size) {
            return new Incident[size];
        }
    };

    private Incident(Parcel in) {
        address = in.readString();
        category = in.readString();
        date = in.readString();
        dayofweek = in.readString();
        descript = in.readString();
        incidntnum = in.readString();
        location = in.readParcelable(LocationModel.class.getClassLoader());
        pddistrict = in.readString();
        pdid = in.readString();
        resolution = in.readString();
        time = in.readString();
        x = in.readString();
        y = in.readString();
    }
}
