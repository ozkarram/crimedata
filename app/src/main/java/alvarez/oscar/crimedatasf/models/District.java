package alvarez.oscar.crimedatasf.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Oscar Álvarez on 15/01/2016.
 */
public class District {

    @SerializedName("COUNT")
    @Expose
    private String COUNT;
    @SerializedName("pddistrict")
    @Expose
    private String pddistrict;

    /**
     *
     * @return
     * The COUNT
     */
    public String getCount() {
        return COUNT;
    }

    /**
     *
     * @param COUNT
     * The COUNT
     */
    public void setCount(String COUNT) {
        this.COUNT = COUNT;
    }

    /**
     *
     * @return
     * The pddistrict
     */
    public String getPddistrict() {
        return pddistrict;
    }

    /**
     *
     * @param pddistrict
     * The pddistrict
     */
    public void setPddistrict(String pddistrict) {
        this.pddistrict = pddistrict;
    }

}
