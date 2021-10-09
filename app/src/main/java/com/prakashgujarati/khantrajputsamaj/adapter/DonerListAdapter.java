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

import com.prakashgujarati.khantrajputsamaj.BusinessDetails;
import com.prakashgujarati.khantrajputsamaj.DonerDetails;
import com.prakashgujarati.khantrajputsamaj.R;
import com.prakashgujarati.khantrajputsamaj.model.Business;
import com.prakashgujarati.khantrajputsamaj.model.Doner;
import com.prakashgujarati.khantrajputsamaj.utils.Constant;

import java.util.ArrayList;

public class DonerListAdapter  extends  RecyclerView.Adapter<DonerListAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Doner> doner;
    private Doner donerdata = new Doner();
    private View view;


    public DonerListAdapter(FragmentActivity activity, ArrayList<Doner> doner) {
        this.context = activity;
        this.doner = doner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doner_list_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.doner_title.setText(doner.get(position).getTitle());
        holder.doner_type.setText(doner.get(position).getType());
        holder.doner_description.setText(doner.get(position).getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DonerDetails.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra(Constant.BundleExtra.DONER_ID, doner.get(position).getId());
                context.startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return doner.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView doner_title,doner_description,doner_type;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            doner_title = itemView.findViewById(R.id.doner_title);
            doner_description =itemView.findViewById(R.id.doner_description);
            doner_type = itemView.findViewById(R.id.doner_type);
        }
    }
}
