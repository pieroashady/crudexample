package com.pieroashady.crudapps.Models.viewdata;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Datum implements Serializable, Parcelable {

    @SerializedName("id_kategori")
    @Expose
    private Integer idKategori;
    @SerializedName("nama_kategori")
    @Expose
    private String namaKategori;
    public final static Creator<Datum> CREATOR = new Creator<Datum>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Datum createFromParcel(Parcel in) {
            return new Datum(in);
        }

        public Datum[] newArray(int size) {
            return (new Datum[size]);
        }

    };

    private final static long serialVersionUID = 2464183078647087527L;

    protected Datum(Parcel in) {
        this.idKategori = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.namaKategori = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Datum() {
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