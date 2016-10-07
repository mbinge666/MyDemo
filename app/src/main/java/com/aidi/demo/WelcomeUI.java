package com.aidi.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.aidi.demo.utils.CacheUtils;

/**
 * @author xiaoBing
 * 欢迎界面
 */
public class WelcomeUI extends AppCompatActivity {

    // 是否打开过主页面的键key
    public static final String IS_OPEN_MAIN_PAGER_KEY = "is_open_main_pager_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        init();
    }

    /**
     * 初始化控件和动画
     */
    private void init() {
        RelativeLayout rlRoot = (RelativeLayout) findViewById(R.id.rl_welcome_root);
        AnimationSet animationSet = new AnimationSet(false);

        // 旋转动画: 以中心点旋转, 0~360
        RotateAnimation rotateAnim = new RotateAnimation(
                0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnim.setDuration(1000);
        rotateAnim.setFillAfter(true); // 设置动画执行完毕后, 控件停留在结束的状态下.

        // 缩放动画: 0~1 从没有到完全显示, 基于中心点进行缩放
        ScaleAnimation scaleAnim = new ScaleAnimation(
                0, 1,
                0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnim.setDuration(1000);
        scaleAnim.setFillAfter(true);

        // 渐变动画: 从不显示到显示
        AlphaAnimation alphaAnim = new AlphaAnimation(0, 1);
        alphaAnim.setDuration(2000);
        alphaAnim.setFillAfter(true);

        // 把动画添加到集合中
        animationSet.addAnimation(rotateAnim);
        animationSet.addAnimation(scaleAnim);
        animationSet.addAnimation(alphaAnim);

        animationSet.setAnimationListener(new MyAnimationListener());

        //执行动画
        rlRoot.startAnimation(animationSet);
    }

    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            // 判断当前是跳转到主页面还是引导页面
            boolean isOpenMainPager = CacheUtils.getBoolean(WelcomeUI.this, IS_OPEN_MAIN_PAGER_KEY, false);
            if (isOpenMainPager) {
                //不是第一次打开，跳转到主界面
                startActivity(new Intent(WelcomeUI.this,MainUI.class));
            } else {
                //第一次打开，跳转到引导页面
                startActivity(new Intent(WelcomeUI.this,GuideUI.class));
            }
            finish();
        }

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
