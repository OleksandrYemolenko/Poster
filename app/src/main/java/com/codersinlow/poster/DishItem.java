package com.codersinlow.poster;

import android.media.Image;

import java.util.ArrayList;
import java.util.List;

public class DishItem {

    private boolean expanded;
    private String title;
    private String price;
    private String imgURL;

    DishItem(String title, String imgURL, String price) {
        this.title = title;
        this.price = price;
        this.imgURL = imgURL;
        // File file = new File(@R.);
    }

    public String getName() {
        return title;
    }
    public String getPrice() {
        return price;
    }

    public String getURL() {
        return imgURL;
    }


    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public static List<DishItem> getDishItem() {
        ArrayList<DishItem> dishList = new ArrayList<>();


        // TODO Передать в dishList список блюд с именем (title), URL на картинку (imgURL) и ценой (price)
        dishList.add(new DishItem("Hot Dog", "https://www.micccp.com/wp/wp-content/uploads/2016/02/4111.jpg", "30.00₴"));
        dishList.add(new DishItem("Coffee", "https://million-wallpapers.ru/wallpapers/2/56/294040267495523.jpg", "25.00₴"));
        dishList.add(new DishItem("Juice", "http://images.media-allrecipes.com/userphotos/960x960/3758394.jpg", "28.00₴"));
        dishList.add(new DishItem("Pizza", "http://food.studiofact.ru/upload/iblock/706/7062914c3c69c543991a45ca006e456b.jpg", "300.00₴"));
        dishList.add(new DishItem("Cake", "https://31p86334w2bvkz0249eyr0cr-wpengine.netdna-ssl.com/wp-content/uploads/2013/04/triple-chocolate-cake-4-600x900.jpg", "150.00₴"));

        return dishList;
    }
}
