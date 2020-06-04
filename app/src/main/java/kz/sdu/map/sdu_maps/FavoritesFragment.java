package kz.sdu.map.sdu_maps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

import kz.sdu.map.sdu_maps.adapters.CategoriesAdapter;
import kz.sdu.map.sdu_maps.models.CategoryModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment implements CategoriesAdapter.OnClickListener {

    private RecyclerView rvCategories;
    private ArrayList<CategoryModel> categories;
    private CategoriesAdapter adapter;

    public FavoritesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        rvCategories = view.findViewById(R.id.rvCategories);
        categories = getCategories();
        configureRVCategories();
        return view;
    }

    private void configureRVCategories() {
        rvCategories.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.HORIZONTAL));
        adapter = new CategoriesAdapter(categories, getContext(), this);
        rvCategories.setAdapter(adapter);
    }

    @Override
    public void onCategoryClicked(int categoryId) {
        if (categories.get(categoryId).isSelected()) {
            categories.get(categoryId).setSelected(false);
//            drawMarkers(-1);
        } else {
//            drawMarkers(categoryId);
            for (int i = 0; i < categories.size(); i++) {
                if (categoryId == i) categories.get(i).setSelected(true);
                else categories.get(i).setSelected(false);
            }
        }
        adapter.notifyDataSetChanged();
    }

    private ArrayList<CategoryModel> getCategories() {
        ArrayList<CategoryModel> categories = new ArrayList<>();
        categories.add(new CategoryModel(0, "Eating", R.drawable.ic_eat, false));
        categories.add(new CategoryModel(1, "Halls", R.drawable.ic_hall, false));
        categories.add(new CategoryModel(2, "Library", R.drawable.ic_library, false));
        categories.add(new CategoryModel(3, "Technopark ", R.drawable.ic_hall, false));
        categories.add(new CategoryModel(4, "Accounting", R.drawable.ic_hall, false));
        categories.add(new CategoryModel(5, "Wardrobe", R.drawable.ic_wardrobe, false));
        categories.add(new CategoryModel(6, "Medical center", R.drawable.ic_med, false));
        categories.add(new CategoryModel(7, "Student center", R.drawable.ic_s_center, false));
        categories.add(new CategoryModel(8, "WC", R.drawable.ic_wc, false));
        categories.add(new CategoryModel(9, "Others", R.drawable.ic_others, false));
        return categories;
    }
}
