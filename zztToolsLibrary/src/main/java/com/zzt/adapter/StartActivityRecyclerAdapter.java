package com.zzt.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
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

    private OnItemClickListener listener;

    public StartActivityRecyclerAdapter(List<StartActivityDao> dataset) {
        this.dataset = dataset;
    }

    public static void setAdapterData(RecyclerView recyclerView, int orientation, StartActivityDao[] dataArray, OnItemClickListener lis) {
        setAdapterData(recyclerView, orientation, Arrays.asList(dataArray), lis);
    }

    public static void setAdapterData(RecyclerView recyclerView, int orientation, List<StartActivityDao> dataset, OnItemClickListener lis) {
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

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.title.setText(dataset.get(position).getTitle());
        holder.description.setText(dataset.get(position).getDescription());
        final Class<?> activity = dataset.get(position).getActivity();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activity != null) {
                    holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(), activity));
                } else if (listener != null) {
                    listener.onItemClick(position, dataset.get(position));
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

    public interface OnItemClickListener {
        void onItemClick(int position, Object data);
    }

}
