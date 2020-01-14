package com.mj.android_note.ui.activity.butter_knife;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mj.android_note.R;
import com.mj.android_note.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Author      : MJ
 * Date        : 2019-11-22--23:27
 * Email       : miaojian_666@126.com
 * Description :
 */
public class TestButterKnifeActivity extends AppCompatActivity {

    @BindView(R.id.activity_test_butter_knife_tv)
    TextView tv;

    @OnClick(R.id.activity_test_butter_knife_tv)
    void textViewClick(View v) {
        ToastUtils.showShortToast("我是textView 我被点击了：" + tv.getText());
    }


    // bind
    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_butter_knife);
        bind = ButterKnife.bind(this);

        tv.setText(String.format("%s", "i am miaojian"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind != null) {
            bind.unbind();
        }
    }


    /**
     * 如果数组是单调递增或单调递减的，那么它是单调的。
     * 如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。
     * 当给定的数组 A 是单调数组时返回 true，否则返回 false
     * <p>
     * 示例 1：
     * <p>
     * 输入：[1,2,2,3]
     * 输出：true
     * 示例 2：
     * <p>
     * 输入：[6,5,4,4]
     * 输出：true
     * 示例 3：
     * <p>
     * 输入：[1,3,2]
     * 输出：false
     * 示例 4：
     * <p>
     * 输入：[1,2,4,5]
     * 输出：true
     * 示例 5：
     * <p>
     * 输入：[1,1,1]
     * 输出：true
     * <p>
     * 提示：
     * <p>
     * 1 <= A.length <= 50000
     * -100000 <= A[i] <= 100000
     * <p>
     * 思路：
     * 本题就是不太好判断是增还是减还是都相等，所以，可以比较第一个元素和最后一个元素，然后就可以分为三种情况了
     * 如果满足单调函数，要么是递增，要么是递减,所以可以把数组的第一个数和最后一个数进行比较
     */

    // 检查一个数列是否是单调的。
    private boolean checkArrayIsMonotonous(int[] A) {
        int length = A.length;
        if (A[0] == A[length - 1]) {
            // 除非是全部相等的数列，才能是单调的
            for (int i = 0; i < length - 1; i++) {
                if (A[0] != A[i]) {
                    return false;
                }
            }
        } else if (A[0] > A[length - 1]) {
            // 可能为递减序列
            for (int i = 0; i < length - 1; i++) {
                if (A[0] < A[i]) {
                    return false;
                }
            }
        } else {
            // 可能为递增序列
            for (int i = 0; i < length - 1; i++) {
                if (A[0] > A[i]) {
                    return false;
                }
            }
        }

        return true;
    }

}
