package com.example.artistapi.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Iterator;
import java.util.Map;


@Service
public class WikiServiceImplmentation implements WikidataService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String getArtistSearchName(String wikiDataId) throws JsonProcessingException {
        String url = String.format("https://www.wikidata.org/w/api.php?action=wbgetentities&ids=%s&format=json&props=sitelinks", wikiDataId);
        String json = restTemplate.getForObject(url, String.class);

        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        JsonNode rootNode = mapper.readTree(json);

        Iterator<Map.Entry<String,JsonNode>> fieldsIterator = rootNode.fields();
        Map.Entry<String, JsonNode> field = fieldsIterator.next();

        JsonNode sitelinks = field.getValue().get(wikiDataId).get("sitelinks");

        Iterator<Map.Entry<String,JsonNode>> wikiIterator = sitelinks.fields();
        while (wikiIterator.hasNext()) {

            Map.Entry<String, JsonNode> wikifield = wikiIterator.next();

            if (wikifield.getKey().equals("enwiki")) {
                return wikifield.getValue().get("title").toString();
            }
        }
        return "";
    }

    @Override
    public String getArtistDescription(String artistSearchName, String mbid) throws JsonProcessingException {
        String url = String.format("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=extracts&exintro=true&redirects=true&titles=%s", artistSearchName);

        JSONObject jsonobject = new JSONObject(restTemplate.getForObject(url, String.class));
        JSONObject pages = jsonobject.getJSONObject("query").getJSONObject("pages");

        Iterator<String> keys = pages.keys();
        JSONObject id = pages.getJSONObject(keys.next());

        return id.getString("extract");
    }
}
