package com.naiofy.albums_shop.utils;

import net.serenitybdd.rest.SerenityRest;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class LastResponseTransformation {
    public static Map<String, String> returned() {
        return mapOfStringsFrom(SerenityRest.lastResponse().getBody().as(Map.class));
    }

    private static Map<String, String> mapOfStringsFrom(Map<String, Object> map) {
        return map.entrySet()
                .stream()
                .collect(toMap(Map.Entry::getKey,
                        entry -> entry.getValue().toString()));
    }
}
