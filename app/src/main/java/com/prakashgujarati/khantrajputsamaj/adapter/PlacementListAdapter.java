package com.prakashgujarati.khantrajputsamaj.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.prakashgujarati.khantrajputsamaj.R;
import com.prakashgujarati.khantrajputsamaj.model.Placement;
import com.prakashgujarati.khantrajputsamaj.utils.Constant;
import com.prakashgujarati.khantrajputsamaj.PlacementDetails;

import java.util.ArrayList;

public class PlacementListAdapter extends RecyclerView.Adapter<PlacementListAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Placement> placement;
    private View view;

    public PlacementListAdapter(FragmentActivity activity, ArrayList<Placement> placement) {
        this.context = activity;
        this.placement = placement;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewtype) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.placement_list, parent, false);
        return new PlacementListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.title.setText(placement.get(position).getTitle());
        holder.desc.setText(placement.get(position).getSkills());
        holder.time.setText(placement.get(position).getEducationQualification());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, PlacementDetails.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra(Constant.BundleExtra.PLACEMENT_ID, placement.get(position).getId());
                context.startActivity(i);
               // Toast.makeText(view.getContext(), "You clicked on "+placement.get(position).getTitle(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return placement.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView title,desc,time;


        public ViewHolder(View itemView) {
            super(itemView);
            //img = itemView.findViewById(R.id.news_image);
            title = itemView.findViewById(R.id.placement_title);
            desc = itemView.findViewById(R.id.placement_description);
            time = itemView.findViewById(R.id.placement_time);
        }
    }
}
