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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.surya.SHCS.R;
import com.surya.SHCS.adapters.HospitalDoctorRecyclerAdapter;
import com.surya.SHCS.helpers.InputValidation;
import com.surya.SHCS.sql.DatabaseHelper;

import java.util.ArrayList;
import java.util.Calendar;

import static com.surya.SHCS.R.style.TimePickerTheme;

public class RequestAppointmentActivity extends Activity implements View.OnClickListener {
    private final Activity activity = RequestAppointmentActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutageView;
    private TextInputLayout textInputLayoutproblemView;

    private TextInputEditText textInputEditTextageView;
    private TextInputEditText textInputEditTextproblemView;
    private AppCompatTextView dateView, nameView;

    private AppCompatButton date;
    private AppCompatButton sendRequest;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;

    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;
    private static String EMAIL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_appointment);

        dateView = (AppCompatTextView) findViewById(R.id.textView3);
        nameView = (AppCompatTextView) findViewById(R.id.textViewName);
        textInputLayoutageView = (TextInputLayout) findViewById(R.id.textInputLayoutAge);
        textInputLayoutproblemView = (TextInputLayout) findViewById(R.id.textInputLayoutProblem);

        textInputEditTextageView = (TextInputEditText) findViewById(R.id.editTextAge);
        textInputEditTextproblemView = (TextInputEditText) findViewById(R.id.editTextProblem);
        date = (AppCompatButton)findViewById(R.id.buttonDate);
        sendRequest = (AppCompatButton)findViewById(R.id.buttonRequest);
        initListeners();
        initObjects();

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,R.style.TimePickerTheme,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                     year = arg1 ;
                     month = arg2+1;
                     day = arg3 ;
                     showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    private void initListeners() {
        date.setOnClickListener(this);
        sendRequest.setOnClickListener(this);
    }

    private void initObjects() {

        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);

        SharedPreferences sp1=this.getSharedPreferences("Login",0);
        EMAIL = sp1.getString("EMAIL", null);
        Log.d("sharedpreference",EMAIL);

        int p_id = databaseHelper.getUserId(EMAIL);
        String name = databaseHelper.getUserName(p_id);
        nameView.setText(name);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonDate:
                Log.d("button","howa");
                setDate(view);
                break;

            case R.id.buttonRequest:
                sendRequest();
//                Intent patientIntent = new Intent(activity, PatientActivity.class);
//                startActivity(patientIntent);
                finish();
        }
    }
    private void sendRequest() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextageView, textInputLayoutageView, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextproblemView, textInputLayoutproblemView, getString(R.string.error_message_email))) {
            return;
        }

        String emailDoctor = getIntent().getStringExtra("EMAIL");
        Log.d("EMAIL",emailDoctor);

        if (databaseHelper.checkUser(EMAIL)) {
            int p_id = databaseHelper.getUserId(EMAIL);
            String name = databaseHelper.getUserName(p_id);
            int d_id = databaseHelper.getUserId(emailDoctor);

            String problem = textInputEditTextproblemView.getText().toString().trim();
            String date = ""+year+"-"+month+"-"+day+" "+"00:00:00";

            databaseHelper.addAppointment(p_id,d_id,problem,date);


        } else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }
    }
}


