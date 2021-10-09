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
import com.prakashgujarati.khantrajputsamaj.EducationDetails;
import com.prakashgujarati.khantrajputsamaj.R;
import com.prakashgujarati.khantrajputsamaj.api.ApiClient;
import com.prakashgujarati.khantrajputsamaj.model.Education;
import com.prakashgujarati.khantrajputsamaj.utils.Constant;

import java.util.ArrayList;

public class EducationListAdapter extends RecyclerView.Adapter<EducationListAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Education> educations;
    private Education educationdata = new Education();
    private View view;

    public EducationListAdapter(FragmentActivity activity, ArrayList<Education> educations) {
        this.context = activity;
        this.educations = educations;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_education_listcard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.education_name.setText(educations.get(position).getName());
        holder.education_qualification.setText(educations.get(position).getQualification());
        holder.education_gender.setText(educations.get(position).getGender());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CircleCrop());
        requestOptions.placeholder(R.drawable.ic_user);
        Glide.with(context)
                .load(ApiClient.IMAGE_URL + "" + educationdata.getPicture())
                .apply(requestOptions)
                .into(holder.education_image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, EducationDetails.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra(Constant.BundleExtra.EDUCATION_ID, educations.get(position).getId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return educations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView education_image;
        private TextView education_name,education_qualification,education_gender;

        public ViewHolder(View itemView) {
            super(itemView);
            education_image = itemView.findViewById(R.id.education_image);
            education_name = itemView.findViewById(R.id.education_name);
            education_qualification = itemView.findViewById(R.id.education_qualification);
            education_gender = itemView.findViewById(R.id.education_gender);
            //vanue = itemView.findViewById(R.id.matrimoney_list_like);
            //type = itemView.findViewById(R.id.matrimoney_list_like);
            //description = itemView.findViewById(R.id.short_description);
        }
    }
}
