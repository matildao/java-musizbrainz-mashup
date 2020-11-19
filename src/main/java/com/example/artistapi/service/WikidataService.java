package com.example.artistapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface WikidataService {
    public abstract String getArtistSearchName(String wikiDataId) throws JsonProcessingException;
    public abstract String getArtistDescription(String artistSearchName, String mbid) throws JsonProcessingException;
}
