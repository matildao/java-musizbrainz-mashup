package com.example.artistapi.service;

import com.example.artistapi.model.Album;
import com.example.artistapi.model.Image;

import java.util.ArrayList;

public interface CoverartService {
    public abstract ArrayList<Album> getArtistAlbumCovers(ArrayList<Album> imagelessAlbums);
    public abstract Image asyncGetArtistAlbumCovers(String imagelessAlbums);

}
