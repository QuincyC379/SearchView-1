package com.madbrains.searchview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private ArrayList<String> originalNamesList;
    private Context context;

    public static ArrayList<String> names;

    public DataAdapter(ArrayList<String> names, Context context) {
        this.originalNamesList = names;
        this.context = context;

        this.names = originalNamesList;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(names.get(position));
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        public ViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.name);
        }
    }

    public void updateData(String text) {

        if (text.equals("")){

            names = originalNamesList;

            notifyDataSetChanged();

        } else {

            ArrayList<String> newNames = new ArrayList<>();

            for (String name : originalNamesList) {
                for (int i = 0; i <= (name.length() - text.length()); i++) {
                    if (name.regionMatches(true, i, text, 0, text.length())) {
                        newNames.add(name);
                    }
                }
            }

            names = newNames;

            notifyDataSetChanged();
        }
    }
}
