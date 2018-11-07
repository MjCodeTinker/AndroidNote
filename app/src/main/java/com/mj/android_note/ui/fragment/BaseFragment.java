package com.mj.android_note.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/**
 * Author      : MJ
 * Date        : 2018/10/27--上午12:20
 * Email       : miaojian_666@126.com
 * Description :
 */
public abstract class BaseFragment extends Fragment {
    protected View rootView;

    protected Activity mActivity;

    protected abstract int getLayoutId();

    protected abstract void init();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), container, false);
        }

        init();
        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }
}
