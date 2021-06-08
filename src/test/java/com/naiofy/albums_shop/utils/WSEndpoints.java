package com.naiofy.albums_shop.utils;

public enum WSEndpoints {
    LOGIN("users/sessions"),
    REGISTRATION("users"),
    ALBUM_LIST("albums"),
    BUY_ALBUM("albums/%s"),
    INVALIDATE_SESSIONS("users/sessions/invalidate_all"),
    PHOTO_ALBUM_LIST("albums/%s/photos"),
    PURCHASED_ALBUM_LIST("users/%s/albums"),
    USER_LIST("users"),
    USER_SEARCH("users/%s");

    private final String basePath;

    WSEndpoints(String basePath) {
        this.basePath = basePath;
    }

    public String getBasePath() {
        return basePath;
    }
}
