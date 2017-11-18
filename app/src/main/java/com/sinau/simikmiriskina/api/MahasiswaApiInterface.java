package com.sinau.simikmiriskina.api;

import com.sinau.simikmiriskina.model.MahasiswaResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface MahasiswaApiInterface {

    @GET("mahasiswa")
    Call<MahasiswaResponse> view();

    @GET("mahasiswa/{id}")
    @Headers("Content-Type: application/json")
    Call<MahasiswaResponse> getMahasiswa(@Path("id") String secureId);


}
