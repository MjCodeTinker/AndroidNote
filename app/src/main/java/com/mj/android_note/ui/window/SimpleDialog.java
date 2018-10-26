package com.mj.android_note.ui.window;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mj.android_note.R;
import com.mj.android_note.bean.FileOrFolderBean;
import com.mj.android_note.ui.adapter.DialogListAdapter;
import com.mj.android_note.utils.LogUtil;
import com.mj.android_note.utils.ScreenUtil;

import java.util.List;

/**
 * Author      : MJ
 * Date        : 2018/10/25--17:41
 * Email       : miaojian@conew.com
 * Description : 弹窗
 */

public class SimpleDialog extends Dialog {

    private static final String TAG = "SimpleDialog";
    private Context context;

    public SimpleDialog(@NonNull Context context) {
        super(context);
        init(context);
    }

    public SimpleDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    protected SimpleDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
    }

    public void initListDialog(@NonNull View referenceView, List<String> itemContents, OnItemClickListener onItemClickListener) {
        initListDialogWindow(referenceView);
        setContentView(R.layout.dialog_list_layout);
        RecyclerView listView = findViewById(R.id.dialog_list_view);
        listView.setLayoutManager(new LinearLayoutManager(context));
        DialogListAdapter dialogListAdapter = new DialogListAdapter(onItemClickListener);
        dialogListAdapter.setData(itemContents);
        listView.setAdapter(dialogListAdapter);
    }

    @SuppressLint("RtlHardcoded")
    private void initListDialogWindow(View referenceView) {
        Window window = getWindow();
        if (window != null) {
            window.setDimAmount(0);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = Gravity.TOP | Gravity.RIGHT;
            Rect rect = new Rect();
            referenceView.getDrawingRect(rect);
            attributes.y = referenceView.getHeight();
            LogUtil.i(TAG, "height dp = " + ScreenUtil.px2dp(referenceView.getHeight()));
            window.setAttributes(attributes);
        }
    }


    public void initCreateFolderOrFileDialog(boolean createFolder, @NonNull FolderOrFileDialogListener<FileOrFolderBean> fileDialogListener) {
        setContentView(R.layout.dialog_create_folder_or_file_layout);

        final FolderOrFileViewHolder fileViewHolder = new FolderOrFileViewHolder();

        setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && fileViewHolder.llFolderListRootView.getVisibility() == View.VISIBLE) {
                    fileViewHolder.llFolderListRootView.setVisibility(View.GONE);
                    fileViewHolder.fileOrFolderCreateRootView.setVisibility(View.VISIBLE);
                    return true;
                }
                return false;
            }
        });
        fileViewHolder.tvTitle.setText(createFolder ? "新建文件夹" : "新建收藏");
        fileViewHolder.etName.setHint(createFolder ? "请输入文件夹名称" : "请输入文件名称");
        fileViewHolder.fileOrFolderCreateRootView.setVisibility(View.VISIBLE);
        fileViewHolder.rlLocation.setOnClickListener(new ViewClick(createFolder, fileViewHolder, fileDialogListener));
        fileViewHolder.tvFileOrFolderCancel.setOnClickListener(new ViewClick(createFolder, fileViewHolder, fileDialogListener));
        fileViewHolder.tvFileOrFolderSave.setOnClickListener(new ViewClick(createFolder, fileViewHolder, fileDialogListener));

    }


    private class ViewClick implements View.OnClickListener {

        FolderOrFileViewHolder holder;
        FolderOrFileDialogListener<FileOrFolderBean> fileDialogListener;
        boolean createFolder;

        ViewClick(boolean createFolder, FolderOrFileViewHolder holder, FolderOrFileDialogListener<FileOrFolderBean> fileDialogListener) {
            this.createFolder = createFolder;
            this.holder = holder;
            this.fileDialogListener = fileDialogListener;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.dialog_create_folder_or_file_btn_cancel:
                    fileDialogListener.negative();
                    break;
                case R.id.dialog_create_folder_or_file_btn_save:
                    FileOrFolderBean folderBean = new FileOrFolderBean();
                    String name = holder.etName.getText().toString().trim();
                    folderBean.setCreateTime(System.currentTimeMillis());
                    folderBean.setFolder(createFolder);
                    if (createFolder) {
                        folderBean.setFolderName(name);
                    } else {
                        folderBean.setFileName(name);
                    }

                    fileDialogListener.positive(folderBean);
                    break;
                case R.id.dialog_create_folder_or_file_rl_location:
                    holder.llFolderListRootView.setVisibility(View.VISIBLE);
                    holder.fileOrFolderCreateRootView.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
        }

    }

    private class FolderOrFileViewHolder {

        LinearLayout fileOrFolderCreateRootView;
        LinearLayout llFolderListRootView;
        TextView tvTitle;
        EditText etName;
        RelativeLayout rlLocation;
        TextView tvLocation;
        TextView tvFileOrFolderCancel;
        TextView tvFileOrFolderSave;

        FolderOrFileViewHolder() {
            fileOrFolderCreateRootView = findViewById(R.id.dialog_create_folder_root_view);
            llFolderListRootView = findViewById(R.id.dialog_folder_list_root_view);
            tvTitle = findViewById(R.id.dialog_create_folder_or_file_tv_title);
            etName = findViewById(R.id.dialog_create_folder_or_file_et_name);
            rlLocation = findViewById(R.id.dialog_create_folder_or_file_rl_location);
            tvLocation = findViewById(R.id.dialog_create_folder_or_file_tv_location);
            tvFileOrFolderCancel = findViewById(R.id.dialog_create_folder_or_file_btn_cancel);
            tvFileOrFolderSave = findViewById(R.id.dialog_create_folder_or_file_btn_save);
        }
    }

    public interface FolderOrFileDialogListener<T> {
        void negative();

        void positive(T t);
    }


    public interface OnItemClickListener {
        void onclick(String itemName);
    }

}
