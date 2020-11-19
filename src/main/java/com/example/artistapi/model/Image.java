package com.example.artistapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Image {
    private ArrayList<ImageObj> images;

    public Image(ArrayList<ImageObj> images) {
        this.images = images;
    }

    public String getFirstImage() {
        return images.get(0).image;
    };

    @Getter
    @Setter
    public static class ImageObj {
        private String image;
    }


}



