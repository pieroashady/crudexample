package com.pieroashady.crudapps.Models.viewdata;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatusGet implements Serializable, Parcelable
{

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    public final static Parcelable.Creator<StatusGet> CREATOR = new Creator<StatusGet>() {


        @SuppressWarnings({
                "unchecked"
        })
        public StatusGet createFromParcel(Parcel in) {
            return new StatusGet(in);
        }

        public StatusGet[] newArray(int size) {
            return (new StatusGet[size]);
        }

    }
            ;
    private final static long serialVersionUID = 2419187263709911726L;

    protected StatusGet(Parcel in) {
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.data, (com.pieroashady.crudapps.Models.viewdata.Datum.class.getClassLoader()));
    }

    public StatusGet() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeList(data);
    }

    public int describeContents() {
        return 0;
    }

}