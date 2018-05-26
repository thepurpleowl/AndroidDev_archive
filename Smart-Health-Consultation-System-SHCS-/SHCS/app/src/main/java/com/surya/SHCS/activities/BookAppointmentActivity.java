package com.surya.SHCS.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.surya.SHCS.R;
import com.surya.SHCS.adapters.AppointmentRecyclerAdapter;
import com.surya.SHCS.model.User;
import com.surya.SHCS.sql.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lalit on 10/10/2016.
 */

public class BookAppointmentActivity extends AppCompatActivity {

    private AppCompatActivity activity = BookAppointmentActivity.this;
    private AppCompatTextView textViewName;
    private RecyclerView recyclerViewUsers;
    private List<User> listUsers;
    private AppointmentRecyclerAdapter appointmentRecyclerAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        getSupportActionBar().setTitle("");
        initViews();
        initObjects();

    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        textViewName = (AppCompatTextView) findViewById(R.id.textViewName);
        recyclerViewUsers = (RecyclerView) findViewById(R.id.recyclerViewUsers);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        listUsers = new ArrayList<>();
        appointmentRecyclerAdapter = new AppointmentRecyclerAdapter(listUsers);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewUsers.setLayoutManager(mLayoutManager);
        recyclerViewUsers.setItemAnimator(new DefaultItemAnimator());
        recyclerViewUsers.setHasFixedSize(true);
        recyclerViewUsers.setAdapter(appointmentRecyclerAdapter);
        databaseHelper = new DatabaseHelper(activity);


        String term = getIntent().getStringExtra("TERM").trim();
        int location = Integer.parseInt(getIntent().getStringExtra("LOCATION").trim());
        textViewName.setText("Search Result for : "+term);

        Log.d("appointment",term+"  "+location);
        getDoctor(term,location);
    }

    /**
     * This method is to fetch all user records from SQLite
     */
    private void getDoctor(final String term,final int location) {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listUsers.clear();

                if(location==1){
                    listUsers.addAll(databaseHelper.getAllDoctorByLocation(term));
                }else{
                    listUsers.addAll(databaseHelper.getAllDoctorByProblem(term));
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                appointmentRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }
}
