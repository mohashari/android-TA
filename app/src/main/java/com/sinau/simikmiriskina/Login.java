package com.sinau.simikmiriskina;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sinau.simikmiriskina.api.ApiClient;
import com.sinau.simikmiriskina.api.MahasiswaApiInterface;
import com.sinau.simikmiriskina.model.LoginRequest;
import com.sinau.simikmiriskina.model.Mahasiswa;
import com.sinau.simikmiriskina.model.MahasiswaResponse;
import com.sinau.simikmiriskina.model.ResultMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {
    SessionManager session;
    private Object mahasiswas = new ArrayList<>();

    private EditText edtUsername;
    private EditText editPassword;

    private void bindData() {
        //+"/"+user.get(SessionManager.kunci_email)
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MahasiswaApiInterface api = retrofit.create(MahasiswaApiInterface.class);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setNim(edtUsername.getText().toString());
        loginRequest.setPassword(editPassword.getText().toString());
        Call<ResultMessage> call = api.login(loginRequest);

        call.enqueue(new Callback<ResultMessage>() {
            @Override
            public void onResponse(Call<ResultMessage> call, Response<ResultMessage> response) {
                String message = response.body().getMessage();

                if (message.equals("OK")) {
                    mahasiswas = response.body().getResult();
                    Intent intent = new Intent(Login.this, Home.class);
                    session.createSession("88eea23b-c760-43c2-8f1c-a17754946f18");
                    startActivity(intent);
                    LOADINGSPLASH();

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        session = new SessionManager(getApplicationContext());

        edtUsername = (EditText) findViewById(R.id.input_email);
        editPassword = (EditText) findViewById(R.id.input_password);
        Button btnLogin = (Button) findViewById(R.id.btn_login);
        TextView regrister = (TextView) findViewById(R.id.link_signup);
        TextView forgot = (TextView) findViewById(R.id.link_forgot);

        final String username = "admin";
        final String pass = "admin";


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bindData();
            }
        });

        regrister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, ForgotPassword.class);
                startActivity(intent);
            }
        });
    }


    public void LOADINGSPLASH() {
        final ProgressDialog myProgressDialog = ProgressDialog.show(Login.this, "Loading", "Mohon Tunggu...", true);
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                }
                Intent i = new Intent(Login.this, Home.class);
                myProgressDialog.dismiss();
                Login.this.finish();
                startActivity(i);
            }
        }.start();
    }
}





