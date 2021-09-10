package com.prakashgujarati.khantrajputsamaj.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.prakashgujarati.khantrajputsamaj.model.CandidateData;
import com.prakashgujarati.khantrajputsamaj.R;

import java.util.List;

public class ProfileDetailAdapter extends RecyclerView.Adapter<ProfileDetailAdapter.ViewHolder> {

    private List<CandidateData> CandidateDataList;

    public ProfileDetailAdapter(List<CandidateData> CandidateDataList) {
        this.CandidateDataList = CandidateDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.profiledetail_item_layout, parent, false);

        return new ProfileDetailAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CandidateData item = CandidateDataList.get(position);
        holder.txtDataField.setText(item.getDataField());
        holder.txtDataValue.setText(item.getDataValue());
    }

    @Override
    public int getItemCount() {
        return CandidateDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtDataField, txtDataValue;

        public ViewHolder(View view) {
            super(view);
            txtDataField = (TextView) view.findViewById(R.id.txtDataField);
            txtDataValue = (TextView) view.findViewById(R.id.txtDataValue);
        }
    }
}