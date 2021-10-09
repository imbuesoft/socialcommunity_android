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
import com.prakashgujarati.khantrajputsamaj.CandidateDetails;
import com.prakashgujarati.khantrajputsamaj.api.ApiClient;
import com.prakashgujarati.khantrajputsamaj.model.Candidate;
import com.prakashgujarati.khantrajputsamaj.R;
import com.prakashgujarati.khantrajputsamaj.utils.Constant;

import java.util.ArrayList;

public class CandidateRecyclerAdapter extends RecyclerView.Adapter<CandidateRecyclerAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Candidate> items;
    private  Candidate candidatedata = new Candidate();
    private View view;

    public CandidateRecyclerAdapter(FragmentActivity activity, ArrayList<Candidate> items) {
        this.context = activity;
        this.items = items;
    }

    @NonNull
    @Override
    public CandidateRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.matrimoney_list_card, parent, false);
        return new CandidateRecyclerAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(items.get(position).getFirstName() + " " + items.get(position).getLastName());
        holder.description.setText(
                items.get(position).getHeight()
                        + " " + items.get(position).getWeight()
                        + "\n" + items.get(position).getEducation()
                        + "\n" + items.get(position).getBirthPlace());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_user);
        Glide.with(context)
                .load(ApiClient.IMAGE_URL + "" + items.get(position).getPicture())
                .apply(requestOptions)
                .into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, CandidateDetails.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra(Constant.BundleExtra.CANDIDATE_ID, items.get(position).getId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView name;
        private TextView description;
      /*  private ImageView getview;
        private ImageView getlike;
        private ImageView getfav;*/

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.matrimoney_image);
            name = itemView.findViewById(R.id.matrimoney_list_title);
            description = itemView.findViewById(R.id.short_description);
           /* getview = itemView.findViewById(R.id.matrimoney_list_view);
            getlike = itemView.findViewById(R.id.matrimoney_list_like);
            getfav = itemView.findViewById(R.id.matrimoney_list_fav);*/
        }
    }
}
