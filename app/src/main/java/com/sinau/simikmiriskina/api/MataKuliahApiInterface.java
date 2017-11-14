package com.sinau.simikmiriskina.api;

import com.sinau.simikmiriskina.model.MataKuliahValue;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface MataKuliahApiInterface {

    @GET("matakuliah")
    Call<MataKuliahValue> view();

}
