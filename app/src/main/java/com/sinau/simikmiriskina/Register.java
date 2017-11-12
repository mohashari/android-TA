package com.sinau.simikmiriskina;

import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fourmob.datetimepicker.date.DatePickerDialog;


public class Register extends FragmentActivity implements DatePickerDialog.OnDateSetListener {
    public static final String DATEPICKER_TAG = "datepicker";

    Button register;
    EditText pass, conpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register = (Button) findViewById(R.id.btn_register);

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
                    Intent intent = new Intent(Register.this, Login.class);
                    Toast.makeText(getApplicationContext(), "Anda Berhasil Register",
                            Toast.LENGTH_SHORT).show();
                    startActivity(intent);
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
