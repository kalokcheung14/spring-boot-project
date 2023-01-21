package com.example.actuatorservice;

import java.util.List;

public record ItunesSearchResult(
        int resultCount,
        List<ItunesDataItem> results
) {

}
