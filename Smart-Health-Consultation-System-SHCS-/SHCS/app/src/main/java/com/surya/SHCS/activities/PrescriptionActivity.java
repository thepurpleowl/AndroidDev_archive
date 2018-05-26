package com.surya.SHCS.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.surya.SHCS.R;
import com.surya.SHCS.helpers.InputValidation;
import com.surya.SHCS.sql.DatabaseHelper;

import java.util.Calendar;

public class PrescriptionActivity extends Activity implements View.OnClickListener {
    private final Activity activity = PrescriptionActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutprescriptionView;
    private TextInputLayout textInputLayoutpayView;

    private TextInputEditText textInputEditTextprescriptionView;
    private TextInputEditText textInputEditTextpayView;

    private AppCompatButton ok;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);

        textInputLayoutprescriptionView = (TextInputLayout) findViewById(R.id.textInputLayoutprescriptionView);
        textInputLayoutpayView = (TextInputLayout) findViewById(R.id.textInputLayoutpayView);

        textInputEditTextprescriptionView = (TextInputEditText) findViewById(R.id.editTextprescriptionView);
        textInputEditTextpayView = (TextInputEditText) findViewById(R.id.editTextpayView);
        ok = (AppCompatButton)findViewById(R.id.buttonOK);
        initListeners();
        initObjects();
    }

    private void initListeners() {
        ok.setOnClickListener(this);
    }

    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonOK:
                if (!inputValidation.isInputEditTextFilled(textInputEditTextprescriptionView, textInputLayoutprescriptionView, getString(R.string.error_message_email))) {
                    return;
                }
                if (!inputValidation.isInputEditTextFilled(textInputEditTextpayView, textInputLayoutpayView, getString(R.string.error_message_email))) {
                    return;
                }

                Log.d("button", ""+getIntent().getStringExtra("p_id"));

                int p_id = Integer.parseInt(getIntent().getStringExtra("p_id").toString());
                int amount = Integer.parseInt(textInputEditTextpayView.getText().toString().trim());
                databaseHelper.addPrescription(p_id,textInputEditTextprescriptionView.getText().toString(),amount);
                Intent intentDoctor = new Intent(view.getContext(), DoctorActivity.class);
                view.getContext().startActivity(intentDoctor);
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

