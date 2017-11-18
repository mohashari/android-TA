package com.sinau.simikmiriskina.api;

import com.sinau.simikmiriskina.model.MataKuliahResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MataKuliahApiInterface {

    @GET("matakuliah")
    Call<MataKuliahResponse> view(
    );

}
