package com.prakashgujarati.khantrajputsamaj.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.prakashgujarati.khantrajputsamaj.BirthdayDetails;
import com.prakashgujarati.khantrajputsamaj.R;
import com.prakashgujarati.khantrajputsamaj.model.Birthday;
import com.prakashgujarati.khantrajputsamaj.utils.Constant;

import java.util.ArrayList;

public class BirthdayListAdapter extends RecyclerView.Adapter<BirthdayListAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Birthday> birthdays;
    private Birthday birthdaydata = new Birthday();
    private View view;

    public BirthdayListAdapter(FragmentActivity activity, ArrayList<Birthday> birthdays) {
        this.context = activity;
        this.birthdays = birthdays;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.birthday_list_card, parent, false);
        return new BirthdayListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(birthdays.get(position).getName());
        holder.birthdate.setText(birthdays.get(position).getBirthdate());
        holder.birthtime.setText(birthdays.get(position).getTime());
        holder.birthplace.setText(birthdays.get(position).getPlace());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, BirthdayDetails.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra(Constant.BundleExtra.BIRTHDAY_ID, birthdays.get(position).getId());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return birthdays.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name,birthdate,birthtime,birthplace;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.anni_name);
            birthdate = itemView.findViewById(R.id.anni_date);
            birthtime = itemView.findViewById(R.id.tv_time);
            birthplace = itemView.findViewById(R.id.tv_place);

        }
    }
}
