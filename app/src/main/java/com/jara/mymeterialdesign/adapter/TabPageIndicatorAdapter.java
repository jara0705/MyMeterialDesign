package com.jara.mymeterialdesign.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.jara.mymeterialdesign.enumset.SubFragmentEnum;

import java.util.List;

/**
 * Created by jara on 2017-6-30.
 */

public class TabPageIndicatorAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private SubFragmentEnum[] subFragmentEnum;

    public TabPageIndicatorAdapter(FragmentManager fm, List<Fragment> fragments, SubFragmentEnum[] fragmentEnum) {
        super(fm);
        this.fragments = fragments;
        this.subFragmentEnum = fragmentEnum;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return subFragmentEnum.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return subFragmentEnum[position % subFragmentEnum.length].getName();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }
}
