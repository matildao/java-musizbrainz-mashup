package com.example.artistapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Artist {
    private String mbid;
    private String description;
    private ArrayList<Album> albums;

    public Artist(String mbid, String description, ArrayList<Album> albums) {
        this.mbid = mbid;
        this.description = description;
        this.albums = albums;
    }
}
