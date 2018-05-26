package com.surya.SHCS.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.surya.SHCS.R;
import com.surya.SHCS.activities.DoctorActivity;
import com.surya.SHCS.activities.RequestAppointmentActivity;
import com.surya.SHCS.model.Record;
import com.surya.SHCS.sql.DatabaseHelper;

import java.util.List;

/**
 * Created by lalit on 10/10/2016.
 */

public class DoctorNewAppointmentRecyclerAdapter extends RecyclerView.Adapter<DoctorNewAppointmentRecyclerAdapter.UserViewHolder> {

    private List<Record> listUsers;
    private DatabaseHelper databaseHelper;

    public DoctorNewAppointmentRecyclerAdapter(List<Record> listUsers) {
        this.listUsers = listUsers;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_new_appointment_recycler, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final UserViewHolder holder, final int position) {
        holder.textViewName.setText(listUsers.get(position).getPatient_name());
        holder.textViewEmail.setText(listUsers.get(position).getProblem());
        holder.textViewPassword.setText(listUsers.get(position).getDate());
        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.addAppointmentStatus(listUsers.get(position).getId(),1);
//                Intent intentBookRequest = new Intent(view.getContext(), DoctorActivity.class);
//                view.getContext().startActivity(intentBookRequest);
//                ((Activity)view.getContext()).finish();
            }
        });
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.addAppointmentStatus(listUsers.get(position).getId(),0);
//                Intent intentBookRequest = new Intent(view.getContext(), DoctorActivity.class);
//                view.getContext().startActivity(intentBookRequest);
//                ((Activity)view.getContext()).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.v(DoctorNewAppointmentRecyclerAdapter.class.getSimpleName(),""+listUsers.size());
        return listUsers.size();
    }


    /**
     * ViewHolder class
     */
    public class UserViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewName;
        public AppCompatTextView textViewEmail;
        public AppCompatTextView textViewPassword;
        public AppCompatButton accept;
        public AppCompatButton cancel;

        public UserViewHolder(View view) {
            super(view);
            textViewName = (AppCompatTextView) view.findViewById(R.id.textViewName);
            textViewEmail = (AppCompatTextView) view.findViewById(R.id.textViewEmail);
            textViewPassword = (AppCompatTextView) view.findViewById(R.id.textViewPassword);
            accept = (AppCompatButton)view.findViewById(R.id.appCompatButtonAccept);
            cancel = (AppCompatButton)view.findViewById(R.id.appCompatButtonReschedule);
            databaseHelper = new DatabaseHelper(view.getContext());
        }
    }
}
