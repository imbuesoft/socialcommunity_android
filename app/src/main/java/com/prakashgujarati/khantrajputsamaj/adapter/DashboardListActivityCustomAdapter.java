package com.prakashgujarati.khantrajputsamaj.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.prakashgujarati.khantrajputsamaj.model.DashboardListActivityModel;
import com.prakashgujarati.khantrajputsamaj.R;
import com.prakashgujarati.khantrajputsamaj.WebActivity;

import java.util.ArrayList;

public class DashboardListActivityCustomAdapter extends RecyclerView.Adapter<DashboardListActivityCustomAdapter.ViewHolder> {

    private Context context;
    private ArrayList<DashboardListActivityModel> dashboardListActivityModelArrayList;
    private View view;

    public DashboardListActivityCustomAdapter(FragmentActivity activity, ArrayList<DashboardListActivityModel> dashboardListActivityModelArrayList) {
        this.context = activity;
        this.dashboardListActivityModelArrayList = dashboardListActivityModelArrayList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_dashboard_custom_list, parent, false);

        return new DashboardListActivityCustomAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DashboardListActivityCustomAdapter.ViewHolder holder, final int position) {
        holder.explore_image.setImageResource(dashboardListActivityModelArrayList.get(position).getExplore_image());
        holder.explore_name.setText(dashboardListActivityModelArrayList.get(position).getExplore_name());
        holder.arrow_image.setImageResource(dashboardListActivityModelArrayList.get(position).getArrow_image());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position ==0)
                {
                    String url = "https://khantrajputsamaj.in/committe";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                  //  i.putExtra("wTitle","RMTS & BRTS Map Rajkot");
                   // i.putExtra("wUrl","file:///android_asset/rmts_map.html");
                    context.startActivity(i);
                }
                else if(position ==1)
                {
                    String url = "https://khantrajputsamaj.in/contact";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                   // i.putExtra("wTitle","How to Travel ?");
                  //  i.putExtra("wUrl","https://en.wikipedia.org/wiki/Rajkot");
                    context.startActivity(i);

                }
                else if(position ==2)
                {
                    String url = "https://en.wikipedia.org/wiki/Khant_Koli";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                   // i.putExtra("wTitle","Popular Places");
                  //  i.putExtra("wUrl","https://wikitravel.org/en/Rajkot");
                    context.startActivity(i);
                }
                else if(position ==3)
                {
                    Intent i = new Intent(context,WebActivity.class);
                    i.putExtra("wTitle","Shopping Malls");
                    i.putExtra("wUrl","file:///android_asset/rajkot_shop.html");
                    context.startActivity(i);
                }
                else if(position ==3)
                {
                    Intent i = new Intent(context,WebActivity.class);
                    i.putExtra("wTitle","Shopping Malls");
                    i.putExtra("wUrl","file:///android_asset/rajkot_shop.html");
                    context.startActivity(i);
                }
                else if(position ==4)
                {
                    Intent i = new Intent(context,WebActivity.class);
                    i.putExtra("wTitle","Food & Drinks");
                    i.putExtra("wUrl","https://www.tripadvisor.in/Restaurants-g660181-Rajkot_Rajkot_District_Gujarat.html");
                    context.startActivity(i);
                }
                else if(position ==5)
                {
                    Intent i = new Intent(context,WebActivity.class);
                    i.putExtra("wTitle","Hotels");
                    i.putExtra("wUrl","https://www.tripadvisor.in/Hotels-g660181-Rajkot_Rajkot_District_Gujarat-Hotels.html");
                    context.startActivity(i);
                }




            }
        });
        if (position % 2 == 1) {
            view.setBackgroundColor(Color.parseColor("#F5FFFFFF"));
        } else {
            view.setBackgroundColor(Color.parseColor("#F5E7E7E7"));
        }



    }

    @Override
    public int getItemCount() {
        return dashboardListActivityModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView explore_image;
        private TextView explore_name;
        private ImageView arrow_image;
        private LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            explore_image = itemView.findViewById(R.id.img_explore_activity_dashboard);
            arrow_image = itemView.findViewById(R.id.img_arrow_activity_dashboard);
            explore_name = itemView.findViewById(R.id.txt_explore_activity_dashboard);
            linearLayout = itemView.findViewById(R.id.ly_dashboard_activity);

        }
    }
}
