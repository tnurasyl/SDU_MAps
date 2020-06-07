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
        list.add(new FacultetsModel(1, "Faculty of Law & Social Sciences", R.drawable.ic_faculty));
        list.add(new FacultetsModel(2, "Faculty of Education & Humanities", R.drawable.ic_faculty));
        list.add(new FacultetsModel(3, "Faculty of Engineering & Natural Sciences", R.drawable.ic_faculty));
        list.add(new FacultetsModel(4, "Business School", R.drawable.ic_faculty));
        list.add(new FacultetsModel(5, "Audiences", R.drawable.ic_faculty));
        list.add(new FacultetsModel(6, "Academics", R.drawable.ic_faculty));
        return list;
    }

    public static List<RoomModel> getRooms() {
        ArrayList<RoomModel> list = new ArrayList<>();
        list.add(new RoomModel(1, "D101", 1, R.drawable.map_pin, 43.208260, 76.669800, false));
        list.add(new RoomModel(2, "D102", 1, R.drawable.map_pin, 43.208265, 76.669875, false));
        list.add(new RoomModel(3, "D103", 1, R.drawable.map_pin, 43.208270, 76.669940, false));
        list.add(new RoomModel(4, "D104", 1, R.drawable.map_pin, 43.208275, 76.670015, false));
        list.add(new RoomModel(5, "D105", 1, R.drawable.map_pin, 43.208280, 76.670090, false));
        list.add(new RoomModel(6, "D106", 1, R.drawable.map_pin, 43.208170, 76.670000, false));
        list.add(new RoomModel(7, "D107", 1, R.drawable.map_pin, 43.208140, 76.669880, false));

        list.add(new RoomModel(8, "E101", 2, R.drawable.map_pin, 43.207860, 76.669880, false));
        list.add(new RoomModel(9, "E102", 2, R.drawable.map_pin, 43.207865, 76.669950, false));
        list.add(new RoomModel(10, "E103", 2, R.drawable.map_pin, 43.207870, 76.670020, false));
        list.add(new RoomModel(11, "E104", 2, R.drawable.map_pin, 43.207875, 76.670090, false));
        list.add(new RoomModel(12, "E105", 2, R.drawable.map_pin, 43.207880, 76.670160, false));
        list.add(new RoomModel(13, "E106", 2, R.drawable.map_pin, 43.207770, 76.670070, false));
        list.add(new RoomModel(14, "E107", 2, R.drawable.map_pin, 43.207740, 76.669950, false));

        list.add(new RoomModel(15, "F101", 3, R.drawable.map_pin, 43.207460, 76.669980, false));
        list.add(new RoomModel(16, "F102", 3, R.drawable.map_pin, 43.207465, 76.670050, false));
        list.add(new RoomModel(17, "F103", 3, R.drawable.map_pin, 43.207470, 76.670120, false));
        list.add(new RoomModel(18, "F104", 3, R.drawable.map_pin, 43.207475, 76.670190, false));
        list.add(new RoomModel(19, "F105", 3, R.drawable.map_pin, 43.207480, 76.670260, false));
        list.add(new RoomModel(20, "F106", 3, R.drawable.map_pin, 43.207370, 76.670140, false));
        list.add(new RoomModel(21, "F107", 3, R.drawable.map_pin, 43.207340, 76.670020, false));

        list.add(new RoomModel(17, "G101", 4, R.drawable.map_pin, 43.207060, 76.670050, false));
        list.add(new RoomModel(18, "G102", 4, R.drawable.map_pin, 43.207065, 76.670120, false));
        list.add(new RoomModel(19, "G103", 4, R.drawable.map_pin, 43.207070, 76.670190, false));
        list.add(new RoomModel(20, "G104", 4, R.drawable.map_pin, 43.207075, 76.670260, false));
        list.add(new RoomModel(21, "G105", 4, R.drawable.map_pin, 43.207080, 76.670330, false));
        list.add(new RoomModel(22, "G106", 4, R.drawable.map_pin, 43.206970, 76.670260, false));
        list.add(new RoomModel(23, "G107", 4, R.drawable.map_pin, 43.206960, 76.670120, false));

        list.add(new RoomModel(24, "A1 - D117", 5, R.drawable.map_pin, 43.208220, 76.669400, false));
        list.add(new RoomModel(25, "B1 - D116", 5, R.drawable.map_pin, 43.208100, 76.669525, false));
        list.add(new RoomModel(26, "C1 - D113", 5, R.drawable.map_pin, 43.207920, 76.669465, false));
        list.add(new RoomModel(27, "D1 - E117", 5, R.drawable.map_pin, 43.207720, 76.669595, false));
        list.add(new RoomModel(28, "A2 - D218", 5, R.drawable.map_pin, 43.208220, 76.669400, false));
        list.add(new RoomModel(29, "B2 - D116", 5, R.drawable.map_pin, 43.208100, 76.669525, false));
        list.add(new RoomModel(30, "C2 - D214", 5, R.drawable.map_pin, 43.207920, 76.669465, false));
        list.add(new RoomModel(31, "D2 - E221", 5, R.drawable.map_pin, 43.207720, 76.669595, false));

        list.add(new RoomModel(32, "Center of Multidisciplinary Education", 6, R.drawable.map_pin, 43.208220, 76.669400, false));
        list.add(new RoomModel(33, "Continuing Education Center", 6, R.drawable.map_pin, 43.208100, 76.669525, false));
        list.add(new RoomModel(34, "Educational and Methodical Center", 6, R.drawable.map_pin, 43.207920, 76.669465, false));

        return list;
    }

    public static List<PlaceModel> getPlaces() {
        ArrayList<PlaceModel> places = new ArrayList<>();
        places.add(new PlaceModel(0, "White Canteen", 0, 43.208680, 76.669550));
        places.add(new PlaceModel(1, "Red Canteen", 0, 43.207550, 76.669600));
        places.add(new PlaceModel(2, "Dining Room", 0, 43.207350, 76.669620));

        places.add(new PlaceModel(3, "Red Hall", 1, 43.208680, 76.670230));
        places.add(new PlaceModel(4, "Mini Red Hall", 1, 43.206930, 76.66955));

        places.add(new PlaceModel(5, "Library", 2, 43.208800, 76.669660));
        places.add(new PlaceModel(6, "Market", 3, 43.207660, 76.669450));;
        places.add(new PlaceModel(7, "IT department", 4, 43.208170, 76.670000));
        places.add(new PlaceModel(8, "Wardrobe", 5, 43.208400, 76.669200));
        places.add(new PlaceModel(9, "Medical center", 6, 43.206820, 76.670000));

        places.add(new PlaceModel(10, "WC", 7, 43.208200, 76.669720));
        places.add(new PlaceModel(11, "WC", 7, 43.207800, 76.669800));
        places.add(new PlaceModel(12, "WC", 7, 43.207400, 76.669900));
        places.add(new PlaceModel(13, "WC", 7, 43.207000, 76.669970));

        places.add(new PlaceModel(14, "Accounting", 8, 43.208550, 76.669800));
        places.add(new PlaceModel(15, "Student center", 9, 43.208000, 76.669790));;
        places.add(new PlaceModel(16, "Copy center", 10, 43.207220, 76.669950));
        places.add(new PlaceModel(17, "Technopark", 11, 43.208400, 76.669200));
        places.add(new PlaceModel(18, "Career center", 12, 43.208450, 76.669690));
        places.add(new PlaceModel(19, "Wifi-zone", 13, 43.208550, 76.669450));
        places.add(new PlaceModel(20, "IR office", 14, 43.207550, 76.669870));

        return places;
    }

    public static ArrayList<CategoryModel> getCategories() {
        ArrayList<CategoryModel> categories = new ArrayList<>();
        categories.add(new CategoryModel(0, "Eating", R.drawable.ic_eat, false));
        categories.add(new CategoryModel(1, "Halls", R.drawable.ic_hall, false));
        categories.add(new CategoryModel(2, "Library", R.drawable.ic_library, false));
        categories.add(new CategoryModel(3, "Market", R.drawable.ic_others, false));
        categories.add(new CategoryModel(4, "It department", R.drawable.ic_it_department, false));
        categories.add(new CategoryModel(5, "Wardrobe", R.drawable.ic_wardrobe, false));
        categories.add(new CategoryModel(6, "Medical center", R.drawable.ic_med, false));
        categories.add(new CategoryModel(7, "WC", R.drawable.ic_wc, false));
        categories.add(new CategoryModel(8, "Accounting ", R.drawable.ic_accounting, false));
        categories.add(new CategoryModel(9, "Student center", R.drawable.ic_s_center, false));
        categories.add(new CategoryModel(10, "Copy center", R.drawable.ic_copycenter, false));
        categories.add(new CategoryModel(11, "Technopark ", R.drawable.ic_technopark, false));
        categories.add(new CategoryModel(12, "Career center", R.drawable.ic_career_center, false));
        categories.add(new CategoryModel(13, "Wifi-zone", R.drawable.ic_wifi_zone, false));
        categories.add(new CategoryModel(14, "IR office", R.drawable.ic_ir_center, false));
        return categories;
    }
}
