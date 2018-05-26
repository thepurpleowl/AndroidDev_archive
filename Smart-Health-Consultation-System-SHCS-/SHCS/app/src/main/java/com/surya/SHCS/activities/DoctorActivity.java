package com.surya.SHCS.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.surya.SHCS.R;
import com.surya.SHCS.adapters.DoctorNewAppointmentRecyclerAdapter;
import com.surya.SHCS.adapters.UsersRecyclerAdapter;
import com.surya.SHCS.helpers.InputValidation;
import com.surya.SHCS.model.User;
import com.surya.SHCS.sql.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lalit on 10/10/2016.
 */

public class DoctorActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = DoctorActivity.this;

    private NestedScrollView nestedScrollView;

    private AppCompatButton appCompatButtonShowNewRecords;
    private AppCompatButton appCompatButtonShowCurrentRecords;
    private AppCompatButton appCompatButtonShowRecords;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        getSupportActionBar().hide();
        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        appCompatButtonShowNewRecords = (AppCompatButton) findViewById(R.id.appCompatButtonShowNewRecords);
        appCompatButtonShowCurrentRecords = (AppCompatButton) findViewById(R.id.appCompatButtonShowCurrentRecords);
        appCompatButtonShowRecords = (AppCompatButton) findViewById(R.id.appCompatButtonShowRecords);
    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonShowNewRecords.setOnClickListener(this);
        appCompatButtonShowCurrentRecords.setOnClickListener(this);
        appCompatButtonShowRecords.setOnClickListener(this);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);

    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonShowNewRecords:
                Intent newRecordIntent = new Intent(activity, DoctorNewAppointmentActivity.class);
                startActivity(newRecordIntent);
                break;
            case R.id.appCompatButtonShowCurrentRecords:
                Intent currentRecordIntent = new Intent(activity, DoctorCurrentAppointmentActivity.class);
                startActivity(currentRecordIntent);
                break;
            case R.id.appCompatButtonShowRecords:
                Intent showRecordIntent = new Intent(activity, ShowRecordDoctorActivity.class);
                startActivity(showRecordIntent);
                break;
        }
    }
}
