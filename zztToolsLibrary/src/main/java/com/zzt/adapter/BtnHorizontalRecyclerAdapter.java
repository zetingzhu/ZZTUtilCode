package com.zzt.adapter;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * @author: zeting
 * @date: 2022/1/11
 * 横向默认按钮
 */
public class BtnHorizontalRecyclerAdapter extends RecyclerView.Adapter<BtnHorizontalRecyclerAdapter.ViewHolder> {
    private List<String> dataset;

    private OnItemClickListener<String> listener;

    public BtnHorizontalRecyclerAdapter(List<String> dataset) {
        this.dataset = dataset;
    }

    public static BtnHorizontalRecyclerAdapter setAdapterData(RecyclerView recyclerView, List<String> dataset, OnItemClickListener<String> lis) {
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), RecyclerView.HORIZONTAL, false));
            BtnHorizontalRecyclerAdapter adapter = new BtnHorizontalRecyclerAdapter(dataset);
            adapter.setListener(lis);
            recyclerView.setAdapter(adapter);
            return adapter ;
        }
        return null;
    }

    public void setListener(OnItemClickListener<String> listener) {
        this.listener = listener;
    }

    public List<String> getDataset() {
        return dataset;
    }

    public void setDataset(List<String> dataset) {
        this.dataset = dataset;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AppCompatButton btn = new AppCompatButton(parent.getContext());
        return new ViewHolder(btn);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int pos) {
        int position = holder.getAdapterPosition();
        holder.getBtn().setText(dataset.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (listener != null) {
                    listener.onItemClick(holder.itemView, position, dataset.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset == null ? 0 : dataset.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
        }

        public AppCompatButton getBtn() {
            return (AppCompatButton) itemView;
        }

    }

    public interface OnItemClickListener<T> {
        void onItemClick(View itemView, int position, T data);
    }

}
