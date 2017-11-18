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

public class Login extends AppCompatActivity {
    SessionManager session;

    private EditText edtUsername;
    private EditText editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        session = new SessionManager(getApplicationContext());

        edtUsername = (EditText) findViewById(R.id.input_email);
        editPassword = (EditText) findViewById(R.id.input_password);
        Button btnLogin = (Button) findViewById(R.id.btn_login);
        TextView regrister = (TextView)findViewById(R.id.link_signup);
        TextView forgot = (TextView)findViewById(R.id.link_forgot);

        final String username = "admin";
        final String pass = "admin";

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtUsername.getText().toString().equals(username) &&
                        editPassword.getText().toString().equals(pass)) {
                    Intent intent = new Intent(Login.this, Home.class);
                    session.createSession("5ba91eb5-0117-4b24-8e03-d70522f17394");
                    startActivity(intent);
                    LOADINGSPLASH();

                } else {
                    Toast.makeText(getApplicationContext(),
                            "Username dan password salah", Toast.LENGTH_SHORT).show();
                }
            }
        });

        regrister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,ForgotPassword.class);
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





