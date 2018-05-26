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
import android.widget.Toast;

import com.surya.SHCS.R;
import com.surya.SHCS.model.Record;
import com.surya.SHCS.model.User;
import com.surya.SHCS.sql.DatabaseHelper;

import java.util.List;

/**
 * Created by lalit on 10/10/2016.
 */

public class RecordRecyclerAdapter extends RecyclerView.Adapter<RecordRecyclerAdapter.UserViewHolder> {

    private List<Record> listUsers;
    private DatabaseHelper databaseHelper;

    public RecordRecyclerAdapter(List<Record> listUsers) {
        this.listUsers = listUsers;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_patientrecord_recycler, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, final int position) {
        holder.textViewName.setText(listUsers.get(position).getDoctor_name());
        holder.textViewEmail.setText(listUsers.get(position).getSpeciality());
        holder.textViewPassword.setText(listUsers.get(position).getDate());
        final String status = listUsers.get(position).getStatus()+"";
        if(status.equals("0")){
            holder.textViewStatus.setText("Declined");
        }else if(status.equals("1")){
            holder.textViewStatus.setText("Acccepted");
        }else if(status.equals("2")){
            holder.textViewStatus.setText("Completed");
        }else if(status.equals("3")){
            holder.textViewStatus.setText("In processing");
        }

        holder.pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(status.equals("2")){
                    Toast.makeText(view.getContext(),"Payment Successful",Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.claim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(status.equals("2")){
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setType("text/plain");
                    view.getContext().startActivity(emailIntent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.v(RecordRecyclerAdapter.class.getSimpleName(),""+listUsers.size());
        return listUsers.size();
    }


    /**
     * ViewHolder class
     */
    public class UserViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewName;
        public AppCompatTextView textViewEmail;
        public AppCompatTextView textViewPassword;
        public AppCompatTextView textViewStatus;
        public AppCompatButton pay;
        public AppCompatButton claim;

        public UserViewHolder(View view) {
            super(view);
            textViewName = (AppCompatTextView) view.findViewById(R.id.textViewName);
            textViewEmail = (AppCompatTextView) view.findViewById(R.id.textViewEmail);
            textViewPassword = (AppCompatTextView) view.findViewById(R.id.textViewPassword);
            textViewStatus = (AppCompatTextView) view.findViewById(R.id.textViewStatus);
            pay = (AppCompatButton)view.findViewById(R.id.appCompatButtonPay);
            claim = (AppCompatButton)view.findViewById(R.id.appCompatButtonClaim);
            databaseHelper = new DatabaseHelper(view.getContext());
        }
    }


}
