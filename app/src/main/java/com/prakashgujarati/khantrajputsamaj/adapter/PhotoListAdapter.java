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
import com.prakashgujarati.khantrajputsamaj.PhotoDetails;
import com.prakashgujarati.khantrajputsamaj.R;
import com.prakashgujarati.khantrajputsamaj.VideoDetails;
import com.prakashgujarati.khantrajputsamaj.api.ApiClient;
import com.prakashgujarati.khantrajputsamaj.model.Photo;
import com.prakashgujarati.khantrajputsamaj.model.Video;
import com.prakashgujarati.khantrajputsamaj.utils.Constant;

import java.util.ArrayList;

public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.ViewHolder> {
    private Context context;
   private ArrayList<Photo> photos;
    private Photo photosdata = new Photo();
    private View view;

    public PhotoListAdapter(FragmentActivity activity, ArrayList<Photo> photolist) {
        this.context = activity;
        this.photos = photolist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent , int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item_list,parent,false);
        return new PhotoListAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        RequestOptions requestOptions = new RequestOptions();
//        requestOptions = requestOptions.transforms(new CircleCrop());
//        requestOptions.placeholder(R.drawable.ic_user);
//        Glide.with(context)
//                .load(ApiClient.BASE_URL + "" + photosdata.getImage())
//                .apply(requestOptions)
//                .into(holder.iv_imageP);
        holder.tv_titleP.setText(photos.get(position).getEventTitle());
        holder.tv_subtitleP.setText(photos.get(position).getLocation());
        holder.tv_descriptionP.setText(photos.get(position).getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, PhotoDetails.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra(Constant.BundleExtra.PHOTO_ID, photos.get(position).getEventTitle());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_titleP,tv_subtitleP,tv_descriptionP;
        //ImageView iv_imageP;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_titleP = itemView.findViewById(R.id.photolist_item_title);
            tv_subtitleP = itemView.findViewById(R.id.photolist_item_author);
            tv_descriptionP = itemView.findViewById(R.id.photolist_item_datetime);
            //iv_imageP = itemView.findViewById(R.id.photolist_item_image);
        }
    }
}

