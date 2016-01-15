package alvarez.oscar.crimedatasf.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Oscar Álvarez on 15/01/16.
 */
public class Incident {

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
     *
     * @return
     *     The address
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     *     The address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return
     *     The category
     */
    public String getCategory() {
        return category;
    }

    /**
     *
     * @param category
     *     The category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     *
     * @return
     *     The date
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     *     The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @return
     *     The dayofweek
     */
    public String getDayofweek() {
        return dayofweek;
    }

    /**
     *
     * @param dayofweek
     *     The dayofweek
     */
    public void setDayofweek(String dayofweek) {
        this.dayofweek = dayofweek;
    }

    /**
     *
     * @return
     *     The descript
     */
    public String getDescript() {
        return descript;
    }

    /**
     *
     * @param descript
     *     The descript
     */
    public void setDescript(String descript) {
        this.descript = descript;
    }

    /**
     *
     * @return
     *     The incidntnum
     */
    public String getIncidntnum() {
        return incidntnum;
    }

    /**
     *
     * @param incidntnum
     *     The incidntnum
     */
    public void setIncidntnum(String incidntnum) {
        this.incidntnum = incidntnum;
    }

    /**
     *
     * @return
     *     The location
     */
    public LocationModel getLocation() {
        return location;
    }

    /**
     *
     * @param location
     *     The location
     */
    public void setLocation(LocationModel location) {
        this.location = location;
    }

    /**
     *
     * @return
     *     The pddistrict
     */
    public String getPddistrict() {
        return pddistrict;
    }

    /**
     *
     * @param pddistrict
     *     The pddistrict
     */
    public void setPddistrict(String pddistrict) {
        this.pddistrict = pddistrict;
    }

    /**
     *
     * @return
     *     The pdid
     */
    public String getPdid() {
        return pdid;
    }

    /**
     *
     * @param pdid
     *     The pdid
     */
    public void setPdid(String pdid) {
        this.pdid = pdid;
    }

    /**
     *
     * @return
     *     The resolution
     */
    public String getResolution() {
        return resolution;
    }

    /**
     *
     * @param resolution
     *     The resolution
     */
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    /**
     *
     * @return
     *     The time
     */
    public String getTime() {
        return time;
    }

    /**
     *
     * @param time
     *     The time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     *
     * @return
     *     The x
     */
    public String getX() {
        return x;
    }

    /**
     *
     * @param x
     *     The x
     */
    public void setX(String x) {
        this.x = x;
    }

    /**
     *
     * @return
     *     The y
     */
    public String getY() {
        return y;
    }

    /**
     *
     * @param y
     *     The y
     */
    public void setY(String y) {
        this.y = y;
    }

}
