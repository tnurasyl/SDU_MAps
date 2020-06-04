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
public class FavoritesFragment extends Fragment  {



    public FavoritesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }


}
