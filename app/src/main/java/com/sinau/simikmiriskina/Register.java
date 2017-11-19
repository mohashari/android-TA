package com.sinau.simikmiriskina;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sinau.simikmiriskina.api.ApiClient;
import com.sinau.simikmiriskina.api.MahasiswaApiInterface;
import com.sinau.simikmiriskina.model.LoginRequest;
import com.sinau.simikmiriskina.model.Mahasiswa;
import com.sinau.simikmiriskina.model.MahasiswaResponse;
import com.sinau.simikmiriskina.model.ResultMessage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Register extends FragmentActivity implements DatePickerDialog.OnDateSetListener {
    public static final String DATEPICKER_TAG = "datepicker";
    SessionManager session;
    Button register;
    EditText pass, conpass, nim, nama, alamat, dateForm, email, agama, semester,phone;
    RadioGroup gender, jurusan;
    private RadioButton rbGenderMale;
    private RadioButton rbGenderFemale;

    private void bindData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MahasiswaApiInterface api = retrofit.create(MahasiswaApiInterface.class);

        Mahasiswa mahasiswa = new Mahasiswa();

        mahasiswa.setNim(nim.getText().toString());
        mahasiswa.setName(nama.getText().toString());
        mahasiswa.setPassword(pass.getText().toString());
        mahasiswa.setAddress(alamat.getText().toString());
        mahasiswa.setDateOfBirth(dateForm.getText().toString());
        mahasiswa.setEmail(email.getText().toString());
        mahasiswa.setPhone(phone.getText().toString());
        mahasiswa.setReligious(agama.getText().toString());
        mahasiswa.setGender("M");
        mahasiswa.setJurusan("Teknik Komputer");
        Call<ResultMessage> call = api.register(mahasiswa);

        call.enqueue(new Callback<ResultMessage>() {
            @Override
            public void onResponse(Call<ResultMessage> call, Response<ResultMessage> response) {
                String message = response.body().getMessage();

                if (message.equals("OK")) {
                    Gson gson = new Gson();
                    JsonObject jsonObject = gson.toJsonTree(response.body().getResult()).getAsJsonObject();
                    Mahasiswa m = gson.fromJson(jsonObject.toString(), Mahasiswa.class);

                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    session.createSession(m.getId());
                    Toast.makeText(getApplicationContext(), "Anda Berhasil Register",
                            Toast.LENGTH_SHORT).show();

                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(), response.body().getResult().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultMessage> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), "Jaringan Error !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nim = (EditText) findViewById(R.id.input_nim);
        nama = (EditText) findViewById(R.id.input_name);
        alamat = (EditText) findViewById(R.id.input_address);
        dateForm = (EditText) findViewById(R.id.getdate);
        email = (EditText) findViewById(R.id.input_emai);
        agama = (EditText) findViewById(R.id.input_religious);
        semester = (EditText) findViewById(R.id.input_semester);
        phone = (EditText) findViewById(R.id.input_telepon);

        register = (Button) findViewById(R.id.btn_register);

        gender = (RadioGroup) findViewById(R.id.gender);
        jurusan = (RadioGroup) findViewById(R.id.jurusan);

        final Calendar calendar = Calendar.getInstance();

        final DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        findViewById(R.id.getdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.setVibrate(false);
                datePickerDialog.setYearRange(1985, 2028);
                datePickerDialog.setCloseOnSingleTapDay(false);
                datePickerDialog.show(getSupportFragmentManager(), DATEPICKER_TAG);
            }
        });
        if (savedInstanceState != null) {
            DatePickerDialog dpd = (DatePickerDialog) getSupportFragmentManager().findFragmentByTag(DATEPICKER_TAG);
            if (dpd != null) {
                dpd.setOnDateSetListener(this);
            }
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pass = (EditText) findViewById(R.id.input_password);
                conpass = (EditText) findViewById(R.id.input_retypePass);
                if (!conpass.getText().toString().equals(pass.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Password tidak sama",
                            Toast.LENGTH_SHORT).show();
                } else {
                    bindData();
                }
            }
        });
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {
        EditText cobaDate = (EditText) findViewById(R.id.getdate);
        cobaDate.setText(year + "-" + month + "-" + day);
    }
}
