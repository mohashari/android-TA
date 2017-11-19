package com.sinau.simikmiriskina.api;

import com.sinau.simikmiriskina.model.AddJadwal;
import com.sinau.simikmiriskina.model.ResultMessage;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface JadwalApiInterface {

    @Headers("Content-Type: application/json")
    @POST("mahasiswa/jadwal")
    Call<ResultMessage> login(@Body AddJadwal addJadwal);
}
