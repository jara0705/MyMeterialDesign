package com.jara.mymeterialdesign;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jara.mymeterialdesign.adapter.MainFragmentPageAdapter;
import com.jara.mymeterialdesign.categoryfrag.FindFragment;
import com.jara.mymeterialdesign.categoryfrag.HomeFragment;
import com.jara.mymeterialdesign.categoryfrag.HotnewsFragment;
import com.jara.mymeterialdesign.categoryfrag.PersonalFragment;
import com.jara.mymeterialdesign.categoryfrag.subfragment.NewFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private List<Fragment> fragmentList;
    private int i = 0;
    private MainFragmentPageAdapter mainFragmentPageAdapter;
    @BindView(R.id.main_viewpager)
    ViewPager viewPager;
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();
        mainFragmentPageAdapter = new MainFragmentPageAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(mainFragmentPageAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
        });

        bottomBar.setOnTabSelectListener(tabId -> {
            switch (tabId) {
                case R.id.tab_home:
                    i = 0;
                    break;
                case R.id.tab_hot_news:
                    i = 1;
                    break;
                case R.id.tab_find:
                    i = 2;
                    break;
                case R.id.tab_me:
                    i = 3;
                    break;
                default:
                    break;
            }
            viewPager.setCurrentItem(i, false);
        });
    }

    private void init() {
        HomeFragment homeFragment = new HomeFragment();
        HotnewsFragment hotnewsFragment = new HotnewsFragment();
        FindFragment findFragment = new FindFragment();
        PersonalFragment personalFragment = new PersonalFragment();
        fragmentList = new ArrayList<>();
        fragmentList.add(homeFragment);
        fragmentList.add(hotnewsFragment);
        fragmentList.add(findFragment);
        fragmentList.add(personalFragment);
    }

}
