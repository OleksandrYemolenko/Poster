package com.codersinlow.poster;

import android.media.Image;

import java.util.ArrayList;
import java.util.List;

public class DishItem {

    private boolean expanded;
    private String title;
    private String price;
    private String imgURL;
    private String description;

    DishItem(String title, String imgURL, String price, String description) {
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
}
