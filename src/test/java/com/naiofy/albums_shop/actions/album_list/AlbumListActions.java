package com.naiofy.albums_shop.actions.album_list;

import com.naiofy.albums_shop.actions.CommonActions;
import com.naiofy.albums_shop.utils.WSEndpoints;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class AlbumListActions {
    @Step("See the albums into the platform")
    public Response searchAlbums(String token) {
        return SerenityRest.given().spec(CommonActions.albumShopRequestSpec())
                .header("Authorization", token)
                .basePath(WSEndpoints.ALBUM_LIST.getBasePath())
                .get().then().extract().response();
    }

    @Step("See the albums into the platform")
    public Response searchAlbumsWithoutToken() {
        return SerenityRest.given().spec(CommonActions.albumShopRequestSpec())
                .basePath(WSEndpoints.ALBUM_LIST.getBasePath())
                .get().then().extract().response();
    }
}
