package com.example.foodman;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReportsAdapter extends
        RecyclerView.Adapter<ReportsAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public LinearLayout parent;

        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.reportname);
            parent = itemView.findViewById(R.id.parent);
        }
    }

    private List<Report> mReports;
    final Context mconext;

    // Pass in the contact array into the constructor
    public ReportsAdapter(List<Report> reports, Context context) {
        mReports = reports;
        mconext = context;
    }

        @Override
        public ReportsAdapter.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType)
        {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View reportView = inflater.inflate(R.layout.reportlayout, parent, false);

        // Return a new holder instance
            ViewHolder viewHolder = new ViewHolder(reportView);
            return viewHolder;
    }

        // Involves populating data into the item through holder
        @Override
        public void onBindViewHolder (ReportsAdapter.ViewHolder holder,int position){
        // Get the data model based on position
        final Report report = mReports.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.nameTextView;
        textView.setText(report.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(mconext, ReportActivity.class);
                intent.putExtra("Report", report);
                mconext.startActivity(intent);
            }
        });
    }

        // Returns the total count of items in the list
        @Override
        public int getItemCount () {
        return mReports.size();
    }
}