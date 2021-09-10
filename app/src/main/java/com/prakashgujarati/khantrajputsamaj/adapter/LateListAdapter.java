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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.prakashgujarati.khantrajputsamaj.BusinessDetails;
import com.prakashgujarati.khantrajputsamaj.CandidateDetails;
import com.prakashgujarati.khantrajputsamaj.LateDetails;
import com.prakashgujarati.khantrajputsamaj.R;
import com.prakashgujarati.khantrajputsamaj.api.ApiClient;
import com.prakashgujarati.khantrajputsamaj.model.Business;
import com.prakashgujarati.khantrajputsamaj.model.Late;
import com.prakashgujarati.khantrajputsamaj.model.Matrimoney;
import com.prakashgujarati.khantrajputsamaj.utils.Constant;

import java.util.ArrayList;

public class LateListAdapter extends RecyclerView.Adapter<LateListAdapter.ViewHolder> {

    private Context context;
    private ImageView iv_logo;
    private ArrayList<Late> lates;
    private Late latesData = new Late();
    private View view;

    public LateListAdapter(FragmentActivity activity, ArrayList<Late> lates) {
        this.context = activity;
        this.lates = lates;
    }

    @NonNull
    @Override
    public LateListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.late_list_card, parent, false);
        return new LateListAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // iv_logo = iv_logo.findViewById(R.id.late_image);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CircleCrop());
        requestOptions.placeholder(R.drawable.ic_user);
        Glide.with(context)
                .load(ApiClient.BASE_URL + "" + latesData.getPicture())
                .apply(requestOptions)
                .into(holder.img);
        holder.name.setText(lates.get(position).getFirstName());
        holder.note.setText(lates.get(position).getLateDate());


       /* holder.vanue.setText(lates.get(position).getVanue());
        holder.description.setText(lates.get(position).getDescription());
        holder.type.setText(lates.get(position).getType());*/

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, LateDetails.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra(Constant.BundleExtra.LATE_ID, lates.get(position).getId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView name;
        private TextView note;
        private TextView vanue;
        private TextView description;
        private TextView type;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.late_image);
            name = itemView.findViewById(R.id.late_name);
            note = itemView.findViewById(R.id.late_note);
            //vanue = itemView.findViewById(R.id.matrimoney_list_like);
            //type = itemView.findViewById(R.id.matrimoney_list_like);
            //description = itemView.findViewById(R.id.short_description);
        }
    }
}
