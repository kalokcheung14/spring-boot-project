package com.example.actuatorservice;

public record ItunesDataItem(
        int trackId,
        String wrapperType,
        String kind,
        String artistName,
        String collectionName,
        String trackName,
        String trackViewUrl,
        String artworkUrl30,
        String artworkUrl60,
        double collectionPrice,
        double trackPrice,
        String releaseDate,
        String collectionExplicitness,
        String trackExplicitness,
        int trackTimeMillis,
        String country,
        String currency,
        String primaryGenreName
){

}
