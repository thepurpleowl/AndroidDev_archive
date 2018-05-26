package com.surya.SHCS.adapters;

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
import com.surya.SHCS.activities.RequestAppointmentActivity;
import com.surya.SHCS.model.User;
import com.surya.SHCS.sql.DatabaseHelper;

import java.util.List;

/**
 * Created by lalit on 10/10/2016.
 */

public class AdminRecyclerAdapter extends RecyclerView.Adapter<AdminRecyclerAdapter.UserViewHolder> {

    private List<User> listUsers;
    private DatabaseHelper databaseHelper;

    public AdminRecyclerAdapter(List<User> listUsers) {
        this.listUsers = listUsers;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_admin_recycler, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, final int position) {
        holder.textViewName.setText(listUsers.get(position).getName());
        holder.textViewEmail.setText(listUsers.get(position).getEmail());
        holder.textViewPassword.setText(listUsers.get(position).getPassword());
        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.acceptUser(listUsers.get(position).getEmail());
                Toast.makeText(view.getContext(),"Acccepted",Toast.LENGTH_SHORT).show();
            }
        });
        holder.decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.declineUser(listUsers.get(position).getEmail());
                Toast.makeText(view.getContext(),"Declined",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.v(AdminRecyclerAdapter.class.getSimpleName(),""+listUsers.size());
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
        public AppCompatButton decline;

        public UserViewHolder(View view) {
            super(view);
            textViewName = (AppCompatTextView) view.findViewById(R.id.textViewName);
            textViewEmail = (AppCompatTextView) view.findViewById(R.id.textViewEmail);
            textViewPassword = (AppCompatTextView) view.findViewById(R.id.textViewPassword);
            accept = (AppCompatButton)view.findViewById(R.id.appCompatButtonAccept);
            decline = (AppCompatButton)view.findViewById(R.id.appCompatButtonDecline);
            databaseHelper = new DatabaseHelper(view.getContext());
        }
    }


}
