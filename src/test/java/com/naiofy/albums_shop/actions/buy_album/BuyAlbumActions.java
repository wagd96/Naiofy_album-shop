package com.naiofy.albums_shop.actions.buy_album;

import com.naiofy.albums_shop.actions.CommonActions;
import com.naiofy.albums_shop.utils.WSEndpoints;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class BuyAlbumActions {
    @Step("Buy an album into the platform")
    public Response buyAnAlbum(String token, int albumId) {
        return SerenityRest.given().spec(CommonActions.albumShopRequestSpec())
                .header("Authorization", token)
                .basePath(String.format(WSEndpoints.BUY_ALBUM.getBasePath(), albumId))
                .get().then().extract().response();
    }

    @Step("Buy an album into the platform without token")
    public Response buyAlbumWithoutToken(int albumId) {
        return SerenityRest.given().spec(CommonActions.albumShopRequestSpec())
                .basePath(String.format(WSEndpoints.BUY_ALBUM.getBasePath(), albumId))
                .get().then().extract().response();
    }

    @Step("Buy an album for invalid albumId into the platform")
    public Response buyInvalidAlbumId(String token) {
        return SerenityRest.given().spec(CommonActions.albumShopRequestSpec())
                .header("Authorization", token)
                .basePath(String.format(WSEndpoints.BUY_ALBUM.getBasePath(), "a"))
                .get().then().extract().response();
    }
}
