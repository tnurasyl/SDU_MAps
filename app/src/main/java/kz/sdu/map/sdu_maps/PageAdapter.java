package kz.sdu.map.sdu_maps;


import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;
    private Context context;
    private CategoriesAdapter.OnClickListener onclick;
    PageAdapter(CategoriesAdapter.OnClickListener onclick, Context context, FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
        this.context = context;
        this.onclick = onclick;

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Category(context, onclick);
            case 1:
                return new Category(context, onclick);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}