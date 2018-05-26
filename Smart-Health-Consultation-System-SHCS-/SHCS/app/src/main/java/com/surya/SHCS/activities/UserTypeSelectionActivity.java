package com.surya.SHCS.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.surya.SHCS.R;

public class UserTypeSelectionActivity  extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private final AppCompatActivity activity = UserTypeSelectionActivity.this;

    private NestedScrollView nestedScrollView;

    private AppCompatButton appCompatButtonLogin;
    private AppCompatTextView textViewLinkRegister;
    private AppCompatSpinner spinner;
    private Object userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_selection);
        getSupportActionBar().hide();

        initViews();
        initListeners();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);
        textViewLinkRegister = (AppCompatTextView) findViewById(R.id.textViewLinkRegister);
        spinner = (AppCompatSpinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.user_array, R.layout.spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonLogin.setOnClickListener(this);
//        textViewLinkRegister.setOnClickListener(this);

        spinner.setOnItemSelectedListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonLogin:
                // Navigate to RegisterActivity
                if(userType.toString().equals("Patient") ) {
                    Intent intentRegister = new Intent(getApplicationContext(), RegisterPatientActivity.class);
//                    intentRegister.putExtra("USER_TYPE", "0");
                    startActivity(intentRegister);
                }
                else if(userType.toString().equals("Doctor") ) {
                    Intent intentRegister = new Intent(getApplicationContext(), RegisterDoctorActivity.class);
//                    intentRegister.putExtra("USER_TYPE", "1");
                    startActivity(intentRegister);
                }
                else if(userType.toString().equals("Hospital") ) {
                    Intent intentRegister = new Intent(getApplicationContext(), RegisterHospitalActivity.class);
//                    intentRegister.putExtra("USER_TYPE", "2");
                    startActivity(intentRegister);
                }
                else if(userType.toString().equals("Pharmacy") ) {
                    Intent intentRegister = new Intent(getApplicationContext(), RegisterPharmacyActivity.class);
//                    intentRegister.putExtra("USER_TYPE", "3");
                    startActivity(intentRegister);
                }
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        userType = adapterView.getItemAtPosition(i);
        Toast.makeText(getApplicationContext(),userType.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
