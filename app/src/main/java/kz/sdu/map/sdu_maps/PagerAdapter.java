package kz.sdu.map.sdu_maps;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import kz.sdu.map.sdu_maps.listeners.OnBetweenFragmentListener;
import kz.sdu.map.sdu_maps.listeners.OnMarkersShowListener;

public class PagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;
    private OnMarkersShowListener listener;
    private OnBetweenFragmentListener fragmentListener;

    private FavoritesFragment favoritesFragment;
    private FacultiesFragment facultiesFragment;

    public PagerAdapter(FragmentManager fm, int numOfTabs, OnMarkersShowListener listener, OnBetweenFragmentListener fragmentListener) {
        super(fm);
        this.numOfTabs = numOfTabs;
        this.listener = listener;
        this.fragmentListener = fragmentListener;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                favoritesFragment = new FavoritesFragment(listener, fragmentListener);
                return favoritesFragment;
            case 1:
                facultiesFragment = new FacultiesFragment(listener, fragmentListener);
                return facultiesFragment;
            default:
                return null;
        }
    }

    public void unselectAllMarkers() {
        favoritesFragment.unselectAllMarkers();
    }

    public void goToFaculties() {
        facultiesFragment.showFaculties();
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
