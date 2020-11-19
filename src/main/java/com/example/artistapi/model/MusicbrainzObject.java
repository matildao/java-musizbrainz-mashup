package com.example.artistapi.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MusicbrainzObject {
    private String id;
    private List<Relation> relations;

    @JsonProperty("release-groups")
    private ArrayList<Album> releaseGroups;

    @Getter
    @Setter
    public static class Url {
        private String id;
        private String resource;
    }

    @Getter
    @Setter
    public static class Relation {
        private String type;
        private Url url;
    }

    public String getWikiId() {
        for(Relation obj: relations){
            if (obj.type.equals("wikipedia")) {
                return obj.getUrl().getResource();
            } else if (obj.type.equals("wikidata")) {
                return obj.getUrl().getResource().replace("https://www.wikidata.org/wiki/", "");
            }
        }
        return "";
    }
}
