package com.aidi.demo.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.aidi.demo.R;
import com.aidi.demo.base.BaseFragment;
import com.aidi.demo.base.BasePager;
import com.aidi.demo.base.impl.GovaffairsPager;
import com.aidi.demo.base.impl.HomePager;
import com.aidi.demo.base.impl.NewsCenterPager;
import com.aidi.demo.base.impl.SettingsPager;
import com.aidi.demo.base.impl.SmartServicePager;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * 正文Fragment
 */

public class ContentFragment extends BaseFragment {
    @ViewInject(R.id.vp_content_fragment)
    private ViewPager mViewPager;

    @ViewInject(R.id.rg_content_fragment)
    private RadioGroup mRadioGruop;

    private List<BasePager> pagerList;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.content_fragment, null);
        // 把当前view对象注入到xUtils框架中
        ViewUtils.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        // 设置默认选中的页面为: 首页
        mRadioGruop.check(R.id.rb_content_fragment_home);

        // 初始化ViewPager的数据
        pagerList = new ArrayList<BasePager>();
        pagerList.add(new HomePager(mActivity));
        pagerList.add(new NewsCenterPager(mActivity));
        pagerList.add(new SmartServicePager(mActivity));
        pagerList.add(new GovaffairsPager(mActivity));
        pagerList.add(new SettingsPager(mActivity));

        // 绑定数据
        ContentAdapter mAdapter = new ContentAdapter();
        mViewPager.setAdapter(mAdapter);
    }

    class ContentAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return pagerList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // 把对应pagerList集合中的position位置的页面的布局添加到ViewPager中, 并返回
            BasePager pager = pagerList.get(position);
            container.addView(pager.rootView); // 把布局添加到ViewPager中
            pager.initData(); // 初始化数据
            return pager.rootView;
        }
    }

}
