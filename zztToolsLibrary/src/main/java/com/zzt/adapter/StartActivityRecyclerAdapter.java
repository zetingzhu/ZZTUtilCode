package com.zzt.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
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

    private OnItemClickListener<StartActivityDao> listener;

    public StartActivityRecyclerAdapter(List<StartActivityDao> dataset) {
        this.dataset = dataset;
    }

    public static void setAdapterData(RecyclerView recyclerView, int orientation, StartActivityDao[] dataArray, OnItemClickListener<StartActivityDao> lis) {
        setAdapterData(recyclerView, orientation, Arrays.asList(dataArray), lis);
    }

    public static void setAdapterData(RecyclerView recyclerView, int orientation, List<StartActivityDao> dataset, OnItemClickListener<StartActivityDao> lis) {
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), orientation, false));
            StartActivityRecyclerAdapter adapter = new StartActivityRecyclerAdapter(dataset);
            adapter.setListener(lis);
            //添加 分割线
            DividerItemDecoration decoration = new DividerItemDecoration(recyclerView.getContext(), orientation);
            decoration.setDrawable(new ColorDrawable(Color.parseColor("#DFE4F8")));
            recyclerView.addItemDecoration(decoration);
            recyclerView.setAdapter(adapter);
        }
    }

    public void setListener(OnItemClickListener<StartActivityDao> listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int pos) {
        int position = holder.getAdapterPosition();
        holder.title.setText(dataset.get(position).getTitle());
        holder.description.setText(dataset.get(position).getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (listener != null) {
                    listener.onItemClick(holder.itemView, position, dataset.get(position));
                }
                Class<?> activity = dataset.get(position).getActivity();
                if (dataset.get(position).isDefaultClass()) {
                    int contentViewId = dataset.get(position).getContentViewId();
                    if (contentViewId != 0) {
                        Intent intent = new Intent(holder.itemView.getContext(), activity);
                        intent.putExtra("layout_content_view_id", dataset.get(position).getContentViewId());
                        holder.itemView.getContext().startActivity(intent);
                    }
                } else {
                    if (activity != null) {
                        Intent intent = new Intent(holder.itemView.getContext(), activity);
                        Bundle bundleString = dataset.get(position).getBundleString();
                        if (bundleString != null) {
                            intent.putExtras(bundleString);
                        }
                        holder.itemView.getContext().startActivity(intent);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset == null ? 0 : dataset.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
        }
    }

    public interface OnItemClickListener<T> {
        void onItemClick(View itemView, int position, T data);
    }

}
