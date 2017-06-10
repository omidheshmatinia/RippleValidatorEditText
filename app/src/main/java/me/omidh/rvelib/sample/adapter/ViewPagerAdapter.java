package me.omidh.rvelib.sample.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import me.omidh.rvelib.sample.fragment.DifferentShapeFragment;
import me.omidh.rvelib.sample.fragment.ValidatorFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            default:
            case 0: return new ValidatorFragment();
            case 1: return new DifferentShapeFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
