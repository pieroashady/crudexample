package com.pieroashady.crudapps.Models.deletedata;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatusDelete implements Serializable, Parcelable
{

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<StatusDelete> CREATOR = new Creator<StatusDelete>() {


        @SuppressWarnings({
                "unchecked"
        })
        public StatusDelete createFromParcel(Parcel in) {
            return new StatusDelete(in);
        }

        public StatusDelete[] newArray(int size) {
            return (new StatusDelete[size]);
        }

    }
            ;
    private final static long serialVersionUID = 2412736863529073883L;

    protected StatusDelete(Parcel in) {
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public StatusDelete() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
    }

    public int describeContents() {
        return 0;
    }

}