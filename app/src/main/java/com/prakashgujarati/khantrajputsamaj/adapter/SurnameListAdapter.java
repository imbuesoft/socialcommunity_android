package com.prakashgujarati.khantrajputsamaj.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.prakashgujarati.khantrajputsamaj.R;

import java.util.ArrayList;

public class SurnameListAdapter extends RecyclerView.Adapter<SurnameListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> surnames;
    private View view;

    public SurnameListAdapter(FragmentActivity activity, ArrayList<String> surnames) {
        this.context = activity;
        this.surnames = surnames;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.surname_custom_text, parent, false);
        return new SurnameListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvIcon.setText(surnames.get(position).toString().substring(0, 1).toUpperCase());
        holder.name.setText(surnames.get(position).toString());


        if (position % 2 == 1) {
            view.setBackgroundColor(Color.parseColor("#F5FFFFFF"));
        } else {
            view.setBackgroundColor(Color.parseColor("#F5E7E7E7"));
        }
    }

    @Override
    public int getItemCount() {
        return surnames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView tvIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.lbl_name);
            tvIcon = itemView.findViewById(R.id.tvIcon);
        }
    }
}


