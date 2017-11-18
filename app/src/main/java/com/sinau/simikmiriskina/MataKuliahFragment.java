package com.sinau.simikmiriskina;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sinau.simikmiriskina.adapter.MataKuliahRecyclerViewAdapter;
import com.sinau.simikmiriskina.api.ApiClient;
import com.sinau.simikmiriskina.api.JadwalApiInterface;
import com.sinau.simikmiriskina.api.MahasiswaApiInterface;
import com.sinau.simikmiriskina.api.MataKuliahApiInterface;
import com.sinau.simikmiriskina.common.ArrayGetMatkul;
import com.sinau.simikmiriskina.model.AddJadwal;
import com.sinau.simikmiriskina.model.LoginRequest;
import com.sinau.simikmiriskina.model.Mahasiswa;
import com.sinau.simikmiriskina.model.Matakuliah;
import com.sinau.simikmiriskina.model.MataKuliahResponse;
import com.sinau.simikmiriskina.model.ResultMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class MataKuliahFragment extends Fragment{
    private View myView;

    private List<Matakuliah> mataKuliahs = new ArrayList<>();
    private MataKuliahRecyclerViewAdapter viewAdapter;

    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    private FloatingActionButton fab;

    SessionManager session ;
    HashMap<String, String> user;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_mata_kuliah, container, false);

        progressBar = (ProgressBar) myView.findViewById(R.id.progress_bar);
        recyclerView = (RecyclerView) myView.findViewById(R.id.recycleView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(viewAdapter);

        loadDataPasien();

        fab = (FloatingActionButton) myView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(ApiClient.URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                JadwalApiInterface api = retrofit.create(JadwalApiInterface.class);

                String idMahasiswa = user.get(SessionManager.kunci_email);
                List<String> idMatakuliah = ArrayGetMatkul.idMatkul;

                Call<ResultMessage> call = api.login(idMahasiswa, idMatakuliah);
                call.enqueue(new Callback<ResultMessage>() {
                    @Override
                    public void onResponse(Call<ResultMessage> call, Response<ResultMessage> response) {
                        String message = response.body().getMessage();

                        if(message.equals("OK")){
                            Toast.makeText(getActivity().getApplicationContext(),
                                    response.body().getResult().toString(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResultMessage> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(getActivity().getApplicationContext(),
                                "GAGAL",
                                Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        return myView;
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    private void loadDataPasien(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MataKuliahApiInterface api = retrofit.create(MataKuliahApiInterface.class);
        Call<MataKuliahResponse> call = api.view();

        call.enqueue(new Callback<MataKuliahResponse>() {
            @Override
            public void onResponse(Call<MataKuliahResponse> call, Response<MataKuliahResponse> response) {
                String message = response.body().getMessage();
                progressBar.setVisibility(View.GONE);

                if(message.equals("OK")){
                    mataKuliahs = response.body().getResult();
                    viewAdapter = new MataKuliahRecyclerViewAdapter(getActivity(), mataKuliahs);
                    recyclerView.setAdapter(viewAdapter);
                }
            }

            @Override
            public void onFailure(Call<MataKuliahResponse> call, Throwable t) {
                t.printStackTrace();
                progressBar.setVisibility(myView.GONE);
                Toast.makeText(getActivity().getApplicationContext(), "Jaringan Error !", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
