package com.sinau.simikmiriskina;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sinau.simikmiriskina.adapter.MataKuliahRecyclerViewAdapter;
import com.sinau.simikmiriskina.api.ApiClient;
import com.sinau.simikmiriskina.api.MataKuliahApiInterface;
import com.sinau.simikmiriskina.model.Matakuliah;
import com.sinau.simikmiriskina.model.MataKuliahResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MataKuliahFragment extends Fragment{
    private View myView;

    private List<Matakuliah> mataKuliahs = new ArrayList<>();
    private MataKuliahRecyclerViewAdapter viewAdapter;

    private ProgressBar progressBar;
    private RecyclerView recyclerView;

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
