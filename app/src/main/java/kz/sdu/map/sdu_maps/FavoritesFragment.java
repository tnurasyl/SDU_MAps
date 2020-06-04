package kz.sdu.map.sdu_maps;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import kz.sdu.map.sdu_maps.R;
import kz.sdu.map.sdu_maps.models.CategoryModel;
import kz.sdu.map.sdu_maps.models.PlaceModel;

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

        categories = getCategories();
        configureRVcategories();

        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    private void drawMarkers(int categoryId) {
        for (Marker marker : shownMarkers) {
            marker.remove();
        }

        if (categoryId == -1) {
            for (int i = 0; i < places.size(); i++) {
                shownMarkers.add(mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(places.get(i).getLatitude(), places.get(i).getLongitude()))
                        .title(places.get(i).getName())
                        .icon(bitmapDescriptorFromVector(this, categories.get(places.get(i).getCategoryId()).getMarkerIcon()))
                        .zIndex(1.0f)));
            }
        } else {
            for (int i = 0; i < places.size(); i++) {
                if (places.get(i).getCategoryId() == categoryId) {
                    shownMarkers.add(mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(places.get(i).getLatitude(), places.get(i).getLongitude()))
                            .title(places.get(i).getName())
//                            .icon(bitmapDescriptorFromVector(this, categories.get(places.get(i).getCategoryId()).getMarkerIcon()))
                            .zIndex(1.0f)));
                }
            }
        }
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

    private ArrayList<PlaceModel> getPlaces() {
        ArrayList<PlaceModel> places = new ArrayList<>();
        places.add(new PlaceModel(0, "Red Hall", 1, 43.208761, 76.670166));
        places.add(new PlaceModel(1, "Library", 2, 43.208819, 76.669663));
        places.add(new PlaceModel(2, "Dining Room", 0, 43.207437, 76.669583));
        places.add(new PlaceModel(3, "B1 Lecture", 3, 43.208130, 76.669516));
        places.add(new PlaceModel(4, "Engineering", 3, 43.207436, 76.670144));
        places.add(new PlaceModel(5, "Economic", 3, 43.207011, 76.670320));
        places.add(new PlaceModel(6, "C1 Lecture", 3, 43.207929, 76.669483));
        places.add(new PlaceModel(7, "White Canteen", 0, 43.208598, 76.669467));
        return places;
    }

    private void configureRVcategories() {
        rvCategories = getView().findViewById(R.id.rvCategories);
        rvCategories.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.HORIZONTAL));
//        rvCategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new CategoriesAdapter(categories,this, this);
        rvCategories.setAdapter(adapter);
    }

    @Override
    public void onCategoryClicked(int categoryId) {
        if (categories.get(categoryId).isSelected()) {
            categories.get(categoryId).setSelected(false);
            drawMarkers(-1);
        } else {
            drawMarkers(categoryId);
            for (int i = 0; i < categories.size(); i++) {
                if (categoryId == i) categories.get(i).setSelected(true);
                else categories.get(i).setSelected(false);
            }
        }
        adapter.notifyDataSetChanged();
    }
}
