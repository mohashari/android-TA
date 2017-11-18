package com.sinau.simikmiriskina.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.sinau.simikmiriskina.R;
import com.sinau.simikmiriskina.common.ArrayGetMatkul;
import com.sinau.simikmiriskina.model.Matakuliah;

import java.util.List;


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
    public void onBindViewHolder(ViewHolder holder, int position) {
        Matakuliah p = mataKuliahs.get(position);
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
        private CheckBox chId;

        public ViewHolder(View itemView) {
            super(itemView);

            txtId = (TextView) itemView.findViewById(R.id.txt_id);
            txtNama = (TextView) itemView.findViewById(R.id.txt_name);
            txtSemester = (TextView) itemView.findViewById(R.id.txt_semester);
            txtVersion = (TextView) itemView.findViewById(R.id.txt_version);
            chId = (CheckBox) itemView.findViewById(R.id.ch_id_sem);

            itemView.setOnClickListener(this);

            chId.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String id = txtId.getText().toString();
                    String nama = txtNama.getText().toString();

                    Toast.makeText(view.getContext(),
                            txtId.getText().toString() + ", " +txtNama.getText().toString(),
                            Toast.LENGTH_SHORT).show();

                    ArrayGetMatkul.getGarage(id);
                }
            });

        }

        @Override
        public void onClick(View view) {
        }
    }
}
