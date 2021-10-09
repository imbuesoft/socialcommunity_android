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
import com.prakashgujarati.khantrajputsamaj.NewsDetails;
import com.prakashgujarati.khantrajputsamaj.R;
import com.prakashgujarati.khantrajputsamaj.SportsDetails;
import com.prakashgujarati.khantrajputsamaj.api.ApiClient;
import com.prakashgujarati.khantrajputsamaj.model.Late;
import com.prakashgujarati.khantrajputsamaj.model.News;
import com.prakashgujarati.khantrajputsamaj.model.Sports;
import com.prakashgujarati.khantrajputsamaj.utils.Constant;

import java.util.ArrayList;

public class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Sports> sports;
    private Sports sportsData = new Sports();
    private View view;

    public SportsAdapter(FragmentActivity activity, ArrayList<Sports> sports) {
        this.context = activity;
        this.sports = sports;
    }

    @Override
    public SportsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sports_list_card, parent, false);
        return new SportsAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CircleCrop());
        requestOptions.placeholder(R.drawable.ic_user);
        Glide.with(context)
                .load(ApiClient.IMAGE_URL + "" + sportsData.getThumbnail())
                .apply(requestOptions)
                .into(holder.img);
        holder.title.setText(sports.get(position).getTitle());
        holder.desc.setText(sports.get(position).getHeadline());
        holder.time.setText(sports.get(position).getReported_Datetime());
        //holder.pimg.setImageResource(news.get(position).getThumbnail());
        //holder.img.setImageResource(Integer.parseInt(news.get(position).getNews_Image()));
        // holder.desc.setText(news.get(position).getDescription());
        // holder.time.setText(news.get(position).getTime());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, SportsDetails.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra(Constant.BundleExtra.SPORTS_ID, sports.get(position).getId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sports.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView title,desc,time;


        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.news_image);
            title = itemView.findViewById(R.id.news_title);
            desc = itemView.findViewById(R.id.news_description);
            time = itemView.findViewById(R.id.news_time);
        }
    }
}