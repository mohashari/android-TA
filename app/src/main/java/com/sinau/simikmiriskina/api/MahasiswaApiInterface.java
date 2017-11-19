package com.sinau.simikmiriskina.api;

import com.sinau.simikmiriskina.model.LoginRequest;
import com.sinau.simikmiriskina.model.Mahasiswa;
import com.sinau.simikmiriskina.model.MahasiswaResponse;
import com.sinau.simikmiriskina.model.MataKuliahResponse;
import com.sinau.simikmiriskina.model.ResultMessage;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MahasiswaApiInterface {

    @Headers("Content-Type: application/json")
    @GET("mahasiswa")
    Call<MahasiswaResponse> view();


    @Headers("Content-Type: application/json")
    @POST("mahasiswa/login")
    Call<ResultMessage> login(@Body LoginRequest loginRequest);

    @Headers("Content-Type: application/json")
    @GET("mahasiswa/{id}")
    Call<MahasiswaResponse> getMahasiswa(@Path("id") String secureId);


    @Headers("Content-Type: application/json")
    @GET("mahasiswa/forgot-password")
    Call<ResultMessage> getForgotPassword(@Query("email") String email);

    @Headers("Content-Type: application/json")
    @POST("mahasiswa")
    Call<ResultMessage> register(@Body Mahasiswa mahasiswa);

}
