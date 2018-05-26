package com.surya.SHCS.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.surya.SHCS.R;
import com.surya.SHCS.helpers.InputValidation;
import com.surya.SHCS.sql.DatabaseHelper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = LoginActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    private AppCompatButton appCompatButtonLogin;

    private AppCompatTextView textViewLinkRegister;
    private AppCompatTextView textViewForgotPassword;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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

        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);

        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);

        textViewLinkRegister = (AppCompatTextView) findViewById(R.id.textViewLinkRegister);
        textViewForgotPassword = (AppCompatTextView) findViewById(R.id.textViewForgotPassword);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
        textViewForgotPassword.setOnClickListener(this);
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
            case R.id.appCompatButtonLogin:
                verifyFromSQLite();
                break;
            case R.id.textViewLinkRegister:
                // Navigate to RegisterActivity
                Intent intentUserType = new Intent(getApplicationContext(), UserTypeSelectionActivity.class);
                startActivity(intentUserType);
                break;
            case R.id.textViewForgotPassword:
                // Navigate to RegisterActivity
                Intent intentForgotPasssword = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
                startActivity(intentForgotPasssword);
                break;

        }
    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private void verifyFromSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_email))) {
            return;
        }

        if (databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim()
                , textInputEditTextPassword.getText().toString().trim())) {

            int isAdmin = databaseHelper.isAdmin(textInputEditTextEmail.getText().toString().trim()
                    , textInputEditTextPassword.getText().toString().trim());
            int user_type =databaseHelper.checkUserType(textInputEditTextEmail.getText().toString().trim()
                    , textInputEditTextPassword.getText().toString().trim());

            SharedPreferences sp=getSharedPreferences("Login", 0);
            SharedPreferences.Editor Ed=sp.edit();
            Ed.putString("EMAIL",textInputEditTextEmail.getText().toString().trim() );
            Ed.commit();
//log in user
            if(isAdmin==1){
                Intent adminIntent = new Intent(activity, AdminActivity.class);
                adminIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
                startActivity(adminIntent);
            }
            else{
                switch (user_type){
                    case 0:
                        Intent patientIntent = new Intent(activity, PatientActivity.class);
                        patientIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
                        startActivity(patientIntent);
                        break;

                    case 1:
                    Intent doctorIntent = new Intent(activity, DoctorActivity.class);
                    doctorIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
                    startActivity(doctorIntent);
                        break;

                    case 2:
                        Intent hospitalIntent = new Intent(activity, HospitalActivity.class);
                        hospitalIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
                        startActivity(hospitalIntent);
                        break;

                    case 3:
                        Intent pharmacyIntent = new Intent(activity, PharmacyActivity.class);
                        pharmacyIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
                        startActivity(pharmacyIntent);
                        break;
                }
            }
            emptyInputEditText();


        } else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }
}
