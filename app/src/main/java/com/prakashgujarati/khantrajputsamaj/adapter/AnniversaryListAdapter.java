package com.prakashgujarati.khantrajputsamaj.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.prakashgujarati.khantrajputsamaj.AnniversaryDetails;
import com.prakashgujarati.khantrajputsamaj.R;
import com.prakashgujarati.khantrajputsamaj.model.Anniversary;

import java.util.ArrayList;

public class AnniversaryListAdapter extends RecyclerView.Adapter<AnniversaryListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Anniversary> anniversary;
    private View view;

    public AnniversaryListAdapter(FragmentActivity activity, ArrayList<Anniversary> anniversary) {
        this.context = activity;
        this.anniversary = anniversary;
    }

    @Override
    public AnniversaryListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.anniversary_list_card, parent, false);
        return new AnniversaryListAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.name.setText(anniversary.get(position).getName());
        holder.date.setText(anniversary.get(position).getAdate()+" | "+anniversary.get(position).getTime());
        //holder.turns.setText(anniversary.get(position).getAdate());
        //holder.remaindays.setText(anniversary.get(position).getAdate());
       /* holder.vanue.setText(anniversary.get(position).getVanue());
        holder.description.setText(anniversary.get(position).getDescription());
        holder.type.setText(anniversary.get(position).getType());*/

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, AnniversaryDetails.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return anniversary.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView name,date,turns,remaindays;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.anni_image);
            name = itemView.findViewById(R.id.anni_name);
            date = itemView.findViewById(R.id.anni_date);
            turns = itemView.findViewById(R.id.anni_turns);
            remaindays = itemView.findViewById(R.id.anni_remaindays);
        }
    }
}
