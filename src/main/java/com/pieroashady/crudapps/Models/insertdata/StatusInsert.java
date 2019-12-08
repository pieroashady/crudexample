package com.pieroashady.crudapps.Models.insertdata;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pieroashady.crudapps.Models.insertdata.Data;
import com.pieroashady.crudapps.Models.viewdata.StatusGet;

public class StatusInsert implements Serializable, Parcelable
{

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private Data data;
    public final static Parcelable.Creator<StatusInsert> CREATOR = new Creator<StatusInsert>() {


        @SuppressWarnings({
                "unchecked"
        })
        public StatusInsert createFromParcel(Parcel in) {
            return new StatusInsert(in);
        }

        public StatusInsert[] newArray(int size) {
            return (new StatusInsert[size]);
        }

    }
            ;
    private final static long serialVersionUID = -3989023813109765621L;

    protected StatusInsert(Parcel in) {
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.data = ((Data) in.readValue((Data.class.getClassLoader())));
    }

    public StatusInsert() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(data);
    }

    public int describeContents() {
        return 0;
    }

}