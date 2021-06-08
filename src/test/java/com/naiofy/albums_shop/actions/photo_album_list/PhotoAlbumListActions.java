package com.naiofy.albums_shop.actions.photo_album_list;

import com.naiofy.albums_shop.actions.CommonActions;
import com.naiofy.albums_shop.utils.WSEndpoints;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class PhotoAlbumListActions {
    @Step("See the photos from an album into the platform")
    public Response searchPhotos(String token, int albumId) {
        return SerenityRest.given().spec(CommonActions.albumShopRequestSpec())
                .header("Authorization", token)
                .basePath(String.format(WSEndpoints.PHOTO_ALBUM_LIST.getBasePath(), albumId))
                .get().then().extract().response();
    }

    @Step("See the photos from an album into the platform")
    public Response searchPhotosWithoutToken(int albumId) {
        return SerenityRest.given().spec(CommonActions.albumShopRequestSpec())
                .basePath(String.format(WSEndpoints.PHOTO_ALBUM_LIST.getBasePath(), albumId))
                .get().then().extract().response();
    }

    @Step("See the photos from an album without albumId into the platform")
    public Response searchPhotosWithoutAlbumId(String token) {
        return SerenityRest.given().spec(CommonActions.albumShopRequestSpec())
                .header("Authorization", token)
                .basePath(String.format(WSEndpoints.PHOTO_ALBUM_LIST.getBasePath(), ""))
                .get().then().extract().response();
    }

    @Step("See the photos from an album for invalid albumId into the platform")
    public Response searchPhotosForInvalidAlbumId(String token) {
        return SerenityRest.given().spec(CommonActions.albumShopRequestSpec())
                .header("Authorization", token)
                .basePath(String.format(WSEndpoints.PHOTO_ALBUM_LIST.getBasePath(), "a"))
                .get().then().extract().response();
    }
}
