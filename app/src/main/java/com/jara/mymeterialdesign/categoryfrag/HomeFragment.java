package com.jara.mymeterialdesign.categoryfrag;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jara.mymeterialdesign.R;
import com.jara.mymeterialdesign.adapter.TabPageIndicatorAdapter;
import com.jara.mymeterialdesign.categoryfrag.subfragment.CommonFragment;
import com.jara.mymeterialdesign.categoryfrag.subfragment.NewFragment;
import com.jara.mymeterialdesign.enumset.SubFragmentEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jara on 2017-6-30.
 */

public class HomeFragment extends Fragment {

    private View view;
    private ViewPager article_viewpager;
    private TabLayout tabLayout;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initData();
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        article_viewpager = (ViewPager) view.findViewById(R.id.article_viewpager);
        setAdapter();
        setListener();
        return view;
    }

    private void initData() {
        NewFragment newFragment = new NewFragment();
        fragments.add(newFragment);
        for (SubFragmentEnum subFragmentEnum : SubFragmentEnum.values()) {
            CommonFragment fragment = new CommonFragment();
            Bundle bundle = new Bundle();
            bundle.putString("type_id", subFragmentEnum.getCode());
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
    }

    private void setAdapter() {
        article_viewpager.setAdapter(new TabPageIndicatorAdapter(getFragmentManager()
                , fragments, SubFragmentEnum.values()));
        tabLayout.setupWithViewPager(article_viewpager);
        tabLayout.setTabTextColors(getResources().getColor(R.color.dark_white), Color.WHITE);
    }

    private void setListener() {
//        indicator.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
//            @Override
//            public void onPageSelected(int position) {
//                tv_title.setText(TITLE[position]);
//            }
//        });
    }
}
