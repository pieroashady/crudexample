package com.pieroashady.crudapps.Models.getdataid;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Serializable, Parcelable
{

    @SerializedName("id_kategori")
    @Expose
    private Integer idKategori;
    @SerializedName("nama_kategori")
    @Expose
    private String namaKategori;
    public final static Parcelable.Creator<Data> CREATOR = new Creator<Data>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        public Data[] newArray(int size) {
            return (new Data[size]);
        }

    }
            ;
    private final static long serialVersionUID = 5375129933028429750L;

    protected Data(Parcel in) {
        this.idKategori = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.namaKategori = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Data() {
    }

    public Integer getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(Integer idKategori) {
        this.idKategori = idKategori;
    }

    public String getNamaKategori() {
        return namaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        this.namaKategori = namaKategori;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(idKategori);
        dest.writeValue(namaKategori);
    }

    public int describeContents() {
        return 0;
    }

}