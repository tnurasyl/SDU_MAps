package kz.sdu.map.sdu_maps;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import kz.sdu.map.sdu_maps.listeners.OnMarkersShowListener;

public class PagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;
    private OnMarkersShowListener listener;

    public PagerAdapter(FragmentManager fm, int numOfTabs, OnMarkersShowListener listener) {
        super(fm);
        this.numOfTabs = numOfTabs;
        this.listener = listener;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FavoritesFragment(listener);
            case 1:
                return new FacultiesFragment(listener);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Favorites";
        } else return "Facultets";
    }
}
