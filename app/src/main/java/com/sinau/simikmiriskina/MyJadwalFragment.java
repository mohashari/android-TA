package com.sinau.simikmiriskina;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sinau.simikmiriskina.adapter.JadwalRecyclerViewAdapter;
import com.sinau.simikmiriskina.adapter.MataKuliahRecyclerViewAdapter;
import com.sinau.simikmiriskina.api.ApiClient;
import com.sinau.simikmiriskina.api.JadwalApiInterface;
import com.sinau.simikmiriskina.api.MataKuliahApiInterface;
import com.sinau.simikmiriskina.model.MataKuliahResponse;
import com.sinau.simikmiriskina.model.Matakuliah;
import com.sinau.simikmiriskina.model.ResultMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MyJadwalFragment extends Fragment {
    private View myView;

    private List<Matakuliah> mataKuliahs = new ArrayList<>();
    private JadwalRecyclerViewAdapter viewAdapter;

    private SwipeRefreshLayout swipeRefresh;

    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    SessionManager session ;
    HashMap<String, String> user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        myView = inflater.inflate(R.layout.fragment_my_jadwal, container, false);

        progressBar = (ProgressBar) myView.findViewById(R.id.progress_bar);
        recyclerView = (RecyclerView) myView.findViewById(R.id.recycleView);
        swipeRefresh = (SwipeRefreshLayout) myView.findViewById(R.id.swiperefresh);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshItem();
            }

            private void refreshItem() {
                loadDataPasien();
                onLoadItem();
            }

            private void onLoadItem() {
                swipeRefresh.setRefreshing(false);
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(viewAdapter);

        session = new SessionManager(myView.getContext());
        user = session.getUserDetails();

        loadDataPasien();

        return myView;
    }


    private void loadDataPasien(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MataKuliahApiInterface api = retrofit.create(MataKuliahApiInterface.class);
        Call<MataKuliahResponse> call = api.getMyJadwal(user.get(SessionManager.kunci_email));

        call.enqueue(new Callback<MataKuliahResponse>() {
            @Override
            public void onResponse(Call<MataKuliahResponse> call, Response<MataKuliahResponse> response) {
                String message = response.body().getMessage();
                progressBar.setVisibility(View.GONE);

                if(message.equals("OK")){
                    mataKuliahs = response.body().getResult();
                    viewAdapter = new JadwalRecyclerViewAdapter(getActivity(), mataKuliahs);
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
