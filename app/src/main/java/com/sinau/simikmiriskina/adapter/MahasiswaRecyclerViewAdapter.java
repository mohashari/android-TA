package com.sinau.simikmiriskina.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sinau.simikmiriskina.R;
import com.sinau.simikmiriskina.model.Mahasiswa;

import java.util.List;


public class MahasiswaRecyclerViewAdapter extends RecyclerView.Adapter<MahasiswaRecyclerViewAdapter.ViewHolder>{
    private Context context;
    private List<Mahasiswa> mahasiswas;

    public MahasiswaRecyclerViewAdapter(Context context, List<Mahasiswa> mahasiswas) {
        this.context = context;
        this.mahasiswas = mahasiswas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_mata_kuliah, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Mahasiswa p = mahasiswas.get(position);
        holder.txtId.setText(p.getId());
        holder.txtName.setText(p.getName());
        holder.txtSemester.setText(p.getSemester());
        holder.txtVersion.setText(p.getVersion());
    }

    @Override
    public int getItemCount() {
        return mahasiswas.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        private EditText txtId;
        private EditText txtNim;
        private EditText txtName;
        private EditText txtVersion;
        private RadioButton rbGenderMale;
        private RadioButton rbGenderFemale;
        private EditText txtAddress;
        private EditText txtEmail;
        private EditText txtReligious;
        private EditText txtPassword;
        private EditText txtSemester;
        private EditText txtJurusan;
        private EditText txtPhone;
        private EditText txtPaying;


        public ViewHolder(View itemView) {
            super(itemView);

            txtId = (EditText) itemView.findViewById(R.id.edit_id);
            txtNim = (EditText) itemView.findViewById(R.id.edit_nim);
            txtName = (EditText) itemView.findViewById(R.id.edit_name);
            txtVersion = (EditText) itemView.findViewById(R.id.edit_version);
            rbGenderMale = (RadioButton) itemView.findViewById(R.id.gender_male);
            rbGenderFemale = (RadioButton) itemView.findViewById(R.id.gender_female);
            txtAddress = (EditText) itemView.findViewById(R.id.edit_address);
            txtEmail = (EditText) itemView.findViewById(R.id.edit_email);
            txtReligious = (EditText) itemView.findViewById(R.id.edit_religious);
            txtPassword = (EditText) itemView.findViewById(R.id.edit_password);
            txtSemester = (EditText) itemView.findViewById(R.id.edit_semester);
            txtJurusan = (EditText) itemView.findViewById(R.id.edit_jurusan);
            txtPhone = (EditText) itemView.findViewById(R.id.edit_phone);
            txtPaying = (EditText) itemView.findViewById(R.id.edit_paying);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            String kodePasien = txtId.getText().toString();
            String nama = txtName.getText().toString();
            String umur = txtSemester.getText().toString();
            String alamat = txtVersion.getText().toString();

            Toast.makeText(view.getContext(),
                    txtId.getText().toString(),
                    Toast.LENGTH_SHORT).show();
        }
    }
}
