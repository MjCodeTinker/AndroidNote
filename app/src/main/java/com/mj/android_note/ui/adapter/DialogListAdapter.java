package com.mj.android_note.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mj.android_note.R;
import com.mj.android_note.ui.window.SimpleDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Author      : MJ
 * Date        : 2018/10/25--17:49
 * Email       : miaojian@conew.com
 * Description :
 */

public class DialogListAdapter extends RecyclerView.Adapter<DialogListAdapter.Holder> {


    private List<String> data = new ArrayList<>();
    private SimpleDialog.OnItemClickListener itemClickListener;

    public DialogListAdapter(SimpleDialog.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setData(List<String> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dialog_list_item, null);
        return new Holder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        final String content = data.get(i);
        holder.tvContent.setText(content);
        holder.tvContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onclick(content);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class Holder extends RecyclerView.ViewHolder {

        private TextView tvContent;

        Holder(@NonNull View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.dialog_list_item_tv);
        }

    }
}
