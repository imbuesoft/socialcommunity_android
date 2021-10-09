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
import com.prakashgujarati.khantrajputsamaj.model.Surname;

import java.util.ArrayList;

public class SurnameListAdapter extends RecyclerView.Adapter<SurnameListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Surname> surnames;
    private View view;
    private Surname surnamedata = new Surname();

    public SurnameListAdapter(FragmentActivity activity, ArrayList<Surname> surnames) {
        this.context = activity;
        this.surnames = surnames;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.surname_custom_text, parent, false);
        return new SurnameListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(surnames.get(position).getName());


      /*  if (position % 2 == 1) {
            view.setBackgroundColor(Color.parseColor("#F5FFFFFF"));
        } else {
            view.setBackgroundColor(Color.parseColor("#F5E7E7E7"));
        }

       */
    }

    @Override
    public int getItemCount() {
        return surnames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
       // private TextView tvIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.lbl_name);
           // tvIcon = itemView.findViewById(R.id.tvIcon);
        }
    }
}


