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
import java.util.List;

import kz.sdu.map.sdu_maps.adapters.CategoriesAdapter;
import kz.sdu.map.sdu_maps.constants.Constants;
import kz.sdu.map.sdu_maps.listeners.OnMarkersShowListener;
import kz.sdu.map.sdu_maps.models.CategoryModel;
import kz.sdu.map.sdu_maps.models.MapMarkerModel;
import kz.sdu.map.sdu_maps.models.PlaceModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment implements CategoriesAdapter.OnClickListener {

    private OnMarkersShowListener markersShowListener;

    FavoritesFragment(OnMarkersShowListener listener) {
        markersShowListener = listener;
    }

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
        categories = Constants.getCategories();
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
            markersShowListener.showMarkers(placesToMapModel(Constants.getPlaces()));

        } else {
            ArrayList<PlaceModel> categoryPlaces = new ArrayList<>();
            for (PlaceModel place : Constants.getPlaces()) {
                if (place.getCategoryId() == categoryId) {
                    categoryPlaces.add(place);
                }
            }
            markersShowListener.showMarkers(placesToMapModel(categoryPlaces));
            for (int i = 0; i < categories.size(); i++) {
                if (categoryId == i) categories.get(i).setSelected(true);
                else categories.get(i).setSelected(false);
            }
        }
        adapter.notifyDataSetChanged();
    }

    private List<MapMarkerModel> placesToMapModel(List<PlaceModel> items) {
        ArrayList<MapMarkerModel> mapMarkerModels = new ArrayList<>();
        for (PlaceModel item : items) {
            mapMarkerModels.add(new MapMarkerModel(item.getName(), item.getLatitude(), item.getLongitude(), Constants.getCategories().get(item.getCategoryId()).getMarkerIcon()));
        }
        return mapMarkerModels;
    }

    public void unselectAllMarkers() {
        for (CategoryModel categoryModel : categories) {
            categoryModel.setSelected(false);
        }
        markersShowListener.showMarkers(new ArrayList<MapMarkerModel>());
        adapter.notifyDataSetChanged();
    }
}
