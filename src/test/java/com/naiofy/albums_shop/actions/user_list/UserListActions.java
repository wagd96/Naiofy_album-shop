package com.naiofy.albums_shop.actions.user_list;

import com.naiofy.albums_shop.actions.CommonActions;
import com.naiofy.albums_shop.utils.Dictionary;
import com.naiofy.albums_shop.utils.WSEndpoints;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.Map;

public class UserListActions {

    @Step("Search a specific user into the platform")
    public Response searchSpecificUser(Map<String, String> loginInfo) {
        return SerenityRest.given().spec(CommonActions.albumShopRequestSpec())
                .header("Authorization", loginInfo.get(Dictionary.TOKEN_TAG))
                .basePath(String.format(WSEndpoints.USER_SEARCH.getBasePath(), loginInfo.get(Dictionary.USER_ID_TAG)))
                .get().then().extract().response();
    }

    @Step("See the users into the platform")
    public Response searchUsers(String token) {
        return SerenityRest.given().spec(CommonActions.albumShopRequestSpec())
                .header("Authorization", token)
                .basePath(WSEndpoints.USER_LIST.getBasePath())
                .get().then().extract().response();
    }

    @Step("See the users into the platform")
    public Response searchUsersWithoutToken() {
        return SerenityRest.given().spec(CommonActions.albumShopRequestSpec())
                .basePath(WSEndpoints.USER_LIST.getBasePath())
                .get().then().extract().response();
    }

    @Step("See the users into the platform in specific page")
    public Response searchUsersInSpecificPage(String token, int page) {
        return SerenityRest.given().spec(CommonActions.albumShopRequestSpec())
                .header("Authorization", token)
                .basePath(WSEndpoints.USER_LIST.getBasePath())
                .param(Dictionary.PAGE_TAG, page)
                .get().then().extract().response();
    }
}
