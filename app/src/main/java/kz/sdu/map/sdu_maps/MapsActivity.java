package kz.sdu.map.sdu_maps;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import kz.sdu.map.sdu_maps.models.CategoryModel;
import kz.sdu.map.sdu_maps.models.PlaceModel;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener,
        CategoriesAdapter.OnClickListener {

    private static final String TAG = MapsActivity.class.getSimpleName();

    private SearchView searchView;
    private GoogleMap mMap;
    private RecyclerView rvCategories;
    private ImageView openCloseCategory;
    private LinearLayout flCategory;
    private GoogleApiClient client;
    private LocationRequest locationRequest;
    private Location lastlocation;
    private Marker currentLocationmMarker;
    public static final int REQUEST_LOCATION_CODE = 99;

    private Marker RedHall, Cattery, B1;
    private List<Marker> markers = new ArrayList<>();
    private List<Marker> shownMarkers = new ArrayList<>();

    private ArrayList<CategoryModel> categories;
    private ArrayList<PlaceModel> places;
    private CategoriesAdapter adapter;

    private boolean isOpenCategory = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        categories = getCategories();
        places = getPlaces();
        flCategory = findViewById(R.id.flCat);
        openCloseCategory = findViewById(R.id.ivOpenCloseCat);
        configureRVcategories();

        openCloseCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpenCategory) {
                    flCategory.setVisibility(View.GONE);
                    isOpenCategory = false;
                } else {
                    flCategory.setVisibility(View.VISIBLE);
                    isOpenCategory = true;
                }
            }
        });

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //TODO search
        searchView = findViewById(R.id.sv_location);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = searchView.getQuery().toString();
                List<Address> addressList = null;
                LatLng latLng = null;

                if (location != null || !location.equals("")) {
                    Geocoder geocoder = new Geocoder(MapsActivity.this);
                    try {
                        for (int i = 0; i < places.size(); i++) {
                            String mm = places.get(i).getName().toLowerCase();
                            if (mm.matches("(.*)" + query.toLowerCase() + "(.*)")) {
                                double lat = places.get(i).getLatitude();
                                double lon = places.get(i).getLongitude();
                                latLng = new LatLng(lat, lon);
                            }
                        }
//                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                    Address address = addressList.get(0);
//                    latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    if (latLng != null) {
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 60));
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        if (client == null) {
                            bulidGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
                }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        // SDU location
        LatLng sdu = new LatLng(43.207736, 76.669709);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sdu));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sdu, 9));

        //get location
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            bulidGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

        //TODO setting photo
        GroundOverlayOptions newarkMap = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.map))
                .position(sdu, 100f, 300f);
        mMap.addGroundOverlay(newarkMap);

        drawMarkers(-1);

        // google map settings
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);

        setMapStyle();
    }

    private void setMapStyle() {
        MapStyleOptions style = new MapStyleOptions("[" +
                "  {" +
                "    \"elementType\":\"labels\"," +
                "    \"stylers\":[" +
                "      {" +
                "        \"visibility\":\"off\"" +
                "      }" +
                "    ]" +
                "  }," +
                "  {" +
                "    \"featureType\":\"administrative\"," +
                "    \"elementType\":\"geometry\"," +
                "    \"stylers\":[" +
                "      {" +
                "        \"visibility\":\"off\"" +
                "      }" +
                "    ]" +
                "  }," +
                "  {" +
                "    \"featureType\":\"administrative.land_parcel\"," +
                "    \"elementType\":\"geometry\"," +
                "    \"stylers\":[" +
                "      {" +
                "        \"visibility\":\"off\"" +
                "      }" +
                "    ]" +
                "  }," +
                "  {" +
                "    \"featureType\":\"administrative.neighborhood\"," +
                "    \"stylers\":[" +
                "      {" +
                "        \"visibility\":\"off\"" +
                "      }" +
                "    ]" +
                "  }," +
                "  {" +
                "    \"featureType\":\"poi\"," +
                "    \"stylers\":[" +
                "      {" +
                "        \"visibility\":\"off\"" +
                "      }" +
                "    ]" +
                "  }," +
                "  {" +
                "    \"featureType\":\"road\"," +
                "    \"stylers\":[" +
                "      {" +
                "        \"visibility\":\"off\"" +
                "      }" +
                "    ]" +
                "  }," +
                "  {" +
                "    \"featureType\":\"road\"," +
                "    \"elementType\":\"labels.icon\"," +
                "    \"stylers\":[" +
                "      {" +
                "        \"visibility\":\"off\"" +
                "      }" +
                "    ]" +
                "  }," +
                "  {" +
                "    \"featureType\":\"transit\"," +
                "    \"stylers\":[" +
                "      {" +
                "        \"visibility\":\"off\"" +
                "      }" +
                "    ]" +
                "  }," +
                "  {" +
                "    \"featureType\":\"water\"," +
                "    \"stylers\":[" +
                "      {" +
                "        \"visibility\":\"off\"" +
                "      }" +
                "    ]" +
                "  }" +
                "]");

        mMap.setMapStyle(style);
    }

    protected synchronized void bulidGoogleApiClient() {
        client = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
        client.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(100);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, this);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        lastlocation = location;
        if (currentLocationmMarker != null) {
            currentLocationmMarker.remove();
        }

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        currentLocationmMarker = mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(10));

        if (client != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(client, this);
        }
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);
            }
            return false;

        } else
            return true;
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

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
        categories.add(new CategoryModel(0, "Eating", R.drawable.ic_icon_department_24dp, false));
        categories.add(new CategoryModel(1, "Halls", R.drawable.ic_icon_bochka_24dp, false));
        categories.add(new CategoryModel(2, "Library", R.drawable.ic_icon_canteen_24dp, false));
        categories.add(new CategoryModel(3, "Student ", R.drawable.ic_icon_others_24dp, false));
        categories.add(new CategoryModel(4, "Red hall", R.drawable.ic_icon_department_24dp, false));
        categories.add(new CategoryModel(5, "Wardrobe", R.drawable.ic_icon_bochka_24dp, false));
        categories.add(new CategoryModel(6, "Medical center", R.drawable.ic_icon_canteen_24dp, false));
        categories.add(new CategoryModel(7, "WC", R.drawable.ic_icon_others_24dp, false));
        categories.add(new CategoryModel(8, "Canteen", R.drawable.ic_icon_department_24dp, false));
        categories.add(new CategoryModel(9, "WC", R.drawable.ic_icon_others_24dp, false));
        return categories;
    }

    private ArrayList<PlaceModel> getPlaces() {
        ArrayList<PlaceModel> places = new ArrayList<>();
        places.add(new PlaceModel(0, "Red Hall", 3, 43.208761, 76.670166));
        places.add(new PlaceModel(1, "Library", 3, 43.208819, 76.669663));
        places.add(new PlaceModel(2, "Dining Room", 2, 43.207437, 76.669583));
        places.add(new PlaceModel(3, "B1 Lecture", 1, 43.208130, 76.669516));
        places.add(new PlaceModel(4, "Engineering", 0, 43.207436, 76.670144));
        places.add(new PlaceModel(5, "Economic", 0, 43.207011, 76.670320));
        places.add(new PlaceModel(6, "C1 Lecture", 1, 43.207929, 76.669483));
        places.add(new PlaceModel(7, "White Canteen", 2, 43.208598, 76.669467));
        return places;
    }

    private void configureRVcategories() {
        rvCategories = findViewById(R.id.rvCategories);
        rvCategories.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.HORIZONTAL));
//        rvCategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new CategoriesAdapter(categories, MapsActivity.this, this);
        rvCategories.setAdapter(adapter);
    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

}