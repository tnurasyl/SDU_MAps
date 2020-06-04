package kz.sdu.map.sdu_maps.constants;

import java.util.ArrayList;
import java.util.List;

import kz.sdu.map.sdu_maps.R;
import kz.sdu.map.sdu_maps.models.CategoryModel;
import kz.sdu.map.sdu_maps.models.FacultetsModel;
import kz.sdu.map.sdu_maps.models.PlaceModel;
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
        list.add(new RoomModel(1, "D102", 1, R.drawable.ic_room, 43.208309, 76.669858, false));
        list.add(new RoomModel(2, "E101", 1, R.drawable.ic_room, 43.207902, 76.669940, false));
        list.add(new RoomModel(3, "F107", 2, R.drawable.ic_room, 43.207351, 76.670096, false));
        list.add(new RoomModel(4, "G104", 2, R.drawable.ic_room, 43.207128, 76.670305, false));
        return list;
    }

    public static List<PlaceModel> getPlaces() {
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

    public static ArrayList<CategoryModel> getCategories() {
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
