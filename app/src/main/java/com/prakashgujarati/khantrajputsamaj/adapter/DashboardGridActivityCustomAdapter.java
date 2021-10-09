package com.prakashgujarati.khantrajputsamaj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.prakashgujarati.khantrajputsamaj.MainActivity;
import com.prakashgujarati.khantrajputsamaj.fragment.AnniversaryFragment;
import com.prakashgujarati.khantrajputsamaj.fragment.BirthdayFragment;
import com.prakashgujarati.khantrajputsamaj.fragment.BookList;
import com.prakashgujarati.khantrajputsamaj.fragment.BusinessFragment;
import com.prakashgujarati.khantrajputsamaj.fragment.ContactFragment;
import com.prakashgujarati.khantrajputsamaj.fragment.DonerFragment;
import com.prakashgujarati.khantrajputsamaj.fragment.EducationFragment;
import com.prakashgujarati.khantrajputsamaj.fragment.EmployeeFragment;
import com.prakashgujarati.khantrajputsamaj.fragment.EventFragment;
import com.prakashgujarati.khantrajputsamaj.fragment.LateFragment;
import com.prakashgujarati.khantrajputsamaj.fragment.MatrimoneyFragment;
import com.prakashgujarati.khantrajputsamaj.fragment.NewsFragment;
import com.prakashgujarati.khantrajputsamaj.fragment.PhotoListFragment;
import com.prakashgujarati.khantrajputsamaj.fragment.PlacementFragment;
import com.prakashgujarati.khantrajputsamaj.fragment.SportsFragment;
import com.prakashgujarati.khantrajputsamaj.fragment.StudentsFragment;
import com.prakashgujarati.khantrajputsamaj.fragment.SurnameFragment;
import com.prakashgujarati.khantrajputsamaj.fragment.VideoListFragment;
import com.prakashgujarati.khantrajputsamaj.model.DashboardGridActivityModel;
import com.prakashgujarati.khantrajputsamaj.R;

import java.util.ArrayList;

public class DashboardGridActivityCustomAdapter extends RecyclerView.Adapter<DashboardGridActivityCustomAdapter.ViewHolder> {

    private Context context;
    private ArrayList<DashboardGridActivityModel> dashboardArrayList ;
    private View view;

    public DashboardGridActivityCustomAdapter(FragmentActivity dashboardFragment, ArrayList<DashboardGridActivityModel> dashboardGridActivityModelArrayList) {

        this.context = dashboardFragment;
        this.dashboardArrayList = dashboardGridActivityModelArrayList;
    }

    @Override
    public DashboardGridActivityCustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_dashboard_custom_grid, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DashboardGridActivityCustomAdapter.ViewHolder holder, final int position) {
        holder.image.setImageResource(dashboardArrayList.get(position).getImage());
        holder.name.setText(dashboardArrayList.get(position).getName());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position == 0)
                {
                    ((MainActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, new BirthdayFragment()).commit();
                }
                else if(position == 1)
                {
                    ((MainActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, new MatrimoneyFragment()).commit();
                }
                else if(position == 2)
                {
                    ((MainActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, new NewsFragment()).commit();
                }
                else if(position == 3)
                {
                    ((MainActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, new LateFragment()).commit();
                }
                else if(position == 4)
                {
                    ((MainActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, new BusinessFragment()).commit();
                }
                else if(position == 5)
                {
                    ((MainActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, new EmployeeFragment()).commit();
                }
                else if(position == 6)
                {
                    ((MainActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, new EducationFragment()).commit();
                }
                else if(position == 7)
                {
                    ((MainActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, new PlacementFragment()).commit();
                }
                else if(position == 8)
                {
                    ((MainActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, new SportsFragment()).commit();
                }
                else if(position == 9)
                {
                    ((MainActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, new BookList()).commit();
                }
                else if(position == 10)
                {
                    ((MainActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, new EventFragment()).commit();
                }
                else if(position == 11)
                {
                    ((MainActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, new DonerFragment()).commit();
                }
                else if(position == 12)
                {
                    ((MainActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, new PhotoListFragment()).commit();
                }
                else if(position == 13)
                {
                    ((MainActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, new VideoListFragment()).commit();
                }
                else if(position == 14)
                {
                    ((MainActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, new SurnameFragment()).commit();
                }
                else if(position == 15)
                {
                    ((MainActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, new ContactFragment()).commit();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dashboardArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.img_custom_grid_activity_main);
            name = itemView.findViewById(R.id.tv_name_main_activity);

        }
    }
}
