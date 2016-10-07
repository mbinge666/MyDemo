package com.aidi.demo.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.aidi.demo.base.BasePager;

/**
 * Created by Administrator on 2016/10/6.
 */

public class SettingsPager extends BasePager {

    public SettingsPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        tvTitle.setText("设置");
        ibMenu.setVisibility(View.GONE);

        TextView tv = new TextView(mActivity);
        tv.setText("设置");
        tv.setTextColor(Color.RED);
        tv.setTextSize(25);
        tv.setGravity(Gravity.CENTER);
        flContent.addView(tv);
    }
}
