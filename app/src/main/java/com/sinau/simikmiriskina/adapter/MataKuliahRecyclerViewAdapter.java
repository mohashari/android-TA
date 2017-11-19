package com.sinau.simikmiriskina.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.sinau.simikmiriskina.R;
import com.sinau.simikmiriskina.model.Matakuliah;

import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;


public class MataKuliahRecyclerViewAdapter extends RecyclerView.Adapter<MataKuliahRecyclerViewAdapter.ViewHolder>{
    private Context context;
    private List<Matakuliah> mataKuliahs;

    public MataKuliahRecyclerViewAdapter(Context context, List<Matakuliah> mataKuliahs) {
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

        public ViewHolder(View itemView) {
            super(itemView);

            txtId = (TextView) itemView.findViewById(R.id.txt_id);
            txtNama = (TextView) itemView.findViewById(R.id.txt_name);
            txtSemester = (TextView) itemView.findViewById(R.id.txt_semester);
            txtVersion = (TextView) itemView.findViewById(R.id.txt_version);
            txtSks = (TextView) itemView.findViewById(R.id.txt_sks);
            txtHari = (TextView) itemView.findViewById(R.id.txt_hari);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(final View view) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
            alertDialogBuilder.setTitle("Konfirmasi");
            alertDialogBuilder
                    .setMessage("Apakah anda yakin akan mengambil mata kuliah ini ?")
                    .setCancelable(false)
                    .setPositiveButton("Tambah",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            Toast.makeText(view.getContext(),
                                    "Berhasil di tambah " + txtId.getText().toString(),
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
    }
}
