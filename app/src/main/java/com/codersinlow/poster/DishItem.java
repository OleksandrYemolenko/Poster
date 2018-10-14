package com.codersinlow.poster;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class DishItem extends AppCompatActivity {

    private String title;
    private String imgURL;
    private Image img;

    DishItem(String title, String imgURL) {
        this.title = title;
        this.imgURL = imgURL;
       // File file = new File(@R.);
    }

    public String getName() {
        return title;
    }

    public String getURL() {
        return imgURL;
    }

    public static List<DishItem> getDishItem() {
        ArrayList<DishItem> dishList = new ArrayList<>();

        // Передать в dishList список блюд с именем (title) и картиткой (img)
        dishList.add(new DishItem("Cake", "https://31p86334w2bvkz0249eyr0cr-wpengine.netdna-ssl.com/wp-content/uploads/2013/04/triple-chocolate-cake-4-600x900.jpg"));
        dishList.add(new DishItem("Cake", "https://31p86334w2bvkz0249eyr0cr-wpengine.netdna-ssl.com/wp-content/uploads/2013/04/triple-chocolate-cake-4-600x900.jpg"));
        dishList.add(new DishItem("Cake", "https://31p86334w2bvkz0249eyr0cr-wpengine.netdna-ssl.com/wp-content/uploads/2013/04/triple-chocolate-cake-4-600x900.jpg"));

        return dishList;
    }
}
