package com.pieroashady.crudapps.Models.insertdata;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Serializable, Parcelable
{

    @SerializedName("nama_kategori")
    @Expose
    private String namaKategori;
    @SerializedName("id_kategori")
    @Expose
    private Integer idKategori;
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
    private final static long serialVersionUID = 4606410234775840472L;

    protected Data(Parcel in) {
        this.namaKategori = ((String) in.readValue((String.class.getClassLoader())));
        this.idKategori = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Data() {
    }

    public String getNamaKategori() {
        return namaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        this.namaKategori = namaKategori;
    }

    public Integer getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(Integer idKategori) {
        this.idKategori = idKategori;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(namaKategori);
        dest.writeValue(idKategori);
    }

    public int describeContents() {
        return 0;
    }

}