package kz.sdu.map.sdu_maps.constants;

import java.util.ArrayList;
import java.util.List;

import kz.sdu.map.sdu_maps.R;
import kz.sdu.map.sdu_maps.models.FacultetsModel;
import kz.sdu.map.sdu_maps.models.RoomModel;

public class Constants {
    public static List<FacultetsModel> getFaculties() {
        ArrayList<FacultetsModel> list = new ArrayList<>();
        list.add(new FacultetsModel(1, "Факультет Инженерии и Естественных наук", R.drawable.ic_faculty));
        list.add(new FacultetsModel(2, "Факультет Педагогики и Гуманитарных наук", R.drawable.ic_faculty));
        list.add(new FacultetsModel(3, "Факультет Права и Социальных наук", R.drawable.ic_faculty));
        list.add(new FacultetsModel(4, "Школа Бизнеса", R.drawable.ic_faculty));
        list.add(new FacultetsModel(5, "Аудитории", R.drawable.ic_faculty));
        return list;
    }

    public static List<RoomModel> getRooms() {
        ArrayList<RoomModel> list = new ArrayList<>();
        list.add(new RoomModel(1, "D102", 1, R.drawable.ic_room, 43.208309, 76.669858));
        list.add(new RoomModel(2, "E101", 1, R.drawable.ic_room, 43.207902, 76.669940));
        list.add(new RoomModel(3, "F107", 2, R.drawable.ic_room, 43.207351, 76.670096));
        list.add(new RoomModel(4, "G104", 2, R.drawable.ic_room, 43.207128, 76.670305));
        return list;
    }
}
