package com.naiofy.albums_shop.actions.purchased_album_list;

import com.naiofy.albums_shop.actions.CommonActions;
import com.naiofy.albums_shop.utils.Dictionary;
import com.naiofy.albums_shop.utils.WSEndpoints;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.Map;

public class PurchasedAlbumListActions {
    @Step("See purchased albums into the platform")
    public Response seePurchasedAlbums(Map<String, String> userInfo) {
        return SerenityRest.given().spec(CommonActions.albumShopRequestSpec())
                .header("Authorization", userInfo.get(Dictionary.TOKEN_TAG))
                .basePath(String.format(WSEndpoints.PURCHASED_ALBUM_LIST.getBasePath(), userInfo.get(Dictionary.USER_ID_TAG)))
                .get().then().extract().response();
    }

    @Step("See purchased albums into the platform without token")
    public Response seePurchasedAlbumsWithoutToken(int userId) {
        return SerenityRest.given().spec(CommonActions.albumShopRequestSpec())
                .basePath(String.format(WSEndpoints.PURCHASED_ALBUM_LIST.getBasePath(), userId))
                .get().then().extract().response();
    }

    @Step("See purchased albums without userId into the platform")
    public Response seePurchasedAlbumsWithoutUserId(String token) {
        return SerenityRest.given().spec(CommonActions.albumShopRequestSpec())
                .header("Authorization", token)
                .basePath(String.format(WSEndpoints.PURCHASED_ALBUM_LIST.getBasePath(), ""))
                .get().then().extract().response();
    }

    @Step("See purchased albums for invalid userId into the platform")
    public Response seePurchasedAlbumsForInvalidUserId(String token) {
        return SerenityRest.given().spec(CommonActions.albumShopRequestSpec())
                .header("Authorization", token)
                .basePath(String.format(WSEndpoints.PURCHASED_ALBUM_LIST.getBasePath(), "a"))
                .get().then().extract().response();
    }
}
