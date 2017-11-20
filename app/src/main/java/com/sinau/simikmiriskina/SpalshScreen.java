package com.sinau.simikmiriskina;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class SpalshScreen extends AppCompatActivity {
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh_screen);

        sessionManager = new SessionManager(getApplicationContext());

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    sessionManager.checkLogin();
                }
            }
        };

        thread.start();
        
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
