package com.example.artistapi.service;

import com.example.artistapi.model.MusicbrainzObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class MusicbrainzServiceImplementation implements MusicbrainzService{
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public MusicbrainzObject getArtistInformation(String mbid) {
        return restTemplate.getForObject("http://musicbrainz.org/ws/2/artist/" + mbid + "?&fmt=json&inc=url-rels+release-groups", MusicbrainzObject.class);
    }
}
