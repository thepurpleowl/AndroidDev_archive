package com.surya.SHCS.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;

import com.surya.SHCS.R;
import com.surya.SHCS.helpers.InputValidation;
import com.surya.SHCS.sql.DatabaseHelper;

public class PatientActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = PatientActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutPID;

    private TextInputEditText textInputEditTextPID;

    private AppCompatButton appCompatButtonLocationSearch;
    private AppCompatButton appCompatButtonProblemSearch;
    private AppCompatButton appCompatButtonShowRecords;
//    private AppCompatButton appCompatButtonoOnlineFee;
//    private AppCompatButton appCompatButtonSendClaim;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
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

        textInputLayoutPID = (TextInputLayout) findViewById(R.id.textInputLayoutPID);;

        textInputEditTextPID = (TextInputEditText) findViewById(R.id.textInputEditTextPID);

        appCompatButtonLocationSearch = (AppCompatButton) findViewById(R.id.appCompatButtonLocSearch);
        appCompatButtonProblemSearch = (AppCompatButton) findViewById(R.id.appCompatButtonProblemSearch);
        appCompatButtonShowRecords = (AppCompatButton) findViewById(R.id.appCompatButtonShowRecords);
//        appCompatButtonoOnlineFee =(AppCompatButton) findViewById(R.id.appCompatButtonOnlineFee);
//        appCompatButtonSendClaim =(AppCompatButton) findViewById(R.id.appCompatButtonSendClaim);
    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonLocationSearch.setOnClickListener(this);
        appCompatButtonProblemSearch.setOnClickListener(this);
        appCompatButtonShowRecords.setOnClickListener(this);
//        appCompatButtonoOnlineFee.setOnClickListener(this);
//        appCompatButtonSendClaim.setOnClickListener(this);
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
            case R.id.appCompatButtonProblemSearch:
                searchByProblem();
                break;
            case R.id.appCompatButtonLocSearch:
                searchbyLocation();
                break;
            case R.id.appCompatButtonShowRecords:
                Intent showRecordIntent = new Intent(activity, ShowRecordActivity.class);
                startActivity(showRecordIntent);
                break;
//            case R.id.appCompatButtonOnlineFee:
//                Toast.makeText(activity,"Going to online payment page",Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.appCompatButtonSendClaim:
//                Toast.makeText(activity,"Sending Insurance Claim",Toast.LENGTH_SHORT).show();
//                break;
        }
    }

    private void searchByProblem() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPID, textInputLayoutPID, getString(R.string.error_message_email))) {
            return;
        }

        String emailFromIntent = getIntent().getStringExtra("EMAIL");
        Intent bookIntent = new Intent(activity, BookAppointmentActivity.class);
        bookIntent.putExtra("TERM", textInputEditTextPID.getText().toString().trim());
        bookIntent.putExtra("LOCATION", "0");
        bookIntent.putExtra("EMAIL", emailFromIntent);
        startActivity(bookIntent);
        emptyInputEditText();
    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private void searchbyLocation() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPID, textInputLayoutPID, getString(R.string.error_message_email))) {
            return;
        }

        String emailFromIntent = getIntent().getStringExtra("EMAIL");
        Intent bookIntent = new Intent(activity, BookAppointmentActivity.class);
        bookIntent.putExtra("TERM", textInputEditTextPID.getText().toString().trim());
        bookIntent.putExtra("LOCATION", "1");
        bookIntent.putExtra("EMAIL", emailFromIntent);
        startActivity(bookIntent);
        emptyInputEditText();

//        Snackbar.make(nestedScrollView, "Pharmacy Activity", Snackbar.LENGTH_LONG).show();
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextPID.setText(null);
    }
}
