package com.sinau.simikmiriskina.api;

import com.sinau.simikmiriskina.model.MataKuliahResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MataKuliahApiInterface {

    @GET("matakuliah")
    Call<MataKuliahResponse> view();

}
