package com.example.artistapi.service;

import com.example.artistapi.model.Album;
import com.example.artistapi.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;


@Service
public class CoverartServiceImplementation implements CoverartService {
    @Autowired
    private RestTemplate restTemplate;


    @Override
    public ArrayList<Album> getArtistAlbumCovers(ArrayList<Album> imagelessAlbums) {

        //Sätt igång parallelisering
        for (Album image: imagelessAlbums) {
            try {
                String id = image.getId();
                String url = String.format("http://coverartarchive.org/release-group/%s", id);

                ResponseEntity<Image> response = restTemplate.exchange (url, HttpMethod.GET, null, Image.class);
                Image imageObj = response.getBody();

                assert imageObj != null;
                image.setImage(imageObj.getFirstImage());
            } catch(Exception ignored) {
                image.setImage("Image was not found for this album.");
            }
        }
        return imagelessAlbums;
    }

    //Image.getFirstImage() on returned object
    @Override
    public Image asyncGetArtistAlbumCovers(String albumId) {
        Image responseImage = null;

        try {
            String url = String.format("http://coverartarchive.org/release-group/%s", albumId);
            responseImage = restTemplate.getForObject(url, Image.class);

        } catch(Exception ignored) {
        }
        return responseImage;
    }

    public CompletableFuture<Image> getAlbumCovers(String albumId) {
        System.out.println(Thread.currentThread());

        Image responseImage = null;

        try {
            String url = String.format("http://coverartarchive.org/release-group/%s", albumId);
            responseImage = restTemplate.getForObject(url, Image.class);

        } catch(Exception ignored) {}

        return CompletableFuture.completedFuture(responseImage);
    }

    public ArrayList<Album> asyncGetAllAlbumCovers(ArrayList<Album> imagelessAlbums) {
        ArrayList<CompletableFuture<Image>> features = new ArrayList<CompletableFuture<Image>>();

        for (Album album : imagelessAlbums) {
            String albumId = album.getId();

            CompletableFuture<Image> feature = getAlbumCovers(albumId);
            features.add(feature);
        }

        CompletableFuture.allOf(features).join();



    }

}
