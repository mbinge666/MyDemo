package com.aidi.demo.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.aidi.demo.base.BaseFragment;

/**
 * 左侧菜单的Fragment
 */

public class LeftMenuFragment extends BaseFragment {
    @Override
    protected View initView() {
        TextView tv = new TextView(mActivity);
        tv.setText("我是左侧菜单的布局");
        tv.setTextSize(20);
        tv.setTextColor(Color.BLACK);
        return tv;
    }
}
