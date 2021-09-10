package com.prakashgujarati.khantrajputsamaj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.prakashgujarati.khantrajputsamaj.R;
import com.prakashgujarati.khantrajputsamaj.model.SimpleItemFour;
import com.prakashgujarati.khantrajputsamaj.model.Video;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class VideoItemListAdapter extends RecyclerView.Adapter<VideoItemListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Video> video;
    private View view;

    public VideoItemListAdapter(FragmentActivity activity, ArrayList<Video> video) {
        this.context = activity;
        this.video = video;
    }

    @Override
    public VideoItemListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item_list, parent, false);
        return new VideoItemListAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(VideoItemListAdapter.ViewHolder holder, int position) {

        if(!video.get(position).getImage().equals(""))
        {
            Picasso.get()
                    .load(video.get(position).getImage())
                    .transform(new CropCircleTransformation())
                    .into(holder.img);
        }

        holder.title.setText(video.get(position).getTitle());
        holder.subtitle.setText(video.get(position).getSubtitle());
        holder.description.setText(video.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return video.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView title,subtitle,description;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.videolist_item_image);
            title = itemView.findViewById(R.id.videolist_item_title);
            subtitle = itemView.findViewById(R.id.videolist_item_author);
            description = itemView.findViewById(R.id.videolist_item_datetime);
        }
    }
}