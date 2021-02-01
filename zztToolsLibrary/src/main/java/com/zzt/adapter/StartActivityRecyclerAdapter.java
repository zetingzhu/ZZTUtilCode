package com.zzt.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zzt.entity.StartActivityDao;
import com.zzt.toolslib.R;

import java.util.Arrays;
import java.util.List;

/**
 * @author: zeting
 * @date: 2021/1/4
 * 跳转到 activity 的列表适配器
 */
public class StartActivityRecyclerAdapter extends RecyclerView.Adapter<StartActivityRecyclerAdapter.ViewHolder> {
    private List<StartActivityDao> dataset;


    public StartActivityRecyclerAdapter(List<StartActivityDao> dataset) {
        this.dataset = dataset;
    }

    public static void setAdapterData(RecyclerView recyclerView, StartActivityDao[] dataArray) {
        setAdapterData(recyclerView, Arrays.asList(dataArray));
    }

    public static void setAdapterData(RecyclerView recyclerView, List<StartActivityDao> dataset) {
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
            recyclerView.setAdapter(new StartActivityRecyclerAdapter(dataset));
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(dataset.get(position).getTitle());
        holder.description.setText(dataset.get(position).getDescription());
        holder.activity = dataset.get(position).getActivity();
    }

    @Override
    public int getItemCount() {
        return dataset == null ? 0 : dataset.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        Class<?> activity = null;
        TextView title;
        TextView description;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (activity != null) {
                        itemView.getContext().startActivity(new Intent(itemView.getContext(), activity));
                    }
                }
            });
        }
    }
}
