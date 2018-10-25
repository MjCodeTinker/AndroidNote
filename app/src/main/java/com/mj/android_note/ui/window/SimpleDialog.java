package com.mj.android_note.ui.window;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.mj.android_note.R;
import com.mj.android_note.ui.adapter.DialogListAdapter;
import com.mj.android_note.utils.LogUtil;

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
//        initListDialogWindow(referenceView);
        setContentView(R.layout.dialog_list_layout);
        RecyclerView listView = findViewById(R.id.dialog_list_view);
        listView.setLayoutManager(new LinearLayoutManager(context));
        DialogListAdapter dialogListAdapter = new DialogListAdapter(onItemClickListener);
        dialogListAdapter.setData(itemContents);
        listView.setAdapter(dialogListAdapter);
    }

    @SuppressLint("RtlHardcoded")
    public void initListDialogWindow(View referenceView) {
        Window window = getWindow();
        if (window != null) {
            window.setDimAmount(0f);
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = Gravity.TOP | Gravity.RIGHT;
            Rect rect = new Rect();
            referenceView.getDrawingRect(rect);
//            attributes.y = rect.top + referenceView.getHeight();
            LogUtil.e(TAG, "top = " + rect.top + "-- height=" + referenceView.getHeight());
            window.setAttributes(attributes);
        }
    }

    public interface OnItemClickListener {
        void onclick(String itemName);
    }

}
