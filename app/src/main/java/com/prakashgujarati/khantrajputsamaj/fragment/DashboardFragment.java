package com.prakashgujarati.khantrajputsamaj.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prakashgujarati.khantrajputsamaj.R;
import com.prakashgujarati.khantrajputsamaj.adapter.DashboardGridActivityCustomAdapter;
import com.prakashgujarati.khantrajputsamaj.adapter.DashboardListActivityCustomAdapter;
import com.prakashgujarati.khantrajputsamaj.commans.BaseFragment;
import com.prakashgujarati.khantrajputsamaj.model.DashboardGridActivityModel;
import com.prakashgujarati.khantrajputsamaj.model.DashboardListActivityModel;

import java.util.ArrayList;

public class DashboardFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private DashboardGridActivityModel dashboardGridActivityModel;
    private ArrayList<DashboardGridActivityModel> dashboardGridActivityModelArrayList;
    private DashboardGridActivityCustomAdapter dashboardGridActivityCustomAdapter;

    private RecyclerView recyclerView1;
    private RecyclerView.LayoutManager layoutManager1;
    private DashboardListActivityModel dashboardListActivityModel;
    private ArrayList<DashboardListActivityModel> dashboardListActivityArrayList;
    private DashboardListActivityCustomAdapter dashboardListActivityCustomAdapter;

    private int i;
    private View view;
    private String[][] gridview_array ={{String.valueOf(R.mipmap.congratulation),"અભિનંદન"},{String.valueOf(R.mipmap.matrimonial),"પરિચય"},{String.valueOf(R.mipmap.news),"જ્ઞાતિ ન્યુઝ"},{String.valueOf(R.mipmap.late),"અવસાન નોંધ"},
            {String.valueOf(R.mipmap.business),"વ્યવસાય"},{String.valueOf(R.mipmap.employee),"નોકરિયાત"},{String.valueOf(R.mipmap.education),"શિક્ષણ"},{String.valueOf(R.mipmap.job),"રોજગાર"},
            {String.valueOf(R.mipmap.supporter_s),"સપોર્ટર"},{String.valueOf(R.mipmap.magazine),"પુસ્તિકા"},{String.valueOf(R.mipmap.event),"આયોજનો"},{String.valueOf(R.mipmap.donate),"ડોનર"},
            {String.valueOf(R.mipmap.imagegallery),"ફોટો ગેલેરી"},{String.valueOf(R.mipmap.videogallery),"વિડીયો ગેલેરી"},{String.valueOf(R.mipmap.surname),"સરનેમ"},{String.valueOf(R.mipmap.contact),"સંપર્ક"}};
    private String[][] listview_array ={{String.valueOf(R.mipmap.community_s),"સંઘ અને જ્ઞાતિ ગ્રુપ"},{String.valueOf(R.mipmap.admin),"ફાઉન્ડર / એડમીન"},{String.valueOf(R.mipmap.rajput),"ઈતિહાસ"}};

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_dashboard, container, false);


        MainActivityObjectView();

        RecyclerviewgridDashboardFragment();
        RecyclerviewListDashboardFragment();
        return view;
    }

    private void RecyclerviewgridDashboardFragment() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        dashboardGridActivityModelArrayList = new ArrayList<>();
        for (i=0;i < gridview_array.length;i++)
        {
            String image = gridview_array[i][0];
            int grid_img = Integer.parseInt(image);

            dashboardGridActivityModel = new DashboardGridActivityModel();
            dashboardGridActivityModel.setImage(grid_img);
            dashboardGridActivityModel.setName(gridview_array[i][1]);
            dashboardGridActivityModelArrayList.add(dashboardGridActivityModel);
        }

        dashboardGridActivityCustomAdapter = new DashboardGridActivityCustomAdapter(getActivity(), dashboardGridActivityModelArrayList);
        recyclerView.setAdapter(dashboardGridActivityCustomAdapter);
        dashboardGridActivityCustomAdapter.notifyDataSetChanged();

    }
    private void RecyclerviewListDashboardFragment() {
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(layoutManager1);

        dashboardListActivityArrayList = new ArrayList<>();
        for (i=0;i< listview_array.length;i++)
        {
            String image1 = listview_array[i][0];
            int list_img = Integer.parseInt(image1);

            dashboardListActivityModel = new DashboardListActivityModel();
            dashboardListActivityModel.setExplore_image(list_img);
            dashboardListActivityModel.setExplore_name(listview_array[i][1]);
            dashboardListActivityModel.setArrow_image(R.drawable.ic_chevron_right_black_24dp);
            dashboardListActivityArrayList.add(dashboardListActivityModel);
        }

        dashboardListActivityCustomAdapter = new DashboardListActivityCustomAdapter(getActivity(), dashboardListActivityArrayList);
        recyclerView1.setAdapter(dashboardListActivityCustomAdapter);
        dashboardListActivityCustomAdapter.notifyDataSetChanged();
    }

    @SuppressLint("WrongConstant")
    private void MainActivityObjectView() {

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_main_activity);
        layoutManager = new GridLayoutManager(getActivity(), 4);

        recyclerView1 = (RecyclerView) view.findViewById(R.id.recyclerview_list_main_activity);
        layoutManager1= new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);

    }
}
