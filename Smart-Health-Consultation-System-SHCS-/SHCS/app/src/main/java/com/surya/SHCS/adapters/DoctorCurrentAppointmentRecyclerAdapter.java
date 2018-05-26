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
import com.surya.SHCS.activities.PrescriptionActivity;
import com.surya.SHCS.model.Record;

import java.util.List;

/**
 * Created by lalit on 10/10/2016.
 */

public class DoctorCurrentAppointmentRecyclerAdapter extends RecyclerView.Adapter<DoctorCurrentAppointmentRecyclerAdapter.UserViewHolder> {

    private List<Record> listUsers;

    public DoctorCurrentAppointmentRecyclerAdapter(List<Record> listUsers) {
        this.listUsers = listUsers;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_current_appointment_recycler, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, final int position) {
        holder.textViewName.setText(listUsers.get(position).getPatient_name());
        holder.textViewEmail.setText(listUsers.get(position).getProblem());
        holder.textViewPassword.setText(listUsers.get(position).getDate());
        holder.prescribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBookRequest = new Intent(view.getContext(), PrescriptionActivity.class);
                intentBookRequest.putExtra("p_id",listUsers.get(position).getId()+"");
                Log.d("prescription_id",listUsers.get(position).getId()+"");
                view.getContext().startActivity(intentBookRequest);
//                ((Activity)view.getContext()).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.v(DoctorCurrentAppointmentRecyclerAdapter.class.getSimpleName(),""+listUsers.size());
        return listUsers.size();
    }


    /**
     * ViewHolder class
     */
    public class UserViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewName;
        public AppCompatTextView textViewEmail;
        public AppCompatTextView textViewPassword;
        public AppCompatButton prescribe;

        public UserViewHolder(View view) {
            super(view);
            textViewName = (AppCompatTextView) view.findViewById(R.id.textViewName);
            textViewEmail = (AppCompatTextView) view.findViewById(R.id.textViewEmail);
            textViewPassword = (AppCompatTextView) view.findViewById(R.id.textViewPassword);
            prescribe = (AppCompatButton)view.findViewById(R.id.appCompatButtonPrescribe);
        }
    }


}
