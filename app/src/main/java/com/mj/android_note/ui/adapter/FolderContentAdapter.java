package com.mj.android_note.ui.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mj.android_note.R;
import com.mj.android_note.bean.FileOrFolderBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Author      : MJ
 * Date        : 2018/10/27--上午1:00
 * Email       : miaojian_666@126.com
 * Description : 文件夹内容的adapter
 */
public class FolderContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<FileOrFolderBean> data = new ArrayList<>();

    public void refreshList(List<FileOrFolderBean> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        @SuppressLint("InflateParams") View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_folder_content, null);
        return new VH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        VH vh = (VH) viewHolder;
        FileOrFolderBean fileOrFolderBean = data.get(i);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private static class VH extends RecyclerView.ViewHolder {

        VH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
