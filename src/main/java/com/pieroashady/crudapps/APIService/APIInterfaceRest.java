package com.pieroashady.crudapps.APIService;

import com.pieroashady.crudapps.Models.deletedata.StatusDelete;
import com.pieroashady.crudapps.Models.getdataid.StatusId;
import com.pieroashady.crudapps.Models.insertdata.StatusInsert;
import com.pieroashady.crudapps.Models.updatedata.StatusUpdate;
import com.pieroashady.crudapps.Models.viewdata.StatusGet;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIInterfaceRest {


    @GET("view_kategori")
    Call<StatusGet> getData();

    @GET("kategori_id/{id_kategori}")
    Call<StatusId> getDataById(@Path("id_kategori") int id);

    @FormUrlEncoded
    @POST("tambah_kategori")
    Call<StatusInsert> saveData(@Field("nama_kategori") String nama_kategori);

    @FormUrlEncoded
    @PUT("update_kategori/{id_kategori}")
    Call<StatusUpdate> updateData(@Path("id_kategori") int biodataId,
                                  @Field("nama_kategori") String nama_kategori);

    @DELETE("delete_kategori/{id_kategori}")
    Call<StatusDelete> deleteData(@Path("id_kategori") int id);

}
