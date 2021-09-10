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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.prakashgujarati.khantrajputsamaj.BusinessDetails;
import com.prakashgujarati.khantrajputsamaj.R;
import com.prakashgujarati.khantrajputsamaj.api.ApiClient;
import com.prakashgujarati.khantrajputsamaj.model.Business;
import com.prakashgujarati.khantrajputsamaj.utils.Constant;

import java.util.ArrayList;

public class BusinessListAdapter extends RecyclerView.Adapter<BusinessListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Business> business;
    private Business businessdata = new Business();
    private View view;

    public BusinessListAdapter(FragmentActivity activity, ArrayList<Business> business) {
        this.context = activity;
        this.business = business;
    }

    @Override
    public BusinessListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.business_list_card, parent, false);
        return new BusinessListAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CircleCrop());
        requestOptions.placeholder(R.drawable.ic_user);
        Glide.with(context)
                .load(ApiClient.BASE_URL + "" + businessdata.getLogo())
                .apply(requestOptions)
                .into(holder.img);
        holder.company.setText(business.get(position).getCompany());
        holder.category.setText(business.get(position).getCategory());
        holder.address.setText(business.get(position).getAddress());
       /* holder.vanue.setText(business.get(position).getVanue());
        holder.description.setText(business.get(position).getDescription());
        holder.type.setText(business.get(position).getType());*/

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, BusinessDetails.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra(Constant.BundleExtra.BUSINESS_ID, business.get(position).getId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return business.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView company,category,address;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.bus_image);
            company = itemView.findViewById(R.id.bus_title);
            category = itemView.findViewById(R.id.bus_category);
            address = itemView.findViewById(R.id.bus_address);
            //vanue = itemView.findViewById(R.id.matrimoney_list_like);
            //type = itemView.findViewById(R.id.matrimoney_list_like);
            //description = itemView.findViewById(R.id.short_description);
        }
    }
}
