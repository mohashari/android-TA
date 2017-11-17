package com.sinau.simikmiriskina.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sinau.simikmiriskina.R;
import com.sinau.simikmiriskina.model.MataKuliah;

import java.util.List;


public class MataKuliahRecyclerViewAdapter extends RecyclerView.Adapter<MataKuliahRecyclerViewAdapter.ViewHolder>{
    private Context context;
    private List<MataKuliah> mataKuliahs;

    public MataKuliahRecyclerViewAdapter(Context context, List<MataKuliah> mataKuliahs) {
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
    public void onBindViewHolder(ViewHolder holder, int position) {
        MataKuliah p = mataKuliahs.get(position);
        holder.txtId.setText(p.getId());
        holder.txtNama.setText(p.getName());
        holder.txtSemester.setText(p.getSemester());
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

        public ViewHolder(View itemView) {
            super(itemView);

            txtId = (TextView) itemView.findViewById(R.id.txt_id);
            txtNama = (TextView) itemView.findViewById(R.id.txt_name);
            txtSemester = (TextView) itemView.findViewById(R.id.txt_semester);
            txtVersion = (TextView) itemView.findViewById(R.id.txt_version);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            String kodePasien = txtId.getText().toString();
            String nama = txtNama.getText().toString();
            String umur = txtSemester.getText().toString();
            String alamat = txtVersion.getText().toString();

            Toast.makeText(view.getContext(),
                    txtId.getText().toString(),
                    Toast.LENGTH_SHORT).show();
        }
    }
}
