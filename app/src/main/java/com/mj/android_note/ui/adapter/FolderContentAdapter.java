package com.mj.android_note.ui.adapter;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mj.android_note.R;
import com.mj.android_note.bean.FileOrFolderBean;
import com.mj.android_note.utils.DateUtils;

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
        vh.ivType.setImageResource(fileOrFolderBean.isFolder() ? R.drawable.ic_db_folder : R.drawable.arrow_down);
        vh.tvName.setText(fileOrFolderBean.isFolder() ? fileOrFolderBean.getFolderName() : fileOrFolderBean.getFileName());
        vh.tvTime.setText(DateUtils.convertTimeStampToStr(fileOrFolderBean.getCreateTime(), DateUtils.YMD_HS));
        vh.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private static class VH extends RecyclerView.ViewHolder {

        LinearLayout rootView;
        ImageView ivType;
        TextView tvName;
        TextView tvTime;

        VH(@NonNull View itemView) {
            super(itemView);
            rootView = itemView.findViewById(R.id.folder_content_root_view);
            ivType = itemView.findViewById(R.id.folder_content_iv_type);
            tvName = itemView.findViewById(R.id.folder_content_tv_name);
            tvTime = itemView.findViewById(R.id.folder_content_tv_time);
        }
    }
}
