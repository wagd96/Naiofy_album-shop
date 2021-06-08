package com.naiofy.albums_shop.actions.invalidate_sessions;

import com.naiofy.albums_shop.actions.CommonActions;
import com.naiofy.albums_shop.utils.WSEndpoints;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class InvalidateSessionsActions {
    @Step("Invalidate sessions into the platform")
    public Response invalidateSessions(String token) {
        return SerenityRest.given().spec(CommonActions.albumShopRequestSpec())
                .header("Authorization", token)
                .basePath(WSEndpoints.INVALIDATE_SESSIONS.getBasePath())
                .get().then().extract().response();
    }

    @Step("Invalidate session into the platform without token")
    public Response invalidateSessionsWithoutToken() {
        return SerenityRest.given().spec(CommonActions.albumShopRequestSpec())
                .basePath(WSEndpoints.INVALIDATE_SESSIONS.getBasePath())
                .get().then().extract().response();
    }
}
