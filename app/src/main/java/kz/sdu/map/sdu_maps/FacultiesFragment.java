package kz.sdu.map.sdu_maps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kz.sdu.map.sdu_maps.adapters.FacultiesAdapter;
import kz.sdu.map.sdu_maps.adapters.RoomsAdapter;
import kz.sdu.map.sdu_maps.constants.Constants;
import kz.sdu.map.sdu_maps.listeners.OnBetweenFragmentListener;
import kz.sdu.map.sdu_maps.listeners.OnMarkersShowListener;
import kz.sdu.map.sdu_maps.listeners.OnRoomSelectedListener;
import kz.sdu.map.sdu_maps.models.MapMarkerModel;
import kz.sdu.map.sdu_maps.models.RoomModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class FacultiesFragment extends Fragment implements FacultiesAdapter.OnFacultyClickListener,
        OnRoomSelectedListener {

    private RecyclerView rvFaculties;
    private RecyclerView rvRooms;
    private LinearLayout toolbar;
    private TextView selectedFacultyName;
    private OnMarkersShowListener listener;
    private OnBetweenFragmentListener fragmentListener;
    private int selectedFacultyId;
    private List<RoomModel> shownRooms;

    FacultiesFragment(OnMarkersShowListener listener, OnBetweenFragmentListener fragmentListener) {
        this.listener = listener;
        this.fragmentListener = fragmentListener;
    }

    private RoomsAdapter roomsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_faculties, container, false);
        rvFaculties = view.findViewById(R.id.rvFaculties);
        rvRooms = view.findViewById(R.id.rvRooms);
        toolbar = view.findViewById(R.id.llToolbar);
        selectedFacultyName = view.findViewById(R.id.facultyName);

        FacultiesAdapter adapter = new FacultiesAdapter(Constants.getFaculties(), this);
        rvFaculties.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rvFaculties.setAdapter(adapter);

        roomsAdapter = new RoomsAdapter(Constants.getRooms(), this);
        rvRooms.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rvRooms.setAdapter(roomsAdapter);

        view.findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar.setVisibility(View.GONE);
                rvRooms.setVisibility(View.GONE);
                rvFaculties.setVisibility(View.VISIBLE);
                listener.showMarkers(new ArrayList<MapMarkerModel>());
            }
        });

        return view;
    }

    private List<RoomModel> getRoomsByFaculty(int facultyId) {
        ArrayList<RoomModel> list = new ArrayList<>();
        for (RoomModel room : Constants.getRooms()) {
            if (room.getFacultyId() == facultyId) {
                list.add(room);
            }
        }
        return list;
    }

    @Override
    public void onClick(int facultyId, String facultyName) {
        fragmentListener.onAFragmentListener();
        rvFaculties.setVisibility(View.GONE);
        toolbar.setVisibility(View.VISIBLE);
        selectedFacultyName.setText(facultyName);
        rvRooms.setVisibility(View.VISIBLE);
        shownRooms = getRoomsByFaculty(facultyId);
        roomsAdapter.updateData(getRoomsByFaculty(facultyId));
        selectedFacultyId = facultyId;
        listener.showMarkers(roomsToMapModel(getFacultyRooms(facultyId)));
    }

    private List<MapMarkerModel> roomsToMapModel(List<RoomModel> items) {
        ArrayList<MapMarkerModel> mapMarkerModels = new ArrayList<>();
        for (RoomModel item : items) {
            mapMarkerModels.add(new MapMarkerModel(item.getRoomName(), item.getLatitude(), item.getLongitude(), item.getLogoId()));
        }
        return mapMarkerModels;
    }

    private List<RoomModel> getFacultyRooms(int facultyId) {
        ArrayList<RoomModel> rooms = new ArrayList<>();
        for (RoomModel room : Constants.getRooms()) {
            if (room.getFacultyId() == facultyId) {
                rooms.add(room);
            }
        }
        return rooms;
    }

    @Override
    public void onRoomSelected(RoomModel room) {
        if (!room.isSelected()) {
            listener.showMarkers(roomsToMapModel(getFacultyRooms(selectedFacultyId)));
        } else {
            ArrayList<RoomModel> rooms = new ArrayList<>();
            rooms.add(room);
            listener.showMarkers(roomsToMapModel(rooms));
        }
    }

    public void showFaculties() {
        rvRooms.setVisibility(View.GONE);
        rvFaculties.setVisibility(View.VISIBLE);
        toolbar.setVisibility(View.GONE);
    }
}
