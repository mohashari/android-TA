package com.sinau.simikmiriskina.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sinau.simikmiriskina.R;
import com.sinau.simikmiriskina.SessionManager;
import com.sinau.simikmiriskina.api.ApiClient;
import com.sinau.simikmiriskina.api.JadwalApiInterface;
import com.sinau.simikmiriskina.model.AddJadwal;
import com.sinau.simikmiriskina.model.Matakuliah;
import com.sinau.simikmiriskina.model.ResultMessage;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class JadwalRecyclerViewAdapter extends RecyclerView.Adapter<JadwalRecyclerViewAdapter.ViewHolder>{
    private Context context;
    private List<Matakuliah> mataKuliahs;

    public JadwalRecyclerViewAdapter(Context context, List<Matakuliah> mataKuliahs) {
        this.context = context;
        this.mataKuliahs = mataKuliahs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_mata_kuliah, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Matakuliah p = mataKuliahs.get(position);
        holder.txtId.setText(p.getId());
        holder.txtNama.setText(p.getName());
        holder.txtSemester.setText(p.getSemester());
        holder.txtSks.setText(p.getSks());
        holder.txtHari.setText(p.getHari());
        holder.txtVersion.setText(p.getVersion());
    }

    @Override
    public int getItemCount() {
        return mataKuliahs.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtId;
        private TextView txtNama;
        private TextView txtSemester;
        private TextView txtVersion;
        private TextView txtSks;
        private TextView txtHari;

        HashMap<String, String> user;
        SessionManager session ;

        public ViewHolder(View itemView) {
            super(itemView);

            txtId = (TextView) itemView.findViewById(R.id.txt_id);
            txtNama = (TextView) itemView.findViewById(R.id.txt_name);
            txtSemester = (TextView) itemView.findViewById(R.id.txt_semester);
            txtVersion = (TextView) itemView.findViewById(R.id.txt_version);
            txtSks = (TextView) itemView.findViewById(R.id.txt_sks);
            txtHari = (TextView) itemView.findViewById(R.id.txt_hari);

            session = new SessionManager(itemView.getContext());
            user = session.getUserDetails();

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(final View view) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
            alertDialogBuilder.setTitle("Konfirmasi");
            alertDialogBuilder
                    .setMessage("Apakah anda yakin akan menghapus mata kuliah ini ?")
                    .setCancelable(false)
                    .setPositiveButton("Tambah",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            Toast.makeText(context,
                                    user.get(SessionManager.kunci_email) +" dan "+ txtId.getText().toString(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Batal",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

        public void hapusJadwal(){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiClient.URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            JadwalApiInterface api = retrofit.create(JadwalApiInterface.class);
            Call<ResultMessage> call = api.delete(
                    user.get(SessionManager.kunci_email), txtId.getText().toString()
            );

            call.enqueue(new Callback<ResultMessage>() {
                @Override
                public void onResponse(Call<ResultMessage> call, Response<ResultMessage> response) {
                    String message = response.body().getMessage().toString();

                    if(message.equals("OK")){
                        Toast.makeText(context,
                                "Matakuliah berhasil dihapus",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context.getApplicationContext(),
                                response.body().getResult().toString(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResultMessage> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(context.getApplicationContext(), "Jaringan Error", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
