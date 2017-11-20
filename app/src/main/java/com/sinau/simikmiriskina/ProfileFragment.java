package com.sinau.simikmiriskina;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sinau.simikmiriskina.api.ApiClient;
import com.sinau.simikmiriskina.api.MahasiswaApiInterface;
import com.sinau.simikmiriskina.model.Mahasiswa;
import com.sinau.simikmiriskina.model.MahasiswaResponse;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends Fragment {
    private View view;
    private Object mahasiswas = new ArrayList<>();
    SessionManager session ;
    HashMap<String, String> user;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        session = new SessionManager(getActivity().getApplicationContext());
        user = session.getUserDetails();

        txtId = (EditText) view.findViewById(R.id.edit_id);
        txtNim = (EditText) view.findViewById(R.id.edit_nim);
        txtName = (EditText) view.findViewById(R.id.edit_name);
        txtVersion = (EditText) view.findViewById(R.id.edit_version);
        rbGenderMale = (RadioButton) view.findViewById(R.id.gender_male);
        rbGenderFemale = (RadioButton) view.findViewById(R.id.gender_female);
        txtAddress = (EditText) view.findViewById(R.id.edit_address);
        txtEmail = (EditText) view.findViewById(R.id.edit_email);
        txtReligious = (EditText) view.findViewById(R.id.edit_religious);
        txtPassword = (EditText) view.findViewById(R.id.edit_password);
        txtSemester = (EditText) view.findViewById(R.id.edit_semester);
        txtJurusan = (EditText) view.findViewById(R.id.edit_jurusan);
        txtPhone = (EditText) view.findViewById(R.id.edit_phone);
        txtPaying = (EditText) view.findViewById(R.id.edit_paying);

        bindData();


        return view;
    }

    private void bindData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MahasiswaApiInterface api = retrofit.create(MahasiswaApiInterface.class);
        Call<MahasiswaResponse> call = api.getMahasiswa(user.get(SessionManager.kunci_email));
        call.enqueue(new Callback<MahasiswaResponse>() {
            @Override
            public void onResponse(Call<MahasiswaResponse> call, Response<MahasiswaResponse> response) {
                String message = response.body().getMessage();
                Gson gson = new Gson();

                if(message.equals("OK")){
                    JsonObject jsonObject = gson.toJsonTree(response.body().getResult()).getAsJsonObject();
                    Mahasiswa m = gson.fromJson(jsonObject.toString(), Mahasiswa.class);
                    txtId.setText(m.getId());
                    txtNim.setText(m.getNim());
                    txtName.setText(m.getName());
                    txtVersion.setText(m.getVersion());
                    txtAddress.setText(m.getAddress());
                    txtEmail.setText(m.getEmail());
                    txtReligious.setText(m.getReligious());
                    txtPassword.setText(m.getPassword());
                    txtSemester.setText(String.valueOf(m.getSemester()));
                    txtJurusan.setText(m.getJurusan());
                    txtPhone.setText(m.getPhone().toString());
                    txtPaying.setText(m.getPaying());

                    if(m.getGender().toString().equals("male")){
                        rbGenderMale.setChecked(true);
                    } else if(m.getGender().toString().equals("female")){
                        rbGenderFemale.setChecked(true);
                    }

                }
            }

            @Override
            public void onFailure(Call<MahasiswaResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getActivity().getApplicationContext(), "Jaringan Error !", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
