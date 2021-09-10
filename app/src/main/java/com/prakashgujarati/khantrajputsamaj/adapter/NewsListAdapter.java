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
import com.prakashgujarati.khantrajputsamaj.api.ApiClient;
import com.prakashgujarati.khantrajputsamaj.model.Late;
import com.prakashgujarati.khantrajputsamaj.model.News;
import com.prakashgujarati.khantrajputsamaj.utils.Constant;

import java.util.ArrayList;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<News> news;
    private News newsData = new News();
    private View view;

    public NewsListAdapter(FragmentActivity activity, ArrayList<News> news) {
        this.context = activity;
        this.news = news;
    }

    @Override
    public NewsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_card, parent, false);
        return new NewsListAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CircleCrop());
        requestOptions.placeholder(R.drawable.ic_user);
        Glide.with(context)
                .load(ApiClient.BASE_URL + "" + newsData.getThumbnail())
                .apply(requestOptions)
                .into(holder.img);
        holder.title.setText(news.get(position).getTitle());
        holder.desc.setText(news.get(position).getHeadline());
        holder.time.setText(news.get(position).getReported_Datetime());
        //holder.pimg.setImageResource(news.get(position).getThumbnail());
        //holder.img.setImageResource(Integer.parseInt(news.get(position).getNews_Image()));
       // holder.desc.setText(news.get(position).getDescription());
       // holder.time.setText(news.get(position).getTime());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, NewsDetails.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra(Constant.BundleExtra.NEWS_ID, news.get(position).getId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return news.size();
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
