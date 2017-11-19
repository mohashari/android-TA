package com.sinau.simikmiriskina;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sinau.simikmiriskina.api.ApiClient;
import com.sinau.simikmiriskina.api.MahasiswaApiInterface;
import com.sinau.simikmiriskina.model.Mahasiswa;
import com.sinau.simikmiriskina.model.ResultMessage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForgotPassword extends AppCompatActivity {

    Button send;
    EditText txtEmail;


    public void bindData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MahasiswaApiInterface api = retrofit.create(MahasiswaApiInterface.class);
        txtEmail = (EditText) findViewById(R.id.emails);
        String email = txtEmail.getText().toString();

        Call<ResultMessage> call = api.getForgotPassword(email);
        call.enqueue(new Callback<ResultMessage>() {
            @Override
            public void onResponse(Call<ResultMessage> call, Response<ResultMessage> response) {
                String message = response.body().getMessage();

                if (message.equals("OK")) {
                    Intent intent = new Intent(getApplicationContext(),Login.class);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        send = (Button) findViewById(R.id.btn_send);
                send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        bindData();
                    }
                });
    }

}
