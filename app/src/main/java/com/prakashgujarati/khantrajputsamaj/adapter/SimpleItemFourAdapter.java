package com.prakashgujarati.khantrajputsamaj.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.prakashgujarati.khantrajputsamaj.EmployeeDetails;
import com.prakashgujarati.khantrajputsamaj.LateDetails;
import com.prakashgujarati.khantrajputsamaj.R;
import com.prakashgujarati.khantrajputsamaj.model.SimpleItemFour;
import com.prakashgujarati.khantrajputsamaj.utils.Constant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class SimpleItemFourAdapter extends RecyclerView.Adapter<SimpleItemFourAdapter.ViewHolder> {


    private Context context;
    private ArrayList<SimpleItemFour> simpleItemFour;
    private View view;

    public SimpleItemFourAdapter(FragmentActivity activity, ArrayList<SimpleItemFour> simpleItemFour) {
        this.context = activity;
        this.simpleItemFour = simpleItemFour;
    }

    @Override
    public SimpleItemFourAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_item_four_list, parent, false);
        return new SimpleItemFourAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(SimpleItemFourAdapter.ViewHolder holder, int position) {

        if(!simpleItemFour.get(position).getLogo().equals(""))
        {
            Picasso.get()
                    .load(simpleItemFour.get(position).getLogo())
                    .transform(new CropCircleTransformation())
                    .into(holder.img);
        }

        holder.title.setText(simpleItemFour.get(position).getFirstName());
        holder.subtitle.setText(simpleItemFour.get(position).getDesignation());
        holder.description.setText(simpleItemFour.get(position).getAddress());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context.getApplicationContext(), "You Click On " + simpleItemFour.get(position).getFirstName(),Toast.LENGTH_LONG).show();
                Intent i = new Intent(context, EmployeeDetails.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra(Constant.BundleExtra.EMPLOYEE_ID, simpleItemFour.get(position).getId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return simpleItemFour.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView title,subtitle,description;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.simpleitemfour_image);
            title = itemView.findViewById(R.id.simpleitemfour_title);
            subtitle = itemView.findViewById(R.id.simpleitemfour_subtitle);
            description = itemView.findViewById(R.id.simpleitemfour_description);
        }
    }
}