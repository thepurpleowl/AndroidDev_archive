package com.surya.SHCS.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import com.surya.SHCS.R;
import com.surya.SHCS.helpers.InputValidation;
import com.surya.SHCS.sql.DatabaseHelper;

public class PharmacyActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = PharmacyActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutPID;
    private TextInputLayout textInputLayoutMedName;
    private TextInputLayout textInputLayoutMedAmt;
    private TextInputLayout textInputLayoutMedCost;

    private TextInputEditText textInputEditTextPID;
    private TextInputEditText textInputEditTextMedName;
    private TextInputEditText textInputEditTextMedAmt;
    private TextInputEditText textInputEditTextMedCost;

    private AppCompatButton appCompatButtonPIDSearch;
    private AppCompatButton appCompatButtonUpdateStock;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy);
        getSupportActionBar().hide();

        Toast.makeText(getApplicationContext(),"Pharmacy activity",Toast.LENGTH_LONG).show();

        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutPID = (TextInputLayout) findViewById(R.id.textInputLayoutPID);
        textInputLayoutMedName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutMedAmt = (TextInputLayout) findViewById(R.id.textInputLayoutAmount);
        textInputLayoutMedCost = (TextInputLayout) findViewById(R.id.textInputLayoutCost);

        textInputEditTextPID = (TextInputEditText) findViewById(R.id.textInputEditTextPID);
        textInputEditTextMedName = (TextInputEditText) findViewById(R.id.textInputEditTextName);
        textInputEditTextMedAmt = (TextInputEditText) findViewById(R.id.textInputEditTextAmount);
        textInputEditTextMedCost = (TextInputEditText) findViewById(R.id.textInputEditTextCost);

        appCompatButtonPIDSearch = (AppCompatButton) findViewById(R.id.appCompatButtonPIDSearch);
        appCompatButtonUpdateStock = (AppCompatButton) findViewById(R.id.appCompatButtonUpdateStock);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonPIDSearch.setOnClickListener(this);
        appCompatButtonUpdateStock.setOnClickListener(this);
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
            case R.id.appCompatButtonPIDSearch:
                checkPrescription();
                break;
            case R.id.appCompatButtonUpdateStock:
                checkUpdateFields();
                break;
        }
    }

    private void checkUpdateFields() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextMedName, textInputLayoutMedName, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextMedAmt, textInputLayoutMedAmt, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextMedCost, textInputLayoutMedCost, getString(R.string.error_message_email))) {
            return;
        }
        String emailFromIntent = getIntent().getStringExtra("EMAIL");
        String med_name = textInputEditTextMedAmt.getText().toString();
        int med_amt = Integer.parseInt(textInputEditTextMedAmt.getText().toString());
        int med_cost = Integer.parseInt(textInputEditTextMedCost.getText().toString());
        databaseHelper.updateStock(emailFromIntent,med_name,med_amt,med_cost);

        Toast.makeText(getApplicationContext(),getString(R.string.text_updatestock),Toast.LENGTH_LONG).show();
    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private void checkPrescription() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPID, textInputLayoutPID, getString(R.string.error_message_email))) {
            return;
        }

        String patient_name = databaseHelper.checkPrescription(Integer.parseInt(textInputEditTextPID.getText().toString().trim()));
        if (patient_name != null) {
            Toast.makeText(getApplicationContext(),"Valid Prescription",Toast.LENGTH_LONG).show();
            emptyInputEditText();
        }else{
            Toast.makeText(getApplicationContext(),"Invalid Entry",Toast.LENGTH_LONG).show();
        }

//        Snackbar.make(nestedScrollView, "Pharmacy Activity", Snackbar.LENGTH_LONG).show();
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextPID.setText(null);
        textInputEditTextMedName.setText(null);
        textInputEditTextMedAmt.setText(null);
        textInputEditTextMedCost.setText(null);
    }
}
