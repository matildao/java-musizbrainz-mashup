package com.example.artistapi.service;

import com.example.artistapi.model.MusicbrainzObject;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface MusicbrainzService {
    public abstract MusicbrainzObject getArtistInformation(String mbid) throws JsonProcessingException;
}
