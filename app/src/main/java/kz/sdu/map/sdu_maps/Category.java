package kz.sdu.map.sdu_maps;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kz.sdu.map.sdu_maps.models.CategoryModel;

public class Category extends Fragment {

    Context context;
    CategoriesAdapter.OnClickListener onClickListener;
    Category(Context context, CategoriesAdapter.OnClickListener onClickListener) {
        this.context = context;
        this.onClickListener = onClickListener;
    }

    private RecyclerView rvCategories;
    private ArrayList<CategoryModel> categories;
    private CategoriesAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        configureRVcategories();
        return inflater.inflate(R.layout.first, container, false);
    }

        private void configureRVcategories() {
        rvCategories = getView().findViewById(R.id.rvCategories);
        rvCategories.setLayoutManager(new GridLayoutManager(this.getContext(), categories.size()));
//        rvCategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new CategoriesAdapter(categories, context, onClickListener);
        rvCategories.setAdapter(adapter);
    }
}
