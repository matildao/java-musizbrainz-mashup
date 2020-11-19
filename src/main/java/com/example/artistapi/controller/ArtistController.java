package com.example.artistapi.controller;

import com.example.artistapi.model.Album;
import com.example.artistapi.model.Artist;
import com.example.artistapi.model.MusicbrainzObject;
import com.example.artistapi.service.CoverartService;
import com.example.artistapi.service.MusicbrainzService;
import com.example.artistapi.service.WikidataService;
import com.example.artistapi.util.ArtistUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ArtistController {
    //5b11f4ce-a62d-471e-81fc-a69a8278c7da   testid

    @Autowired
    private MusicbrainzService musicbrainzService;

    @Autowired
    private CoverartService coverartService;

    @Autowired
    private WikidataService wikidataService;

    @GetMapping("/{MBID}")
    public ResponseEntity<Artist> getArtist(@PathVariable("MBID") String MBID) throws JsonProcessingException {
        MusicbrainzObject artist = musicbrainzService.getArtistInformation(MBID);

        String wikiId = artist.getWikiId();
        ArrayList<Album> imagelessAlbums = artist.getReleaseGroups();
        ArrayList<Album> albumsWithImages = coverartService.getArtistAlbumCovers(imagelessAlbums);

        String artistSearchName = wikidataService.getArtistSearchName(wikiId);
        String description = wikidataService.getArtistDescription(ArtistUtils.parseToApiEncodedString(artistSearchName), wikiId);

        Artist responseArtist = new Artist(MBID, description, albumsWithImages);

        return new ResponseEntity<>(responseArtist, HttpStatus.OK);
    }
}
