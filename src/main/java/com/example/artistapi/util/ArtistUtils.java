package com.example.artistapi.util;

public class ArtistUtils {

    public static String parseToApiEncodedString(String originalString) {
        String spaceExchangedString = originalString.replace(" ", "_");

        return spaceExchangedString.substring(1, spaceExchangedString.length() - 1);
    }
}
