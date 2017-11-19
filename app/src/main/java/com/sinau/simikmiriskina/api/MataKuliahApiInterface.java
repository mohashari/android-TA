package com.sinau.simikmiriskina.api;

import com.sinau.simikmiriskina.model.MataKuliahResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface MataKuliahApiInterface {

    @GET("matakuliah/list-jadwal")
    Call<MataKuliahResponse> view();


    @Headers("Content-Type: application/json")
    @GET("mahasiswa/my-jadwal")
    Call<MataKuliahResponse> getMyJadwal(@Field("id") String search);

}
