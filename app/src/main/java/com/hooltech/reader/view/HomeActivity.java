package com.hooltech.reader.view;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.hooltech.reader.R;
import com.hooltech.reader.custom.TitleLayout;
import com.hooltech.reader.view.adapter.worldBean;
import com.hooltech.reader.view.adapter.worldAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private MeFragment meFragment;
    private LoveFragment loveFragment;
    private WorldFragment worldFragment;
    private SearchFragment searchFragment;
    private ArrayList<Fragment> fragments;



    //    private TitleLayout mTitleLayout;

    final String TAG = "HoolReader";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //沉浸式状态栏实现
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }


//        mTitleLayout = (TitleLayout) findViewById(R.id.titleLayout);
//        mTitleLayout.setTitleText("design");
//        mTitleLayout.setButtontext("编辑","right");
//
//        mTitleLayout.setOnLeftAndRightClickListener(new TitleLayout.OnLeftAndRightClickListener() {
//            @Override
//            public void OnLeftButtonClick() {
//
//            }
//
//            @Override
//            public void OnRightButtonClick() {
//
//            }
//        });


        fragments = getFragments();
        setDefaultFragment();

        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        /** 导航基础设置 包括按钮选中效果 导航栏背景色等 */
        bottomNavigationBar
                .setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(int position) {
                        Log.d(TAG,"onTabSelected");
                        if (fragments != null) {
                            if (position < fragments.size()) {
                                FragmentManager fm = getSupportFragmentManager();
                                FragmentTransaction ft = fm.beginTransaction();
                                Fragment fragment = fragments.get(position);
                                if (fragment.isAdded()) {
                                    Log.d("Hoolreader","fragment.isAdded");
                                } else {
                                    Log.d("Hoolreader","fragment.isnotAdded");
                                    ft.add(R.id.fragment_show, fragment);
                                }
                                ft.show(fragment);
                                ft.commitAllowingStateLoss();
                            }
                        }
                    }

                    @Override
                    public void onTabUnselected(int position) {
                        Log.d(TAG,"onTabUnselected");
                        if (fragments != null) {
                            if (position < fragments.size()) {
                                Log.d("Hoolreader","remove"+String.valueOf(position));
                                FragmentManager fm = getSupportFragmentManager();
                                FragmentTransaction ft = fm.beginTransaction();
                                Fragment fragment = fragments.get(position);
                                ft.hide(fragment);
                                ft.commitAllowingStateLoss();
                            }
                        }
                    }

                    @Override
                    public void onTabReselected(int position) {
                        Log.d(TAG,"onTabReselected");
                    }
                })
                .setMode(BottomNavigationBar.MODE_FIXED)
                /**
                 *  setMode() 内的参数有三种模式类型：
                 *  MODE_DEFAULT 自动模式：导航栏Item的个数<=3 用 MODE_FIXED 模式，否则用 MODE_SHIFTING 模式
                 *  MODE_FIXED 固定模式：未选中的Item显示文字，无切换动画效果。
                 *  MODE_SHIFTING 切换模式：未选中的Item不显示文字，选中的显示文字，有切换动画效果。
                 */

                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                /**
                 *  setBackgroundStyle() 内的参数有三种样式
                 *  BACKGROUND_STYLE_DEFAULT: 默认样式 如果设置的Mode为MODE_FIXED，将使用BACKGROUND_STYLE_STATIC
                 *                                    如果Mode为MODE_SHIFTING将使用BACKGROUND_STYLE_RIPPLE。
                 *  BACKGROUND_STYLE_STATIC: 静态样式 点击无波纹效果
                 *  BACKGROUND_STYLE_RIPPLE: 波纹样式 点击有波纹效果
                 */

                .setActiveColor("#FF107FFD") //选中颜色
                .setInActiveColor("#e9e6e6") //未选中颜色
                .setBarBackgroundColor("#1ccbae");//导航栏背景色

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_me, "I"))
                .addItem(new BottomNavigationItem(R.drawable.ic_love, "love"))
                .addItem(new BottomNavigationItem(R.drawable.ic_world, "World"))
                .setFirstSelectedPosition(0)
                .initialise();

    }

    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        //homeFragment = HomeFragment.newInstance("home");
        transaction.add(R.id.fragment_show,meFragment);
        transaction.show(meFragment);
        transaction.commit();
    }
    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        meFragment = MeFragment.newInstance("home",null);
        loveFragment = loveFragment.newInstance("love",null);
        worldFragment = WorldFragment.newInstance("world",null);
        searchFragment = SearchFragment.newInstance("search",null);
        fragments.add(meFragment);
        fragments.add(loveFragment);
        fragments.add(worldFragment);
        fragments.add(searchFragment);
        return fragments;
    }

    public void setChantEssayFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment = fragments.get(2);
        ft.hide(fragment);
        fragment = fragments.get(3);
        ft.add(R.id.fragment_show, fragment);
        ft.show(fragment);
        ft.commitAllowingStateLoss();
    }
}
