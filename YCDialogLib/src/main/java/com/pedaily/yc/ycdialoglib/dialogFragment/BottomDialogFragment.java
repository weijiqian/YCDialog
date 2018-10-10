package com.pedaily.yc.ycdialoglib.dialogFragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentManager;
import android.view.View;

/**
 * <pre>
 *     @author yangchong
 *     blog  : https://github.com/yangchong211
 *     time  : 2017/8/9
 *     desc  : 自定义布局弹窗DialogFragment
 *     revise: 1月10日
 *                  有bug，如果想通过点击弹窗控件销毁dialog，该如何处理。
 *                  使用场景：点击对话框中控件，不会销毁dialog，按返回键处理销毁逻辑。
 *                  其实质是DialogFragment，
 * </pre>
 */
public class BottomDialogFragment extends BaseDialogFragment {

    private static final String KEY_LAYOUT_RES = "bottom_layout_res";
    private static final String KEY_HEIGHT = "bottom_height";
    private static final String KEY_DIM = "bottom_dim";
    private static final String KEY_CANCEL_OUTSIDE = "bottom_cancel_outside";

    private FragmentManager mFragmentManager;
    private boolean mIsCancelOutside = false;
    private String mTag = super.getFragmentTag();
    private float mDimAmount = super.getDimAmount();
    private int mHeight = super.getHeight();

    @LayoutRes
    private int mLayoutRes;
    private ViewListener mViewListener;

    public static BottomDialogFragment create(FragmentManager manager) {
        BottomDialogFragment dialog = new BottomDialogFragment();
        dialog.setFragmentManager(manager);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setLocal(Local.BOTTOM);
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mLayoutRes = savedInstanceState.getInt(KEY_LAYOUT_RES);
            mHeight = savedInstanceState.getInt(KEY_HEIGHT);
            mDimAmount = savedInstanceState.getFloat(KEY_DIM);
            mIsCancelOutside = savedInstanceState.getBoolean(KEY_CANCEL_OUTSIDE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(KEY_LAYOUT_RES, mLayoutRes);
        outState.putInt(KEY_HEIGHT, mHeight);
        outState.putFloat(KEY_DIM, mDimAmount);
        outState.putBoolean(KEY_CANCEL_OUTSIDE, mIsCancelOutside);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void bindView(View v) {
        if (mViewListener != null) {
            mViewListener.bindView(v);
        }
    }

    @Override
    protected boolean isCancel() {
        return mIsCancelOutside;
    }

    @Override
    public int getLayoutRes() {
        return mLayoutRes;
    }

    public BottomDialogFragment setFragmentManager(FragmentManager manager) {
        mFragmentManager = manager;
        return this;
    }

    public BottomDialogFragment setViewListener(ViewListener listener) {
        mViewListener = listener;
        return this;
    }

    public BottomDialogFragment setLayoutRes(@LayoutRes int layoutRes) {
        mLayoutRes = layoutRes;
        return this;
    }

    public BottomDialogFragment setCancelOutside(boolean cancel) {
        mIsCancelOutside = cancel;
        return this;
    }

    public BottomDialogFragment setTag(String tag) {
        mTag = tag;
        return this;
    }

    public BottomDialogFragment setDimAmount(float dim) {
        mDimAmount = dim;
        return this;
    }

    public BottomDialogFragment setHeight(int heightPx) {
        mHeight = heightPx;
        return this;
    }

    @Override
    public float getDimAmount() {
        return mDimAmount;
    }

    @Override
    public int getHeight() {
        return mHeight;
    }

    @Override
    public String getFragmentTag() {
        return mTag;
    }


    public BaseDialogFragment show() {
        show(mFragmentManager);
        return this;
    }

    public void dismissDialogFragment(){
        dismissDialog();
    }

    public interface ViewListener {
        /**
         * 绑定
         * @param v     view
         */
        void bindView(View v);
    }

}
